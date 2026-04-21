package cn.iocoder.yudao.module.chargepark.carservice.service.decision;

import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.ChargeParkMapChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.NearStationChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.SpacePushChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.DisputeMediateChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.SuggestionChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.UserAppealChartRespVO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.decision.vo.ServiceOpReportChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.decision.vo.ServiceOpReportPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.decision.vo.ServiceOpReportRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.decision.vo.TimeReportRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo.PathPlanChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo.SpaceLocationChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo.RescueInfoChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.reserve.vo.ReserveListChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.serviceconfig.vo.WordingMgmtChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.enums.ReportPeriodEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * 决策分析 / 服务运营报表 Service 接口
 *
 * 不独立建表，基于 11 张业务表 SQL 聚合实现。
 *
 * 提供两类方法：
 * - chartXxx(): 12 个 chart 接口的扁平字段响应,严格按 05 接口文档命名
 * - generateReport / customReport: 时间尺度报表 + 自定义报表
 */
public interface ServiceOpReportService {

    // ========== chart 接口(返回扁平字段,严格按 05 接口文档命名) ==========

    RescueInfoChartRespVO chartRescue(LocalDateTime startTime, LocalDateTime endTime);
    ChargeParkMapChartRespVO chartChargeParkMap(LocalDateTime startTime, LocalDateTime endTime);
    NearStationChartRespVO chartNearStation(LocalDateTime startTime, LocalDateTime endTime);
    SpacePushChartRespVO chartSpacePush(LocalDateTime startTime, LocalDateTime endTime);
    ReserveListChartRespVO chartReserve(LocalDateTime startTime, LocalDateTime endTime);
    SpaceLocationChartRespVO chartSpaceLocation(LocalDateTime startTime, LocalDateTime endTime);
    PathPlanChartRespVO chartPathPlan(LocalDateTime startTime, LocalDateTime endTime);
    SuggestionChartRespVO chartSuggestion(LocalDateTime startTime, LocalDateTime endTime);
    UserAppealChartRespVO chartUserAppeal(LocalDateTime startTime, LocalDateTime endTime);
    DisputeMediateChartRespVO chartDisputeMediate(LocalDateTime startTime, LocalDateTime endTime);
    WordingMgmtChartRespVO chartWordingMgmt();
    ServiceOpReportChartRespVO chartServiceOpReport(LocalDateTime startTime, LocalDateTime endTime);

    // ========== 时间尺度报表 + 自定义报表 ==========

    /**
     * 自定义报表
     *
     * @param startTime 起始时间
     * @param endTime   结束时间
     * @param filters   筛选条件（业务模块/状态/类型...）
     */
    Map<String, Object> customReport(LocalDateTime startTime, LocalDateTime endTime, Map<String, Object> filters);

    /**
     * 生成时间尺度报表（日/周/月/季/半年/年）
     *
     * @param period   尺度
     * @param baseDate 基准日期（为 null 则用今天）
     */
    TimeReportRespVO generateReport(ReportPeriodEnum period, LocalDate baseDate);

    /**
     * 服务运营报表分页(客户文档架构:按 timeScale 切分 statTime 区间,每个子窗口动态生成一条报表)
     *
     * - 不建专表,全部即时聚合
     * - 每条包含:救援完成率/预约成功率/投诉处理率 + 同比/环比 delta
     */
    PageResult<ServiceOpReportRespVO> pageServiceOpReport(ServiceOpReportPageReqVO reqVO);

    // ========== count-by-status 系列(给前端卡片角标用) ==========
    // 一次 GROUP BY 取所有状态,缺失状态补 0,自动带 yudao 多租户隔离

    Map<String, Long> countRescueInfoByStatus();
    Map<String, Long> countReserveListByStatus();
    Map<String, Long> countSpacePushByStatus();
    Map<String, Long> countSuggestionByStatus();
    Map<String, Long> countUserAppealByStatus();
    Map<String, Long> countDisputeMediateByStatus();

}
