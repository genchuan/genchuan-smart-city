package cn.iocoder.yudao.module.envirhealth.dal.mysql.roadcleaning;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.roadcleaning.RoadCleaningPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.RoadCleaningDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.HourlyCompletionDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.HourlyProgressDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.RoadCleaningDO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.LineItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 道路清扫计划 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface RoadCleaningMapper extends BaseMapperX<RoadCleaningDO> {

    default PageResult<RoadCleaningDO> selectPage(RoadCleaningPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RoadCleaningDO>()
                .eqIfPresent(RoadCleaningDO::getCleaningId, reqVO.getCleaningId())
                .eqIfPresent(RoadCleaningDO::getPlanNo, reqVO.getPlanNo())
                .eqIfPresent(RoadCleaningDO::getRoadId, reqVO.getRoadId())
                .eqIfPresent(RoadCleaningDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(RoadCleaningDO::getFrequency, reqVO.getFrequency())
                .eqIfPresent(RoadCleaningDO::getTimePeriod, reqVO.getTimePeriod())
                .eqIfPresent(RoadCleaningDO::getStaffIds, reqVO.getStaffIds())
                .eqIfPresent(RoadCleaningDO::getPlanStatusId, reqVO.getPlanStatusId())
                .eqIfPresent(RoadCleaningDO::getQualityRate, reqVO.getQualityRate())
                .eqIfPresent(RoadCleaningDO::getProblemCount, reqVO.getProblemCount())
                .eqIfPresent(RoadCleaningDO::getAttendanceRate, reqVO.getAttendanceRate())
                .eqIfPresent(RoadCleaningDO::getToolIds, reqVO.getToolIds())
                .eqIfPresent(RoadCleaningDO::getStandard, reqVO.getStandard())
                .betweenIfPresent(RoadCleaningDO::getCheckinTime, reqVO.getCheckinTime())
                .eqIfPresent(RoadCleaningDO::getProgress, reqVO.getProgress())
                .eqIfPresent(RoadCleaningDO::getOperationStatus, reqVO.getOperationStatus())
                .eqIfPresent(RoadCleaningDO::getTrackCoverage, reqVO.getTrackCoverage())
                .betweenIfPresent(RoadCleaningDO::getLastReportTime, reqVO.getLastReportTime())
                .eqIfPresent(RoadCleaningDO::getIsAbnormal, reqVO.getIsAbnormal())
                .betweenIfPresent(RoadCleaningDO::getCompleteTime, reqVO.getCompleteTime())
                .eqIfPresent(RoadCleaningDO::getCheckPhotoUrl, reqVO.getCheckPhotoUrl())
                .eqIfPresent(RoadCleaningDO::getReviewStatus, reqVO.getReviewStatus())
                .eqIfPresent(RoadCleaningDO::getReviewBy, reqVO.getReviewBy())
                .betweenIfPresent(RoadCleaningDO::getReviewTime, reqVO.getReviewTime())
                .eqIfPresent(RoadCleaningDO::getReformRequire, reqVO.getReformRequire())
                .eqIfPresent(RoadCleaningDO::getReviewDesc, reqVO.getReviewDesc())
                .betweenIfPresent(RoadCleaningDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RoadCleaningDO::getId));
    }

    /**
     * 查询指定日期的最大序号（用于plan_no）
     */
    @Select("SELECT IFNULL(MAX(RIGHT(plan_no, 3)), 0) FROM road_cleaning WHERE plan_no LIKE CONCAT('RC', #{dateStr}, '%')")
    Integer selectMaxSeqByDate(@Param("dateStr") String dateStr);

    /**
     * 查询全局最大序号（用于cleaning_id）
     */
    @Select("SELECT IFNULL(MAX(SUBSTRING_INDEX(cleaning_id, '-', -1)), 0) FROM road_cleaning")
    Integer selectMaxSeq();

    List<RoadCleaningDetailDO> selectDetailPage(@Param("reqVO") RoadCleaningPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") RoadCleaningPageReqVO pageReqVO);

    /** 总计划数 */
    @Select("SELECT COUNT(1) FROM road_cleaning r WHERE r.deleted = 0")
    Long countTotalPlan();

    /** 执行中计划数 */
    @Select("""
            SELECT COUNT(1) FROM road_cleaning r
            WHERE r.deleted = 0 AND r.plan_status_id = 'uuid-plan-status-002'
            """)
    Long countExecutingPlan();

    /** 质量达标数 */
    @Select("""
            SELECT COUNT(1) FROM road_cleaning r
            WHERE r.deleted = 0 AND r.review_status = '达标'
            """)
    Long countQualityQualified();

    /** 质量待核查数 */
    @Select("""
            SELECT COUNT(1) FROM road_cleaning r
            WHERE r.deleted = 0 AND r.review_status <> '达标'
            """)
    Long selectPendingReviewCount();

    /** 全勤人员数：执行中计划中涉及人员去重 */
    @Select("""
            SELECT CAST(r.staff_ids AS CHAR)
            FROM road_cleaning r
            WHERE r.deleted = 0 AND r.plan_status_id = 'uuid-plan-status-002'
            """)
    List<String> selectExecutingStaffIdsJson();

    /** 计划状态占比 */
    @Select("""
            SELECT
              COALESCE(NULLIF(TRIM(p.name), ''), '未知') AS name,
              COUNT(1) AS value
            FROM road_cleaning r
                LEFT JOIN sys_plan_status p ON r.plan_status_id = p.sys_plan_status_id
            WHERE r.deleted = 0
            GROUP BY COALESCE(NULLIF(TRIM(p.name), ''), '未知')
            ORDER BY value DESC
            """)
    List<PieItemVO> selectPlanStatusPie();

    /** 路段类型占比 */
    @Select("""
            SELECT
              COALESCE(NULLIF(TRIM(ro.road_name), ''), '未知') AS name,
              COUNT(1) AS value
            FROM road_cleaning r
                LEFT JOIN sys_road ro ON r.road_id = ro.road_id
            WHERE r.deleted = 0
            GROUP BY COALESCE(NULLIF(TRIM(ro.road_name), ''), '未知')
            ORDER BY value DESC
            """)
    List<PieItemVO> selectRoadSectionTypePie();

    /** 区域质量达标率 */
    @Select("""
            SELECT
              COALESCE(NULLIF(TRIM(a.area_name), ''), '未知') AS name,
              ROUND(100 * SUM(CASE WHEN r.review_status = '达标' THEN 1 ELSE 0 END) / NULLIF(COUNT(1), 0), 2) AS value
            FROM road_cleaning r
            LEFT JOIN sys_area a ON a.area_code = r.area_code
            WHERE r.deleted = 0
            GROUP BY COALESCE(NULLIF(TRIM(a.area_name), ''), '未知')
            ORDER BY value DESC
            """)
    List<BarItemVO> selectQualityQualifiedRateByArea();

    /**
     * 待执行计划总数
     */
    @Select("SELECT COUNT(1) FROM road_cleaning r " +
            "WHERE r.deleted = 0 AND r.plan_status_id = 'uuid-plan-status-001'")
    Long countPendingPlan();

    /**
     * 待执行涉及区域数
     */
    @Select("SELECT COUNT(DISTINCT r.area_code) FROM road_cleaning r " +
            "WHERE r.deleted = 0 AND r.plan_status_id = 'uuid-plan-status-001'")
    Long countPendingArea();

    /**
     * 待执行涉及人员ID列表
     */
    @Select("SELECT r.staff_ids FROM road_cleaning r " +
            "WHERE r.deleted = 0 AND r.plan_status_id = 'uuid-plan-status-001' " +
            "AND r.staff_ids IS NOT NULL AND r.staff_ids != '' AND r.staff_ids != '[]'")
    List<String> selectPendingStaffIds();

    /**
     * 待执行计划-清扫频次分布
     */
    @Select("SELECT " +
            "COALESCE(NULLIF(TRIM(r.frequency), ''), '未知') AS name, " +
            "COUNT(1) AS value " +
            "FROM road_cleaning r " +
            "WHERE r.deleted = 0 AND r.plan_status_id = 'uuid-plan-status-001' " +
            "GROUP BY COALESCE(NULLIF(TRIM(r.frequency), ''), '未知') " +
            "ORDER BY value DESC")
    List<PieItemVO> selectPendingFrequencyDistribution();

    /**
     * 待执行计划-路段类型占比
     */
    @Select("SELECT " +
            "COALESCE(NULLIF(TRIM(ro.road_name), ''), '未知') AS name, " +
            "COUNT(1) AS value " +
            "FROM road_cleaning r " +
            "LEFT JOIN sys_road ro ON r.road_id = ro.road_id " +
            "WHERE r.deleted = 0 AND r.plan_status_id = 'uuid-plan-status-001' " +
            "GROUP BY COALESCE(NULLIF(TRIM(ro.road_name), ''), '未知') " +
            "ORDER BY value DESC")
    List<PieItemVO> selectPendingRoadSectionTypeDistribution();

    /**
     * 待执行计划-不同时段数量对比（按时间段类型分组）
     */
    @Select("SELECT " +
            "time_period_name AS name, " +
            "COUNT(1) AS value " +
            "FROM ( " +
            "  SELECT " +
            "    CASE " +
            "      WHEN r.time_period REGEXP '^(0[0-5]|06):' THEN '凌晨' " + //(00:00-06:00)
            "      WHEN r.time_period REGEXP '^(0[6-9]|1[0-1]):' AND SPLIT_PART(r.time_period, '-', 1) < '12:00' THEN '上午' " + //(06:00-12:00)
            "      WHEN r.time_period REGEXP '^(1[2-7]):' AND SPLIT_PART(r.time_period, '-', 1) < '18:00' THEN '下午' " +  //(12:00-18:00)
            "      WHEN r.time_period REGEXP '^(1[8-9]|2[0-3]):' THEN '晚上' " +  //(18:00-24:00)
            "      ELSE '其他' " +
            "    END AS time_period_name " +
            "  FROM road_cleaning r " +
            "  WHERE r.deleted = 0 AND r.plan_status_id = 'uuid-plan-status-001' " +
            ") AS sub_query " +
            "GROUP BY time_period_name " +
            "ORDER BY " +
            "  CASE time_period_name " +
            "    WHEN '凌晨' THEN 1 " +
            "    WHEN '上午' THEN 2 " +
            "    WHEN '下午' THEN 3 " +
            "    WHEN '晚上' THEN 4 " +
            "    ELSE 5 " +
            "  END")
    List<BarItemVO> selectPendingPlanCountByTimePeriod();

    /**
     * 当前作业任务数（执行中的计划数量）
     */
    @Select("SELECT COUNT(1) FROM road_cleaning r " +
            "WHERE r.deleted = 0 AND r.plan_status_id = 'uuid-plan-status-002'")
    Long countCurrentTask();

    /**
     * 正常运行数（执行中且无异常）
     */
    @Select("SELECT COUNT(1) FROM road_cleaning r " +
            "WHERE r.deleted = 0 AND r.plan_status_id = 'uuid-plan-status-002' " +
            "AND (r.is_abnormal IS NULL OR r.operation_status = '运行')")
    Long countNormalRunning();

    /**
     * 异常标记数（执行中有异常）
     */
    @Select("SELECT COUNT(1) FROM road_cleaning r " +
            "WHERE r.deleted = 0 AND r.plan_status_id = 'uuid-plan-status-002' " +
            "AND (r.is_abnormal = 1 OR r.operation_status = '异常' OR r.operation_status = '暂停')")
    Long countAbnormal();

    /**
     * 获取当日每小时的道路清扫进度数据
     */
    @Select("SELECT " +
            "HOUR(r.last_report_time) AS hourPoint, " +
            "ROUND(AVG(r.progress), 2) AS avgProgress " +
            "FROM road_cleaning r " +
            "WHERE r.deleted = 0 " +
            "AND DATE(r.last_report_time) = CURDATE() " +
            "AND r.progress IS NOT NULL " +
            "GROUP BY HOUR(r.last_report_time) " +
            "ORDER BY hourPoint")
    List<HourlyProgressDO> selectHourlyProgress();

    /**
     * 获取当天各小时的完成率（基于已完成路段数）
     */
    @Select("SELECT " +
            "HOUR(r.complete_time) AS hourPoint, " +
            "COUNT(1) AS completedCount " +
            "FROM road_cleaning r " +
            "WHERE r.deleted = 0 " +
            "AND DATE(r.complete_time) = CURDATE() " +
            "AND r.complete_time IS NOT NULL " +
            "AND r.plan_status_id = 'uuid-plan-status-003' " + // 假设003是已完成状态
            "GROUP BY HOUR(r.complete_time) " +
            "ORDER BY hourPoint")
    List<HourlyCompletionDO> selectHourlyCompletion();

    /**
     * 获取当天总计划数
     */
    @Select("SELECT COUNT(1) FROM road_cleaning r " +
            "WHERE r.deleted = 0 " +
            "AND DATE(r.create_time) <= CURDATE() ")
    Long countTodayTotalPlans();

    /**
     * 统计待核查任务数
     */
    @Select("SELECT COUNT(*) FROM road_cleaning WHERE deleted = 0 AND review_status = '待核查'")
    Long countPendingCheck();

    /**
     * 统计已达标数
     */
    @Select("SELECT COUNT(*) FROM road_cleaning WHERE deleted = 0 AND review_status = '达标'")
    Long countQualified();

    /**
     * 统计需整改数
     */
    @Select("SELECT COUNT(*) FROM road_cleaning WHERE deleted = 0 AND review_status = '不达标'")
    Long countNeedReform();

    /**
     * 查询核查结果分布（待核查/达标/不达标）
     */
    @Select("SELECT review_status as name, COUNT(*) as value FROM road_cleaning " +
            "WHERE deleted = 0 AND review_status IS NOT NULL AND review_status != '' " +
            "GROUP BY review_status")
    List<PieItemVO> selectCheckResultDistribution();

    /**
     * 查询区域分布占比
     * 关联区域表获取区域名称
     */
    @Select("SELECT sa.area_name as name, COUNT(rc.id) as value " +
            "FROM road_cleaning rc " +
            "LEFT JOIN sys_area sa ON rc.area_code = sa.area_code " +
            "WHERE rc.deleted = 0 " +
            "GROUP BY rc.area_code, sa.area_name")
    List<PieItemVO> selectAreaDistribution();

    /**
     * 查询各区域质量达标率
     * 计算每个区域的平均质量达标率
     */
    @Select("SELECT sa.area_name as name, ROUND(AVG(rc.quality_rate), 2) as value " +
            "FROM road_cleaning rc " +
            "LEFT JOIN sys_area sa ON rc.area_code = sa.area_code " +
            "WHERE rc.deleted = 0 AND rc.quality_rate IS NOT NULL " +
            "GROUP BY rc.area_code, sa.area_name " +
            "ORDER BY value DESC")
    List<BarItemVO> selectAreaQualityRates();

    /**
     * 统计已完成任务总数
     */
    @Select("SELECT COUNT(*) FROM road_cleaning WHERE deleted = 0 AND plan_status_id = 'uuid-plan-status-003' " )
    Long countCompletedTasks();

    /**
     * 统计总清扫里程
     */
    @Select("SELECT SUM(sr.length) FROM road_cleaning rc " +
            "LEFT JOIN sys_road sr ON rc.road_id = sr.road_id " +
            "WHERE rc.deleted = 0 AND rc.plan_status_id = 'uuid-plan-status-003' ")
    Double sumCleaningMileage();

    /**
     * 计算平均质量达标率
     */
    @Select("SELECT ROUND(AVG(quality_rate), 2) FROM road_cleaning " +
            "WHERE deleted = 0 AND plan_status_id = 'uuid-plan-status-003' " +
            "AND quality_rate IS NOT NULL ")
    Double avgQualityRate();

    /**
     * 计算问题处置及时率
     */
    @Select("SELECT ROUND(LEAST(SUM(CASE WHEN timely_handled_count <= problem_count THEN timely_handled_count ELSE problem_count END) * 100.0 / NULLIF(SUM(problem_count), 0), 100), 2) " +
            "FROM road_cleaning " +
            "WHERE deleted = 0 AND plan_status_id = 'uuid-plan-status-003' " +
            "AND problem_count > 0 ")
    Double calculateProblemHandleRate();

    /**
     * 查询质量达标率趋势
     */
    @Select("SELECT DATE_FORMAT(complete_time, '%Y-%m-%d') AS timePoint, " +
            "ROUND(AVG(quality_rate), 2) AS value " +
            "FROM road_cleaning " +
            "WHERE deleted = 0 AND plan_status_id = 'uuid-plan-status-003' " +
            "AND quality_rate IS NOT NULL " +
            "GROUP BY DATE_FORMAT(complete_time, '%Y-%m-%d') " +
            "ORDER BY DATE_FORMAT(complete_time, '%Y-%m-%d')")
    List<LineItemVO> selectQualityRateTrend();

    /**
     * 查询按日任务完成量对比
     */
    @Select("SELECT DATE_FORMAT(complete_time, '%m-%d') AS name, COUNT(*) AS value, " +
            "MIN(DATE(complete_time)) AS sort_date " +
            "FROM road_cleaning " +
            "WHERE deleted = 0 AND plan_status_id = 'uuid-plan-status-003' " +
            "AND complete_time BETWEEN #{startTime} AND #{endTime} " +
            "GROUP BY DATE_FORMAT(complete_time, '%m-%d') " +
            "ORDER BY sort_date")
    List<BarItemVO> selectDailyTaskCompletion(@Param("startTime") LocalDateTime startTime,
                                              @Param("endTime") LocalDateTime endTime);

    /**
     * 查询按周任务完成量对比
     */
    @Select("SELECT CONCAT(t.year, '年第', t.week, '周') AS name, t.value " +
            "FROM ( " +
            "  SELECT YEAR(complete_time) AS year, WEEK(complete_time) AS week, COUNT(*) AS value " +
            "  FROM road_cleaning " +
            "  WHERE deleted = 0 AND plan_status_id = 'uuid-plan-status-003' " +
            "  AND complete_time BETWEEN #{startTime} AND #{endTime} " +
            "  AND complete_time IS NOT NULL " +
            "  GROUP BY YEAR(complete_time), WEEK(complete_time) " +
            ") AS t " +
            "ORDER BY t.year, t.week")
    List<BarItemVO> selectWeeklyTaskCompletion(@Param("startTime") LocalDateTime startTime,
                                               @Param("endTime") LocalDateTime endTime);
    /**
     * 查询按月任务完成量对比
     */
    @Select("SELECT DATE_FORMAT(complete_time, '%Y-%m') AS name, COUNT(*) AS value " +
            "FROM road_cleaning " +
            "WHERE deleted = 0 AND plan_status_id = 'uuid-plan-status-003' " +
            "AND complete_time BETWEEN #{startTime} AND #{endTime} " +
            "GROUP BY DATE_FORMAT(complete_time, '%Y-%m') " +
            "ORDER BY DATE_FORMAT(complete_time, '%Y-%m')")
    List<BarItemVO> selectMonthlyTaskCompletion(@Param("startTime") LocalDateTime startTime,
                                                @Param("endTime") LocalDateTime endTime);

    /**
     * 查询各区域完成量占比
     */
    @Select("SELECT COALESCE(sa.area_name, rc.area_code) AS name, COUNT(rc.id) AS value " +
            "FROM road_cleaning rc " +
            "LEFT JOIN sys_area sa ON rc.area_code = sa.area_code " +
            "WHERE rc.deleted = 0 AND rc.plan_status_id = 'uuid-plan-status-003' " +
            "GROUP BY rc.area_code, sa.area_name")
    List<PieItemVO> selectAreaCompletionDistribution();

    /**
     * 查询各人员作业量占比
     */
    @Select("SELECT su.user_name AS name, COUNT(rc.id) AS value " +
            "FROM road_cleaning rc " +
            "LEFT JOIN sys_user su ON JSON_CONTAINS(rc.staff_ids, CONCAT('\"', su.user_id, '\"')) " +
            "WHERE rc.deleted = 0  " +
            "AND su.user_name IS NOT NULL " +
            "GROUP BY su.user_id, su.user_name " +
            "ORDER BY value DESC")
    List<PieItemVO> selectStaffWorkloadDistribution();

    /**
     * 按计划状态统计
     */
    @Select("SELECT " +
            "    CASE " +
            "        WHEN rc.plan_status_id IN ('uuid-plan-status-001') THEN '清扫待执行' " +
            "        WHEN rc.plan_status_id IN ('uuid-plan-status-002') THEN '作业进行中' " +
            "        WHEN rc.plan_status_id = 'uuid-plan-status-003' THEN '已完成' " +
            "        ELSE COALESCE(sps.name, '未设置') " +
            "    END as status_name, " +
            "    COUNT(rc.id) as count " +
            "FROM road_cleaning rc " +
            "LEFT JOIN sys_plan_status sps ON rc.plan_status_id = sps.sys_plan_status_id " +
            "WHERE rc.deleted = 0 " +
            "GROUP BY " +
            "    CASE " +
            "        WHEN rc.plan_status_id IN ('uuid-plan-status-001') THEN '清扫待执行' " +
            "        WHEN rc.plan_status_id IN ('uuid-plan-status-002') THEN '作业进行中' " +
            "        WHEN rc.plan_status_id = 'uuid-plan-status-003' THEN '已完成' " +
            "        ELSE COALESCE(sps.name, '未设置') " +
            "    END")
    List<Map<String, Object>> selectStatisticsByPlanStatus();
}