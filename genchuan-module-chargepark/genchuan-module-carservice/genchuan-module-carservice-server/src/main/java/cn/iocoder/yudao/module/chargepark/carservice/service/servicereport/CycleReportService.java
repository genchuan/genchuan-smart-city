package cn.iocoder.yudao.module.chargepark.carservice.service.servicereport;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.servicereport.vo.CycleReportChartReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.servicereport.vo.CycleReportChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.servicereport.vo.CycleReportCreateReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.servicereport.vo.CycleReportDetailRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.servicereport.vo.CycleReportPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.servicereport.vo.CycleReportRespVO;
import jakarta.validation.Valid;

/**
 * 周期报表(cycle-report)Service
 *
 * 无独立数据库表,报表主体在进程内内存保存(ConcurrentHashMap),重启丢失。
 * 生成过程基于 rescue_info 等 11 张业务表即时聚合。
 */
public interface CycleReportService {

    /** 分页查询已生成的周期报表 */
    PageResult<CycleReportRespVO> pageCycleReport(CycleReportPageReqVO reqVO);

    /**
     * 仅按时间范围查询:后端扫 6 种周期(日/周/月/季/半年/年),
     * 凡是完整落在 [start, end] 内的窗口都纳入,混合返回。
     */
    PageResult<CycleReportRespVO> pageAllCycleReport(java.time.LocalDateTime statStartTime,
                                                    java.time.LocalDateTime statEndTime,
                                                    Integer pageNo, Integer pageSize);

    /** 手动触发生成一份周期报表,返回 id */
    Long createCycleReport(@Valid CycleReportCreateReqVO reqVO);

    /** 按 id 查询单条报表明细(含 detailData) */
    CycleReportDetailRespVO getCycleReport(Long id);

    /** 可视化图表聚合(卡片+折线+柱状+地图+饼) */
    CycleReportChartRespVO chartCycleReport(@Valid CycleReportChartReqVO reqVO);

    /**
     * 分页查询单维度明细数据。
     * dimension 取值:rescue / reserve / complaint / findCar / spacePush / wording
     */
    PageResult<java.util.Map<String, Object>> pageDetail(Long id, String dimension, Integer pageNo, Integer pageSize);

    /** 取某条报表的全量明细数据(不分页/不限条数,供导出使用) */
    java.util.Map<String, java.util.List<java.util.Map<String, Object>>> getFullDetailForExport(Long id);

    /** 同比分析:本期 vs 去年同期(按 statStartTime/statEndTime 平移一年) */
    java.util.Map<String, Object> compareYoY(Long id);

    /** 环比分析:本期 vs 上一等长周期 */
    java.util.Map<String, Object> compareMoM(Long id);

}
