package cn.iocoder.yudao.module.stationresource.service.stationresource.decisionanalysis.stationopreport;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.decisionanalysis.stationopreport.vo.StationOpReportPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.decisionanalysis.stationopreport.vo.StationOpReportRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.decisionanalysis.stationopreport.vo.ops.StationOpReportChartRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.decisionanalysis.stationopreport.StationOpReportDO;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.decisionanalysis.stationopreport.StationOpReportMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

@Service
@Validated
public class StationOpReportServiceImpl implements StationOpReportService {

    @Resource
    private StationOpReportMapper stationOpReportMapper;

    @Override
    public StationOpReportDO getReport(Long id) {
        // 从 业务表 统计生成报表数据（不查物理表，查统计视图）
        return stationOpReportMapper.selectReportById(id);
    }


    @Override
    public PageResult<StationOpReportDO> getReportPage(StationOpReportPageReqVO pageReqVO) {
        // ========== 核心：自动根据报表类型计算时间(自定义报表：保留前端传入的 startTime、endTime 不变) ==========
        String reportType = pageReqVO.getReportType();
        if (reportType!=null){
            //如果不是自定义报表，自动计算时间
            if (StrUtil.isNotBlank(reportType) && !"自定义报表".equals(reportType)) {
                // 非自定义：自动计算 开始/结束 时间
                Date[] dates = autoCalcReportTime(reportType);
                // 覆盖前端传入的时间（自动生成）
                pageReqVO.setStartTime(LocalDateTime.parse(DateUtil.format(dates[0], "yyyy-MM-dd HH:mm:ss")));
                pageReqVO.setEndTime(LocalDateTime.parse(DateUtil.format(dates[1], "yyyy-MM-dd HH:mm:ss")));
            }
        }


        // 1. 创建分页对象（和你正确示例完全一致）
        Page<StationOpReportDO> mpPage = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        // 2. 调用 Mapper
        Page<StationOpReportDO> resultPage = stationOpReportMapper.selectReportPage(mpPage);

        // 3. 直接构造 PageResult（你正确的格式！）
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
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

//    @Override
//    public PageResult<StationOpReportRespVO> getReportPage(StationOpReportPageReqVO reqVO) {
//        // 1. 分页查询基础报表数据
//        PageResult<Map<String, Object>> pageResult = stationOpReportMapper.selectReportPage(reqVO);
//        if (CollUtil.isEmpty(pageResult.getList())) {
//            return PageResult.empty();
//        }
//
//        // 2. 组装统计指标
//        List<StationOpReportRespVO> list = pageResult.getList().stream().map(map -> {
//            StationOpReportRespVO resp = BeanUtils.toBean(map, StationOpReportRespVO.class);
//            // 从统计结果赋值扩展字段
//            resp.setStationCount(MapUtils.getInteger(map, "station_count", 0));
//            resp.setSpaceUseRate(cn.iocoder.yudao.framework.common.util.collection.MapUtils.getBigDecimal(map, "space_use_rate", BigDecimal.ZERO));
//            resp.setRuleMatchRate(cn.iocoder.yudao.framework.common.util.collection.MapUtils.getBigDecimal(map, "rule_match_rate", BigDecimal.ZERO));
//            resp.setYearOnYear(cn.iocoder.yudao.framework.common.util.collection.MapUtils.getBigDecimal(map, "year_on_year", BigDecimal.ZERO));
//            resp.setMonthOnMonth(cn.iocoder.yudao.framework.common.util.collection.MapUtils.getBigDecimal(map, "month_on_month", BigDecimal.ZERO));
//            return resp;
//        }).collect(Collectors.toList());
//
//        return new PageResult<>(list, pageResult.getTotal());
//    }
    @Override
    public StationOpReportChartRespVO getReportChart(Long reportId) {
        // 卡片：从 场站、车位、收费、订单 真实统计
        StationOpReportChartRespVO.CardDataVO card = stationOpReportMapper.selectCardData(reportId);

        // 折线：每日订单 & 营收 趋势
        List<StationOpReportChartRespVO.OperateLineVO> lineList = stationOpReportMapper.selectOperateLine(reportId);

        // 柱状：片区营收分布
        List<StationOpReportChartRespVO.AreaBarVO> barList = stationOpReportMapper.selectAreaBar(reportId);

        StationOpReportChartRespVO resp = new StationOpReportChartRespVO();
        resp.setCardData(card);
        resp.setOperateLineList(lineList);
        resp.setAreaBarList(barList);
        return resp;
    }
}
