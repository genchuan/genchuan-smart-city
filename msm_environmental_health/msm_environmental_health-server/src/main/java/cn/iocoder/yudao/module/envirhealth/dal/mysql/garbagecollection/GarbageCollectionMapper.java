package cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagecollection;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.GarbageCollectionPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.card.all.GarbageCollectionCardAllVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.circle.all.GarbageCollectionCircleAllVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.column.all.AreaCompletionRateColumnAllVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.column.pending.TimePeriodPendingColumnVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.trend.executing.GarbageCollectionDailyTrendVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.GarbageCollectionDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.detail.GarbageCollectionDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 收运计划 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface GarbageCollectionMapper extends BaseMapperX<GarbageCollectionDO> {

    default PageResult<GarbageCollectionDO> selectPage(GarbageCollectionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GarbageCollectionDO>()
                .eqIfPresent(GarbageCollectionDO::getCollectionId, reqVO.getCollectionId())
                .eqIfPresent(GarbageCollectionDO::getPlanNo, reqVO.getPlanNo())
                .eqIfPresent(GarbageCollectionDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(GarbageCollectionDO::getGarbageTypeId, reqVO.getGarbageTypeId())
                .eqIfPresent(GarbageCollectionDO::getFrequency, reqVO.getFrequency())
                .eqIfPresent(GarbageCollectionDO::getTimePeriod, reqVO.getTimePeriod())
                .eqIfPresent(GarbageCollectionDO::getVehicleId, reqVO.getVehicleId())
                .eqIfPresent(GarbageCollectionDO::getStaffIds, reqVO.getStaffIds())
                .eqIfPresent(GarbageCollectionDO::getPointIds, reqVO.getPointIds())
                .eqIfPresent(GarbageCollectionDO::getPlanStatusId, reqVO.getPlanStatusId())
                .eqIfPresent(GarbageCollectionDO::getCompletionRate, reqVO.getCompletionRate())
                .eqIfPresent(GarbageCollectionDO::getAbnormalCount, reqVO.getAbnormalCount())
                .betweenIfPresent(GarbageCollectionDO::getAbnormalCreateTime, reqVO.getAbnormalCreateTime())
                .betweenIfPresent(GarbageCollectionDO::getAbnormalUpdateTime, reqVO.getAbnormalUpdateTime())
                .eqIfPresent(GarbageCollectionDO::getCreateBy, reqVO.getCreateBy())
                .eqIfPresent(GarbageCollectionDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(GarbageCollectionDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(GarbageCollectionDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(GarbageCollectionDO::getExtCommon4, reqVO.getExtCommon4())
                .eqIfPresent(GarbageCollectionDO::getProgress, reqVO.getProgress())
                .eqIfPresent(GarbageCollectionDO::getCollectedVolume, reqVO.getCollectedVolume())
                .eqIfPresent(GarbageCollectionDO::getCheckinStatus, reqVO.getCheckinStatus())
                .eqIfPresent(GarbageCollectionDO::getTrackCoverage, reqVO.getTrackCoverage())
                .betweenIfPresent(GarbageCollectionDO::getLastReportTime, reqVO.getLastReportTime())
                .eqIfPresent(GarbageCollectionDO::getIsAbnormal, reqVO.getIsAbnormal())
                .betweenIfPresent(GarbageCollectionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GarbageCollectionDO::getId));
    }

    /**
     * 查询指定日期的最大序号（用于plan_no）
     */
    @Select("SELECT IFNULL(MAX(RIGHT(plan_no, 3)), 0) FROM garbage_collection WHERE plan_no LIKE CONCAT('GC', #{dateStr}, '%')")
    Integer selectMaxSeqByDate(@Param("dateStr") String dateStr);

    /**
     * 查询全局最大序号（用于collection_id）
     */
    @Select("SELECT IFNULL(MAX(SUBSTRING_INDEX(collection_id, '-', -1)), 0) FROM garbage_collection")
    Integer selectMaxSeq();


    /**
     * 联表查询垃圾收运详情列表(分页)
     * @param pageReqVO 分页查询参数
     * @return 详情列表
     */
    List<GarbageCollectionDetailDO> selectDetailPage(@Param("reqVO") GarbageCollectionPageReqVO pageReqVO);

    /**
     * 查询主表总数(分页)
     * @param pageReqVO 查询参数
     * @return 总数
     */
    Long selectCount(@Param("reqVO") GarbageCollectionPageReqVO pageReqVO);

    /**
     * 统计收运计划各状态数量
     * @return 统计结果
     */
    @Select("SELECT " +
            "COUNT(*) AS total_count, " +
            "SUM(CASE WHEN plan_status_id = 'uuid-plan-status-002' THEN 1 ELSE 0 END) AS executing_count, " +
            "SUM(CASE WHEN plan_status_id = 'uuid-plan-status-003' THEN 1 ELSE 0 END) AS completed_count, " +
            "SUM(CASE WHEN plan_status_id = 'uuid-plan-status-004' THEN 1 ELSE 0 END) AS abnormal_count " +
            "FROM garbage_collection " +
            "WHERE deleted = 0")
    GarbageCollectionCardAllVO selectCardAll();

    /**
     * 统计收运品类占比（按垃圾类型ID分组，统计计划数量）
     * 联表：garbage_collection -> sys_garbage_type（业务主键UUID关联）
     */
    @Select("SELECT " +
            "IFNULL(g.name, '未知品类') AS name, " +
            "COUNT(gc.id) AS value, " +
            "ROUND(COUNT(gc.id) * 100.0 / (SELECT COUNT(*) FROM garbage_collection WHERE deleted = 0), 2) AS proportion " +
            "FROM garbage_collection gc " +
            "LEFT JOIN sys_garbage_type g ON gc.garbage_type_id = g.sys_garbage_type_id " +
            "WHERE gc.deleted = 0 " +
            "GROUP BY gc.garbage_type_id, g.name " +
            "ORDER BY value DESC")
    List<GarbageCollectionCircleAllVO> selectGarbageTypeCircleAll();

    /**
     * 统计计划状态占比（按计划状态ID分组，统计计划数量）
     * 联表：garbage_collection -> sys_plan_status（业务主键UUID关联）
     */
    @Select("SELECT " +
            "IFNULL(s.name, '未知状态') AS name, " +
            "COUNT(gc.id) AS value, " +
            "ROUND(COUNT(gc.id) * 100.0 / (SELECT COUNT(*) FROM garbage_collection WHERE deleted = 0), 2) AS proportion " +
            "FROM garbage_collection gc " +
            "LEFT JOIN sys_plan_status s ON gc.plan_status_id = s.sys_plan_status_id " +
            "WHERE gc.deleted = 0 " +
            "GROUP BY gc.plan_status_id, s.name " +
            "ORDER BY value DESC")
    List<GarbageCollectionCircleAllVO> selectPlanStatusCircleAll();

    /**
     * 统计区域分布占比（按区域编码分组，统计计划数量）
     * 联表：garbage_collection -> sys_area（区域编码关联）
     */
    @Select("SELECT " +
            "IFNULL(a.area_name, '未知区域') AS name, " +
            "COUNT(gc.id) AS value, " +
            "ROUND(COUNT(gc.id) * 100.0 / (SELECT COUNT(*) FROM garbage_collection WHERE deleted = 0), 2) AS proportion " +
            "FROM garbage_collection gc " +
            "LEFT JOIN sys_area a ON gc.area_code = a.area_code " +
            "WHERE gc.deleted = 0 " +
            "GROUP BY gc.area_code, a.area_name " +
            "ORDER BY value DESC")
    List<GarbageCollectionCircleAllVO> selectAreaDistributionCircleAll();

    /**
     * 统计各区域收运计划平均完成率（柱状图）
     */
    @Select("SELECT " +
            "IFNULL(a.area_name, '未知区域') AS areaName, " +
            "ROUND(AVG(gc.completion_rate), 2) AS completionRate " +
            "FROM garbage_collection gc " +
            "LEFT JOIN sys_area a ON gc.area_code = a.area_code " +
            "WHERE gc.deleted = 0 " +
            "GROUP BY gc.area_code, a.area_name " +
            "ORDER BY completionRate DESC")
    List<AreaCompletionRateColumnAllVO> selectAreaCompletionRateColumnAll();

    /**
     * 查询待执行计划总数
     */
    @Select("SELECT COUNT(id) FROM garbage_collection WHERE deleted = 0 AND plan_status_id = 'uuid-plan-status-001'")
    Long selectPendingTotalCount();

    /**
     * 按区域统计待执行计划数量（返回Map：区域名称 -> 数量）
     */
    @Select("SELECT IFNULL(a.area_name, '未知区域') AS areaName, COUNT(gc.id) AS count " +
            "FROM garbage_collection gc " +
            "LEFT JOIN sys_area a ON gc.area_code = a.area_code " +
            "WHERE gc.deleted = 0 AND gc.plan_status_id = 'uuid-plan-status-001' " +
            "GROUP BY gc.area_code, a.area_name")
    List<Map<String, Object>> selectPendingCountByArea();

    /**
     * 按品类统计待执行计划数量（返回Map：品类名称 -> 数量）
     */
    @Select("SELECT IFNULL(g.name, '未知品类') AS garbageTypeName, COUNT(gc.id) AS count " +
            "FROM garbage_collection gc " +
            "LEFT JOIN sys_garbage_type g ON gc.garbage_type_id = g.sys_garbage_type_id " +
            "WHERE gc.deleted = 0 AND gc.plan_status_id = 'uuid-plan-status-001' " +
            "GROUP BY gc.garbage_type_id, g.name")
    List<Map<String, Object>> selectPendingCountByGarbageType();

    /**
     * 统计待执行计划-按区域分组（待执行）
     */
    @Select("SELECT " +
            "IFNULL(a.area_name, '未知区域') AS name, " +
            "COUNT(gc.id) AS value, " +
            "CASE WHEN total.total_count = 0 THEN 0.00 " +
            "     ELSE ROUND(COUNT(gc.id) * 100.0 / total.total_count, 2) " +
            "END AS proportion " +
            "FROM garbage_collection gc " +
            "LEFT JOIN sys_area a ON gc.area_code = a.area_code " +
            "CROSS JOIN (SELECT COUNT(*) AS total_count FROM garbage_collection WHERE deleted = 0 AND plan_status_id = 'uuid-plan-status-001') AS total " +
            "WHERE gc.deleted = 0 " +
            "AND gc.plan_status_id = 'uuid-plan-status-001' " +
            "GROUP BY gc.area_code, a.area_name, total.total_count " +
            "ORDER BY value DESC")
    List<GarbageCollectionCircleAllVO> selectPendingByArea();

    /**
     * 统计待执行计划-按品类分组
     */
    @Select("SELECT " +
            "IFNULL(g.name, '未知品类') AS name, " +
            "COUNT(gc.id) AS value, " +
            "CASE WHEN total.total_count = 0 THEN 0.00 " +
            "     ELSE ROUND(COUNT(gc.id) * 100.0 / total.total_count, 2) " +
            "END AS proportion " +
            "FROM garbage_collection gc " +
            "LEFT JOIN sys_garbage_type g ON gc.garbage_type_id = g.sys_garbage_type_id " +
            "CROSS JOIN (SELECT COUNT(*) AS total_count FROM garbage_collection WHERE deleted = 0 AND plan_status_id = 'uuid-plan-status-001') AS total " +
            "WHERE gc.deleted = 0 " +
            "AND gc.plan_status_id = 'uuid-plan-status-001' " +
            "GROUP BY gc.garbage_type_id, g.name, total.total_count " +
            "ORDER BY value DESC")
    List<GarbageCollectionCircleAllVO> selectPendingByGarbageType();

    /**
     * 按时段统计待执行计划数量（柱状图）
     */
    @Select("SELECT " +
            "IFNULL(gc.time_period, '未知时段') AS timePeriod, " +
            "COUNT(gc.id) AS count " +
            "FROM garbage_collection gc " +
            "WHERE gc.deleted = 0 " +
            "AND gc.plan_status_id = 'uuid-plan-status-001' " + // 待执行状态
            "GROUP BY gc.time_period " +
            "ORDER BY count DESC")
    List<TimePeriodPendingColumnVO> selectTimePeriodPendingColumn();

    /**
     * 获取收运计划统计数据（按状态分组）
     * @return 状态统计列表
     */
    @Select("SELECT " +
            "CASE " +
            "   WHEN gc.plan_status_id = 'uuid-plan-status-001' THEN '待执行' " +
            "   WHEN gc.plan_status_id = 'uuid-plan-status-002' THEN '执行中' " +
            "   WHEN gc.plan_status_id = 'uuid-plan-status-003' THEN '已完成' " +
            "   WHEN gc.plan_status_id = 'uuid-plan-status-004' THEN '异常' " +
            "   WHEN gc.plan_status_id = 'uuid-plan-status-007' THEN '待复核' " +
            "   ELSE '其他' " +
            "END AS status_name, " +
            "COUNT(*) AS count " +
            "FROM garbage_collection gc " +
            "WHERE gc.deleted = 0 " +
            "GROUP BY gc.plan_status_id")
    List<Map<String, Object>> selectStatisticsByPlanStatus();

    /**
     * 获取收运计划总数量
     * @return 总数量
     */
    @Select("SELECT COUNT(*) FROM garbage_collection WHERE deleted = 0")
    Long selectTotalCount();

    /**
     * 获取卡片统计数据（当前作业任务数、正常运行数、异常标记数）
     * @return 统计结果Map
     */
    @Select("SELECT " +
            "SUM(CASE WHEN plan_status_id = 'uuid-plan-status-002' THEN 1 ELSE 0 END) AS normal_running_count, " +
            "SUM(CASE WHEN plan_status_id = 'uuid-plan-status-004' THEN 1 ELSE 0 END) AS abnormal_count " +
            "FROM garbage_collection " +
            "WHERE deleted = 0")
    Map<String, Object> selectCardExecuting();

    /**
     * 查询当日收运量实时增长趋势（按小时分组）
     * @return 趋势数据列表
     */
    @Select("SELECT " +
            "DATE_FORMAT(create_time, '%H:00') AS timePoint, " +
            "SUM(collected_volume) AS collectedVolume, " +
            "@cumulative := @cumulative + SUM(collected_volume) AS cumulativeVolume " +
            "FROM garbage_collection, (SELECT @cumulative := 0) AS temp " +
            "WHERE deleted = 0 " +
            "AND DATE(create_time) = CURDATE() " + // 仅查询当日数据
            "GROUP BY DATE_FORMAT(create_time, '%H:00') " + // 按小时分组
            "ORDER BY timePoint ASC")
    List<GarbageCollectionDailyTrendVO> selectDailyCollectionVolumeTrend();
}