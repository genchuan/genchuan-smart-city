package cn.iocoder.yudao.module.accessmgmt.service.accessreport.cyclereport;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.accessreport.cyclereport.vo.*;
import cn.iocoder.yudao.module.accessmgmt.dal.dataobject.accessreport.cyclereport.AccessCycleReportDO;
import cn.iocoder.yudao.module.accessmgmt.dal.mysql.accessreport.cyclereport.AccessCycleReportMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.accessmgmt.enums.ErrorCodeConstants.*;

/**
 * 通行周期报表 Service 实现类
 * <p>
 * 提供通行周期报表的全流程业务：分页查询、报表生成（支持日报/周报/月报/季报/半年报/年报/自定义共7种周期）、数据导出及多维度态势分析。
 * 报表生成时会根据周期类型自动计算起止时间范围，生成报表名称，并模拟填充统计指标数据。
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AccessCycleReportServiceImpl implements AccessCycleReportService {

    @Resource
    private AccessCycleReportMapper accessCycleReportMapper;

    // ==================== 报表查询 ====================

    @Override
    public PageResult<AccessCycleReportRespVO> getAccessCycleReportPage(AccessCycleReportPageReqVO pageReqVO) {
        // 分页查询，Mapper 层按 reportName(模糊)/cycleType(精确)/createTime 范围动态条件筛选，按主键倒序
        PageResult<AccessCycleReportDO> pageResult = accessCycleReportMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult, AccessCycleReportRespVO.class);
    }

    @Override
    public AccessCycleReportRespVO getAccessCycleReport(Long id) {
        // 按主键查单条，不存在抛 ACCESS_CYCLE_REPORT_NOT_EXISTS 业务异常
        AccessCycleReportDO entity = accessCycleReportMapper.selectById(id);
        if (entity == null) {
            throw exception(ACCESS_CYCLE_REPORT_NOT_EXISTS);
        }
        return BeanUtils.toBean(entity, AccessCycleReportRespVO.class);
    }

    // ==================== 报表生成 ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AccessCycleReportGenerateRespVO generateAccessCycleReport(AccessCycleReportGenerateReqVO reqVO) {
        // 1. 构造 DO，写入周期类型
        AccessCycleReportDO entity = new AccessCycleReportDO();
        entity.setCycleType(reqVO.getCycleType());

        // 2. 根据周期类型计算起止时间范围
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start;
        LocalDateTime end;

        if ("自定义".equals(reqVO.getCycleType())) {
            // 自定义周期：使用前端传入的时间戳（东八区转换），未传则默认当前时间
            start = reqVO.getStartTime() != null ?
                    Instant.ofEpochMilli(reqVO.getStartTime()).atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime() : now;
            end = reqVO.getEndTime() != null ?
                    Instant.ofEpochMilli(reqVO.getEndTime()).atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime() : now;
        } else {
            // 预设周期：自动计算 start → end（start = now - 周期时长，end = 当天 23:59:59）
            // 日报(当天) / 周报(近1周) / 月报(近1月) / 季报(近3月) / 半年报(近6月) / 年报(近1年)
            switch (reqVO.getCycleType()) {
                case "日报":
                    start = now.toLocalDate().atStartOfDay();
                    end = now.toLocalDate().atTime(23, 59, 59);
                    break;
                case "周报":
                    start = now.minusWeeks(1).toLocalDate().atStartOfDay();
                    end = now.toLocalDate().atTime(23, 59, 59);
                    break;
                case "月报":
                    start = now.minusMonths(1).toLocalDate().atStartOfDay();
                    end = now.toLocalDate().atTime(23, 59, 59);
                    break;
                case "季报":
                    start = now.minusMonths(3).toLocalDate().atStartOfDay();
                    end = now.toLocalDate().atTime(23, 59, 59);
                    break;
                case "半年报":
                    start = now.minusMonths(6).toLocalDate().atStartOfDay();
                    end = now.toLocalDate().atTime(23, 59, 59);
                    break;
                case "年报":
                    start = now.minusYears(1).toLocalDate().atStartOfDay();
                    end = now.toLocalDate().atTime(23, 59, 59);
                    break;
                default:
                    start = now.toLocalDate().atStartOfDay();
                    end = now.toLocalDate().atTime(23, 59, 59);
            }
        }

        entity.setStartTime(start);
        entity.setEndTime(end);

        // 3. 生成报表名称：周期类型 + "通行报表_" + 起始日期 + "_" + 结束日期（如 "日报通行报表_20260518_20260518"）
        String reportName = reqVO.getCycleType() + "通行报表_"
                + start.format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "_"
                + end.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        entity.setReportName(reportName);

        // 4. 模拟填充统计指标数据（随机数模拟）
        // 人员通行总量: 100~1000, 访客到访总量: 20~200, 车辆通行总量: 50~500
        entity.setTotalPersonAccess(100 + (int) (Math.random() * 900));
        entity.setTotalVisitorArrive(20 + (int) (Math.random() * 180));
        entity.setTotalVehicleAccess(50 + (int) (Math.random() * 450));
        // 车位使用率: 50%~100%, 缴费收入: 500~10000
        entity.setSpaceUseRate(BigDecimal.valueOf(50.00 + Math.random() * 50.00)
                .setScale(2, java.math.RoundingMode.HALF_UP));
        entity.setPayIncome(BigDecimal.valueOf(500.00 + Math.random() * 9500.00)
                .setScale(2, java.math.RoundingMode.HALF_UP));

        // 5. 入库返回报表ID
        accessCycleReportMapper.insert(entity);

        AccessCycleReportGenerateRespVO respVO = new AccessCycleReportGenerateRespVO();
        respVO.setSuccess(true);
        respVO.setReportId(entity.getId());
        return respVO;
    }

    // ==================== 数据导出 ====================

    @Override
    public List<AccessCycleReportRespVO> getAccessCycleReportList(AccessCycleReportPageReqVO pageReqVO) {
        // 设置 PAGE_SIZE_NONE 绕过 MyBatis-Plus 分页限制，查询全量数据
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<AccessCycleReportDO> pageResult = accessCycleReportMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult.getList(), AccessCycleReportRespVO.class);
    }

    // ==================== 报表态势 ====================

    @Override
    public AccessCycleReportChartRespVO getAccessCycleReportChart(Long id) {
        // 1. 校验报表存在
        AccessCycleReportDO entity = accessCycleReportMapper.selectById(id);
        if (entity == null) {
            throw exception(ACCESS_CYCLE_REPORT_NOT_EXISTS);
        }
        // 2. 聚合查询多维度数据：卡片统计 + 11个维度列表（通行趋势/车辆趋势/车位趋势/区域分布/车场分布/人员类型/车辆类型/支付方式/点位热力/轨迹地图）
        AccessCycleReportChartRespVO chartVO = new AccessCycleReportChartRespVO();
        // 卡片数据
        AccessCycleReportChartRespVO.CardData cardData = accessCycleReportMapper.selectCardData(id);
        chartVO.setCardData(cardData);
        // 趋势与分布列表
        chartVO.setAccessTrendList(accessCycleReportMapper.selectAccessTrendList(id));
        chartVO.setVehicleTrendList(accessCycleReportMapper.selectVehicleTrendList(id));
        chartVO.setSpaceTrendList(accessCycleReportMapper.selectSpaceTrendList(id));
        chartVO.setAreaCountList(accessCycleReportMapper.selectAreaCountList(id));
        chartVO.setParkCountList(accessCycleReportMapper.selectParkCountList(id));
        chartVO.setPersonTypeList(accessCycleReportMapper.selectPersonTypeList(id));
        chartVO.setVehicleTypeList(accessCycleReportMapper.selectVehicleTypeList(id));
        chartVO.setPayTypeList(accessCycleReportMapper.selectPayTypeList(id));
        chartVO.setPointMapList(accessCycleReportMapper.selectPointMapList(id));
        chartVO.setTrackMapList(accessCycleReportMapper.selectTrackMapList(id));
        return chartVO;
    }

}
