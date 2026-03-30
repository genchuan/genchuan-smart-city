package cn.iocoder.yudao.module.envirhealth.dal.mysql.publictoilet;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcleaningtask.ToiletCleaningTaskPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletCleaningTaskDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletCleaningTaskDetailDO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

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
                .betweenIfPresent(ToiletCleaningTaskDO::getCompleteTime, reqVO.getCompleteTime())
                .eqIfPresent(ToiletCleaningTaskDO::getHandleResult, reqVO.getHandleResult())
                .betweenIfPresent(ToiletCleaningTaskDO::getHandleDuration, reqVO.getHandleDuration())
                .eqIfPresent(ToiletCleaningTaskDO::getSatisfaction, reqVO.getSatisfaction())
                .eqIfPresent(ToiletCleaningTaskDO::getStatPeriod, reqVO.getStatPeriod())
                .eqIfPresent(ToiletCleaningTaskDO::getReviewDesc, reqVO.getReviewDesc())
                .orderByDesc(ToiletCleaningTaskDO::getId));
    }

    @Select("SELECT IFNULL(MAX(RIGHT(task_no, 3)), 0) FROM public_toilet_cleaning_task WHERE task_no LIKE CONCAT('PTCT', #{dateStr}, '%')")
    Integer selectMaxSeqByDate(@Param("dateStr") String dateStr);

    List<ToiletCleaningTaskDetailDO> selectDetailPage(@Param("reqVO") ToiletCleaningTaskPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") ToiletCleaningTaskPageReqVO pageReqVO);

    @Select("SELECT " +
            "    CASE " +
            "        WHEN ptct.plan_status_id IN ('uuid-plan-status-001', 'uuid-plan-status-002') THEN '保洁待执行' " +
            "        WHEN ptct.plan_status_id = 'uuid-plan-status-003' THEN '已完成' " +
            "        ELSE COALESCE(sps.name, '未设置') " +
            "    END AS status_name, " +
            "    COUNT(ptct.id) AS count " +
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

    @Select("SELECT COUNT(1) " +
            "FROM public_toilet_cleaning_task t " +
            "WHERE t.deleted = 0 " +
            "  AND t.plan_status_id = 'uuid-plan-status-001'")
    Long countPending();

    @Select("SELECT COUNT(DISTINCT COALESCE(NULLIF(TRIM(a.area_name), ''), '未知')) " +
            "FROM public_toilet_cleaning_task task " +
            "LEFT JOIN public_toilet pt ON pt.toilet_id = task.toilet_id " +
            "LEFT JOIN sys_area a ON a.area_code = pt.area_code " +
            "WHERE task.deleted = 0 " +
            "  AND task.plan_status_id = 'uuid-plan-status-001'")
    Long countPendingAreaNum();

    @Select("SELECT t.cleaner_ids " +
            "FROM public_toilet_cleaning_task t " +
            "WHERE t.deleted = 0 " +
            "  AND t.plan_status_id = 'uuid-plan-status-001'")
    List<String> selectPendingCleanerIdsJson();

    @Select("SELECT " +
            "  COALESCE(NULLIF(TRIM(t.cleaning_frequency), ''), '未知') AS name, " +
            "  COUNT(1) AS value " +
            "FROM public_toilet_cleaning_task t " +
            "WHERE t.deleted = 0 " +
            "  AND t.plan_status_id = 'uuid-plan-status-001' " +
            "GROUP BY COALESCE(NULLIF(TRIM(t.cleaning_frequency), ''), '未知') " +
            "ORDER BY value DESC")
    List<PieItemVO> selectFrequencyDistribution();

    @Select("SELECT " +
            "  COALESCE(NULLIF(TRIM(a.area_name), ''), '未知') AS name, " +
            "  COUNT(1) AS value " +
            "FROM public_toilet_cleaning_task task " +
            "LEFT JOIN public_toilet pt ON pt.toilet_id = task.toilet_id " +
            "LEFT JOIN sys_area a ON a.area_code = pt.area_code " +
            "WHERE task.deleted = 0 " +
            "  AND task.plan_status_id = 'uuid-plan-status-001' " +
            "GROUP BY COALESCE(NULLIF(TRIM(a.area_name), ''), '未知') " +
            "ORDER BY value DESC")
    List<PieItemVO> selectAreaDistribution();

    @Select("SELECT " +
            "  COALESCE(NULLIF(TRIM(t.cleaning_time), ''), '未知') AS name, " +
            "  COUNT(1) AS value " +
            "FROM public_toilet_cleaning_task t " +
            "WHERE t.deleted = 0 " +
            "  AND t.plan_status_id = 'uuid-plan-status-001' " +
            "GROUP BY COALESCE(NULLIF(TRIM(t.cleaning_time), ''), '未知') " +
            "ORDER BY value DESC")
    List<BarItemVO> selectPlanCountByTimeSlot();

    @Select("SELECT COUNT(1) " +
            "FROM public_toilet_cleaning_task t " +
            "WHERE t.deleted = 0 " +
            "  AND t.plan_status_id = 'uuid-plan-status-003'")
    Long countCompleted();

    @Select("SELECT IFNULL(" +
            "         100 * SUM(IF(t.completion_rate IS NOT NULL AND t.completion_rate >= 95, 1, 0)) " +
            "         / NULLIF(COUNT(1), 0) " +
            "       , 0) AS value " +
            "FROM public_toilet_cleaning_task t " +
            "WHERE t.deleted = 0 " +
            "  AND t.plan_status_id = 'uuid-plan-status-003'")
    Double calcCleaningQualifiedRate();

    @Select("SELECT IFNULL(AVG(IFNULL(t.complaint_rate, 0)), 0) " +
            "FROM public_toilet t " +
            "WHERE t.deleted = 0")
    Double calcComplaintFinishRate();

    @Select("SELECT IFNULL(AVG(IFNULL(t.facility_rate, 0)), 0) " +
            "FROM public_toilet t " +
            "WHERE t.deleted = 0")
    Double calcFacilityGoodRate();

    @Select("SELECT " +
            "  COALESCE(NULLIF(TRIM(t.stat_period), ''), '未知') AS name, " +
            "  CAST(COUNT(1) AS DECIMAL(10,2)) AS value " +
            "FROM public_toilet_cleaning_task t " +
            "WHERE t.deleted = 0 " +
            "  AND t.plan_status_id = 'uuid-plan-status-003' " +
            "GROUP BY COALESCE(NULLIF(TRIM(t.stat_period), ''), '未知') " +
            "ORDER BY value DESC")
    List<BarItemVO> selectCompletionCountByPeriod();

    @Select("SELECT " +
            "  COALESCE(NULLIF(TRIM(t.stat_period), ''), '未知') AS name, " +
            "  IFNULL(AVG(IFNULL(t.completion_rate, 0)), 0) AS value " +
            "FROM public_toilet_cleaning_task t " +
            "WHERE t.deleted = 0 " +
            "  AND t.plan_status_id = 'uuid-plan-status-003' " +
            "GROUP BY COALESCE(NULLIF(TRIM(t.stat_period), ''), '未知') " +
            "ORDER BY name")
    List<BarItemVO> selectCleaningQualifiedTrend();

    @Select("SELECT " +
            "  COALESCE(NULLIF(TRIM(t.cleaning_frequency), ''), '未知') AS name, " +
            "  COUNT(1) AS value " +
            "FROM public_toilet_cleaning_task t " +
            "WHERE t.deleted = 0 " +
            "  AND t.plan_status_id = 'uuid-plan-status-003' " +
            "GROUP BY COALESCE(NULLIF(TRIM(t.cleaning_frequency), ''), '未知') " +
            "ORDER BY value DESC")
    List<PieItemVO> selectTaskTypeDistribution();

    @Select("SELECT " +
            "  COALESCE(NULLIF(TRIM(a.area_name), ''), '未知') AS name, " +
            "  COUNT(1) AS value " +
            "FROM public_toilet_cleaning_task t " +
            "LEFT JOIN public_toilet pt ON pt.toilet_id = t.toilet_id " +
            "LEFT JOIN sys_area a ON a.area_code = pt.area_code " +
            "WHERE t.deleted = 0 " +
            "  AND t.plan_status_id = 'uuid-plan-status-003' " +
            "GROUP BY COALESCE(NULLIF(TRIM(a.area_name), ''), '未知') " +
            "ORDER BY value DESC")
    List<PieItemVO> selectAreaCompletionDistribution();
}