package cn.iocoder.yudao.module.inspectop.service.cyclereport;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.inspectop.controller.admin.cyclereport.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.cyclereport.CycleReportDO;
import cn.iocoder.yudao.module.inspectop.dal.mysql.cyclereport.CycleReportMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
@Validated
@Slf4j
public class CycleReportServiceImpl implements CycleReportService {

    @Resource
    private CycleReportMapper cycleReportMapper;

    @Resource
    @Qualifier("taskScheduler")
    private Executor executor; // 异步线程池

    @Override
    public PageResult<CycleReportRespVO> getCycleReportPage(CycleReportPageReqVO pageReqVO) {
        // 直接使用实时查询
        if (pageReqVO.getStatTimeStart() != null && pageReqVO.getStatTimeEnd() != null
                && pageReqVO.getStationId() != null) {
            List<CycleReportRespVO> list = cycleReportMapper.selectRealTimePage(pageReqVO);
            return new PageResult<>(list, (long) list.size());
        } else {
            // 如果没有时间范围和场站，返回空
            return new PageResult<>();
        }
    }

    @Override
    public CycleReportRespVO generateCycleReport(CycleReportGenerateReqVO generateReqVO) {

        // ========== 核心：自动根据报表类型计算时间(自定义报表：保留前端传入的 startTime、endTime 不变) ==========
        String reportType = generateReqVO.getReportCycle();
        if (reportType!=null){
            //如果不是自定义报表，自动计算时间
            if (StrUtil.isNotBlank(reportType) && !"自定义报表".equals(reportType)) {
                // 非自定义：自动计算 开始/结束 时间
                Date[] dates = autoCalcReportTime(reportType);
                // 覆盖前端传入的时间（自动生成）
                // 方式1：使用 Date -> LocalDateTime 直接转换（推荐，无格式问题）
                generateReqVO.setStatTimeStart(LocalDateTime.ofInstant(dates[0].toInstant(), ZoneId.systemDefault()));
                generateReqVO.setStatTimeEnd(LocalDateTime.ofInstant(dates[1].toInstant(), ZoneId.systemDefault()));
            }
        }

        // 1. 获取统计参数
        Long stationId = generateReqVO.getStationId();
        LocalDateTime statTimeStart = generateReqVO.getStatTimeStart();
        LocalDateTime statTimeEnd = generateReqVO.getStatTimeEnd();

        // 2. 构建返回对象
        CycleReportRespVO respVO = new CycleReportRespVO();

        // 3. 设置基础信息
        respVO.setReportCycle(generateReqVO.getReportCycle());
        respVO.setStatTimeStart(statTimeStart);
        respVO.setStatTimeEnd(statTimeEnd);
        respVO.setStationId(stationId);
        respVO.setStationName(cycleReportMapper.selectStationNameById(stationId));
        respVO.setGenerateStatus("已生成");
        respVO.setGenerateTime(LocalDateTime.now());
        respVO.setOperator(SecurityFrameworkUtils.getLoginUserNickname());
        respVO.setExportCount(0);

        // 4. 核心：分步查询各个业务表数据
        // 4.1 设备监控统计
        Map<String, Object> deviceMap = cycleReportMapper.selectDeviceMonitorReport(stationId, statTimeStart, statTimeEnd);
        if (deviceMap != null) {
            respVO.setNormalDeviceNum(((Number) deviceMap.getOrDefault("normalDeviceNum", 0)).intValue());
            respVO.setAbnormalDeviceNum(((Number) deviceMap.getOrDefault("abnormalDeviceNum", 0)).intValue());
        } else {
            respVO.setNormalDeviceNum(0);
            respVO.setAbnormalDeviceNum(0);
            log.warn("设备监控统计查询返回null，stationId={}", stationId);
        }

        // 4.2 巡检任务统计
        Map<String, Object> taskMap = cycleReportMapper.selectInspectTaskReport(stationId, statTimeStart, statTimeEnd);
        respVO.setInspectTaskNum(((Number) taskMap.getOrDefault("inspectTaskNum", 0)).intValue());
        Object taskCompleteRate = taskMap.get("taskCompleteRate");
        respVO.setTaskCompleteRate(taskCompleteRate != null ?
                new BigDecimal(taskCompleteRate.toString()) : BigDecimal.ZERO);

        // 4.3 油车占位统计
        Map<String, Object> oilMap = cycleReportMapper.selectOilMonitorReport(stationId, statTimeStart, statTimeEnd);
        respVO.setOilWaitHandleNum(((Number) oilMap.getOrDefault("oilWaitHandleNum", 0)).intValue());
        Object oilHandleCompleteRate = oilMap.get("oilHandleCompleteRate");
        respVO.setOilHandleCompleteRate(oilHandleCompleteRate != null ?
                new BigDecimal(oilHandleCompleteRate.toString()) : BigDecimal.ZERO);

        // 4.4 巡检人员统计
        Map<String, Object> userMap = cycleReportMapper.selectInspectUserReport(stationId);
        respVO.setInspectUserOnlineNum(((Number) userMap.getOrDefault("inspectUserOnlineNum", 0)).intValue());

        // 4.5 资产信息统计
        Map<String, Object> assetMap = cycleReportMapper.selectAssetInfoReport(stationId);
        respVO.setAssetNormalNum(((Number) assetMap.getOrDefault("assetNormalNum", 0)).intValue());

        // 4.6 库存预警统计
        Map<String, Object> stockMap = cycleReportMapper.selectAssetStockReport(stationId);
        respVO.setStockWarnNum(((Number) stockMap.getOrDefault("stockWarnNum", 0)).intValue());

        // 5. 同比环比数据（这里需要你根据业务逻辑实现）
        // 暂时设置为默认值
        respVO.setYearOnYearData("--");
        respVO.setChainRatioData("--");

        return respVO;
    }

    /**
     * 根据报表类型，自动计算 开始时间、结束时间
     * @param reportType 报表类型
     * @return Date[0] = 开始时间，Date[1] = 结束时间
     */
    private Date[] autoCalcReportTime(String reportType) {
        Date now = new Date();
        Date startTime = null;
        Date endTime = null;

        switch (reportType) {
            case "日报":
                // 今天 00:00:00 ~ 23:59:59
                startTime = DateUtil.beginOfDay(now);
                endTime = DateUtil.endOfDay(now);
                break;
            case "周报":
                // 本周一 00:00:00 ~ 本周日 23:59:59
                startTime = DateUtil.beginOfWeek(now);
                endTime = DateUtil.endOfWeek(now);
                break;
            case "月报":
                // 本月1号 ~ 本月最后一天
                startTime = DateUtil.beginOfMonth(now);
                endTime = DateUtil.endOfMonth(now);
                break;
            case "季报":
                // 本季度第一天 ~ 本季度最后一天
                startTime = DateUtil.beginOfQuarter(now);
                endTime = DateUtil.endOfQuarter(now);
                break;
            case "半年报":
                // 上半年/下半年 自动计算
                int month = DateUtil.month(now) + 1;
                if (month <= 6) {
                    startTime = DateUtil.parse(DateUtil.year(now) + "-01-01");
                    endTime = DateUtil.parse(DateUtil.year(now) + "-06-30");
                } else {
                    startTime = DateUtil.parse(DateUtil.year(now) + "-07-01");
                    endTime = DateUtil.parse(DateUtil.year(now) + "-12-31");
                }
                break;
            case "年报":
                // 本年1月1日 ~ 12月31日
                startTime = DateUtil.beginOfYear(now);
                endTime = DateUtil.endOfYear(now);
                break;
            default:
                // 默认：今天
                startTime = DateUtil.beginOfDay(now);
                endTime = DateUtil.endOfDay(now);
        }
        return new Date[]{startTime, endTime};
    }



}