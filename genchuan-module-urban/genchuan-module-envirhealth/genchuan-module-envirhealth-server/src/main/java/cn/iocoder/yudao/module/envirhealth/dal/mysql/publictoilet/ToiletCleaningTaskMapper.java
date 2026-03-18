package cn.iocoder.yudao.module.envirhealth.dal.mysql.publictoilet;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcleaningtask.ToiletCleaningTaskPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcleaningtask.ToiletCleaningTaskWithJoinRespVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletCleaningTaskDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.detail.ToiletCleaningTaskDetailDO;
import cn.iocoder.yudao.module.envirhealth.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.util.vo.PieItemVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 公厕保洁任务 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ToiletCleaningTaskMapper extends BaseMapperX<ToiletCleaningTaskDO> {

    default PageResult<ToiletCleaningTaskDO> selectPage(ToiletCleaningTaskPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ToiletCleaningTaskDO>()
                .betweenIfPresent(ToiletCleaningTaskDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ToiletCleaningTaskDO::getToiletId, reqVO.getToiletId())
                .eqIfPresent(ToiletCleaningTaskDO::getTaskNo, reqVO.getTaskNo())
                .eqIfPresent(ToiletCleaningTaskDO::getCleaningFrequency, reqVO.getCleaningFrequency())
                .betweenIfPresent(ToiletCleaningTaskDO::getCleaningTime, reqVO.getCleaningTime())
                .eqIfPresent(ToiletCleaningTaskDO::getCleaningContent, reqVO.getCleaningContent())
                .eqIfPresent(ToiletCleaningTaskDO::getCleaningStandard, reqVO.getCleaningStandard())
                .eqIfPresent(ToiletCleaningTaskDO::getCleanerIds, reqVO.getCleanerIds())
                .eqIfPresent(ToiletCleaningTaskDO::getPlanStatusId, reqVO.getPlanStatusId())
                .eqIfPresent(ToiletCleaningTaskDO::getCompletionRate, reqVO.getCompletionRate())
                .eqIfPresent(ToiletCleaningTaskDO::getIsAbnormal, reqVO.getIsAbnormal())
                .eqIfPresent(ToiletCleaningTaskDO::getAbnormalDesc, reqVO.getAbnormalDesc())
                .eqIfPresent(ToiletCleaningTaskDO::getProofUrls, reqVO.getProofUrls())
                // 新增字段查询条件
                .betweenIfPresent(ToiletCleaningTaskDO::getCompleteTime, reqVO.getCompleteTime())
                .eqIfPresent(ToiletCleaningTaskDO::getHandleResult, reqVO.getHandleResult())
                .betweenIfPresent(ToiletCleaningTaskDO::getHandleDuration, reqVO.getHandleDuration())
                .eqIfPresent(ToiletCleaningTaskDO::getSatisfaction, reqVO.getSatisfaction())
                .eqIfPresent(ToiletCleaningTaskDO::getStatPeriod, reqVO.getStatPeriod())
                .eqIfPresent(ToiletCleaningTaskDO::getReviewDesc, reqVO.getReviewDesc())
                .orderByDesc(ToiletCleaningTaskDO::getId));
    }

    /**
     * 查询指定日期的最大序号（用于task_no）
     */
    @Select("SELECT IFNULL(MAX(RIGHT(task_no, 3)), 0) FROM public_toilet_cleaning_task WHERE task_no LIKE CONCAT('PTCT', #{dateStr}, '%')")
    Integer selectMaxSeqByDate(@Param("dateStr") String dateStr);

    List<ToiletCleaningTaskDetailDO> selectDetailPage(@Param("reqVO") ToiletCleaningTaskPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") ToiletCleaningTaskPageReqVO pageReqVO);


    /**
     * 按计划状态统计
     */
    @Select("SELECT " +
            "    CASE " +
            "        WHEN ptct.plan_status_id IN ('uuid-plan-status-001', 'uuid-plan-status-002') THEN '保洁待执行' " +
            "        WHEN ptct.plan_status_id = 'uuid-plan-status-003' THEN '已完成' " +
            "        ELSE COALESCE(sps.name, '未设置') " +
            "    END as status_name, " +
            "    COUNT(ptct.id) as count " +
            "FROM public_toilet_cleaning_task ptct " +
            "LEFT JOIN sys_plan_status sps ON ptct.plan_status_id = sps.sys_plan_status_id " +
            "WHERE ptct.deleted = 0 " +
            "GROUP BY " +
            "    CASE " +
            "        WHEN ptct.plan_status_id IN ('uuid-plan-status-001', 'uuid-plan-status-002') THEN '保洁待执行' " +
            "        WHEN ptct.plan_status_id = 'uuid-plan-status-003' THEN '已完成' " +
            "        ELSE COALESCE(sps.name, '未设置') " +
            "    END")
    List<Map<String, Object>> selectStatisticsByPlanStatus();

    /**
     * 待执行的计划数量
     */
    @Select("""
            SELECT COUNT(1)
            FROM public_toilet_cleaning_task t
            WHERE t.deleted = b'0'
              AND t.plan_status_id = 'uuid-plan-status-001'
            """)
    Long countPending();

    /**
     * 按区域待执行数
     */
    @Select("""
            SELECT COUNT(DISTINCT COALESCE(NULLIF(TRIM(a.area_name), ''), '未知'))
            FROM public_toilet_cleaning_task task
            LEFT JOIN public_toilet pt ON pt.toilet_id = task.toilet_id
            LEFT JOIN sys_area a ON a.area_code = pt.area_code
            WHERE task.deleted = b'0'
              AND task.plan_status_id = 'uuid-plan-status-001'
            """)
    Long countPendingAreaNum();

    /**
     * 按按人员分配数待执行数
     */
    @Select("""
            SELECT CAST(t.cleaner_ids AS CHAR) AS cleaner_ids
            FROM public_toilet_cleaning_task t
            WHERE t.deleted = b'0'
              AND t.plan_status_id = 'uuid-plan-status-001'
            """)
    List<String> selectPendingCleanerIdsJson();

    /**
     * 保洁频次分布占比(待执行)
     */
    @Select("""
            SELECT
              COALESCE(NULLIF(TRIM(t.cleaning_frequency), ''), '未知') AS name,
              COUNT(1) AS value
            FROM public_toilet_cleaning_task t
            WHERE t.deleted = b'0'
              AND t.plan_status_id = 'uuid-plan-status-001'
            GROUP BY COALESCE(NULLIF(TRIM(t.cleaning_frequency), ''), '未知')
            ORDER BY value DESC
            """)
    List<PieItemVO> selectFrequencyDistribution();

    /**
     * 区域分布占比(待执行)
     */
    @Select("""
            SELECT
              COALESCE(NULLIF(TRIM(a.area_name), ''), '未知') AS name,
              COUNT(1) AS value
            FROM public_toilet_cleaning_task task
            LEFT JOIN public_toilet pt ON pt.toilet_id = task.toilet_id
            LEFT JOIN sys_area a ON a.area_code = pt.area_code
            WHERE task.deleted = b'0'
              AND task.plan_status_id = 'uuid-plan-status-001'
            GROUP BY COALESCE(NULLIF(TRIM(a.area_name), ''), '未知')
            ORDER BY value DESC
            """)
    List<PieItemVO> selectAreaDistribution();

    /**
     * 不同时段保洁计划数量对比(待执行)
     */
    @Select("""
            SELECT
              COALESCE(NULLIF(TRIM(t.cleaning_time), ''), '未知') AS name,
              COUNT(1) AS value
            FROM public_toilet_cleaning_task t
            WHERE t.deleted = b'0'
              AND t.plan_status_id = 'uuid-plan-status-001'
            GROUP BY COALESCE(NULLIF(TRIM(t.cleaning_time), ''), '未知')
            ORDER BY value DESC
            """)
    List<BarItemVO> selectPlanCountByTimeSlot();

    /**
     * 已完成任务总数
     */
    @Select("""
        SELECT COUNT(1)
        FROM public_toilet_cleaning_task t
        WHERE t.deleted = b'0'
          AND t.plan_status_id = 'uuid-plan-status-003'
        """)
    Long countCompleted();

    /**
     * 保洁达标率（已完成任务中，completion_rate >= 95 的占比，返回 0-100）
     */
    @Select("""
        SELECT IFNULL(
                 100 * SUM(CASE WHEN t.completion_rate IS NOT NULL AND t.completion_rate >= 95 THEN 1 ELSE 0 END)
                 / NULLIF(COUNT(1), 0)
               , 0) AS value
        FROM public_toilet_cleaning_task t
        WHERE t.deleted = b'0'
          AND t.plan_status_id = 'uuid-plan-status-003'
        """)
    Double calcCleaningQualifiedRate();

    /**
     * 投诉办结率（直接用公厕表 complaint_rate 的平均值，0-100）
     */
    @Select("""
        SELECT IFNULL(AVG(IFNULL(t.complaint_rate, 0)), 0)
        FROM public_toilet t
        WHERE t.deleted = b'0'
        """)
    Double calcComplaintFinishRate();

    /**
     * 设施完好率（直接用公厕表 facility_rate 的平均值，0-100）
     */
    @Select("""
        SELECT IFNULL(AVG(IFNULL(t.facility_rate, 0)), 0)
        FROM public_toilet t
        WHERE t.deleted = b'0'
        """)
    Double calcFacilityGoodRate();

    @Select("""
        SELECT
          COALESCE(NULLIF(TRIM(t.stat_period), ''), '未知') AS name,
          CAST(COUNT(1) AS DECIMAL(10, 2)) AS value
        FROM public_toilet_cleaning_task t
        WHERE t.deleted = b'0'
          AND t.plan_status_id = 'uuid-plan-status-003'
        GROUP BY COALESCE(NULLIF(TRIM(t.stat_period), ''), '未知')
        ORDER BY value DESC
        """)
    List<BarItemVO> selectCompletionCountByPeriod();

    @Select("""
        SELECT
          COALESCE(NULLIF(TRIM(t.stat_period), ''), '未知') AS name,
          IFNULL(AVG(IFNULL(t.completion_rate, 0)), 0) AS value
        FROM public_toilet_cleaning_task t
        WHERE t.deleted = b'0'
          AND t.plan_status_id = 'uuid-plan-status-003'
        GROUP BY COALESCE(NULLIF(TRIM(t.stat_period), ''), '未知')
        ORDER BY name
        """)
    List<BarItemVO> selectCleaningQualifiedTrend();

    @Select("""
        SELECT
          COALESCE(NULLIF(TRIM(t.cleaning_frequency), ''), '未知') AS name,
          COUNT(1) AS value
        FROM public_toilet_cleaning_task t
        WHERE t.deleted = b'0'
          AND t.plan_status_id = 'uuid-plan-status-003'
        GROUP BY COALESCE(NULLIF(TRIM(t.cleaning_frequency), ''), '未知')
        ORDER BY value DESC
        """)
    List<PieItemVO> selectTaskTypeDistribution();

    @Select("""
        SELECT
          COALESCE(NULLIF(TRIM(a.area_name), ''), '未知') AS name,
          COUNT(1) AS value
        FROM public_toilet_cleaning_task t
        LEFT JOIN public_toilet pt ON pt.toilet_id = t.toilet_id
        LEFT JOIN sys_area a ON a.area_code = pt.area_code
        WHERE t.deleted = b'0'
          AND t.plan_status_id = 'uuid-plan-status-003'
        GROUP BY COALESCE(NULLIF(TRIM(a.area_name), ''), '未知')
        ORDER BY value DESC
        """)
    List<PieItemVO> selectAreaCompletionDistribution();
}