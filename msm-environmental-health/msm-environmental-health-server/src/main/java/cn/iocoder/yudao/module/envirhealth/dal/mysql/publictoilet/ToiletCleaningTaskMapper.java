package cn.iocoder.yudao.module.envirhealth.dal.mysql.publictoilet;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcleaningtask.ToiletCleaningTaskPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcleaningtask.ToiletCleaningTaskWithJoinRespVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletCleaningTaskDO;
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
                .orderByDesc(ToiletCleaningTaskDO::getId));
    }

    /**
     * 查询指定日期的最大序号（用于task_no）
     */
    @Select("SELECT IFNULL(MAX(RIGHT(task_no, 3)), 0) FROM public_toilet_cleaning_task WHERE task_no LIKE CONCAT('PTCT', #{dateStr}, '%')")
    Integer selectMaxSeqByDate(@Param("dateStr") String dateStr);

    /**
     * 分页查询公厕保洁任务（带关联信息）
     */
    default PageResult<ToiletCleaningTaskWithJoinRespVO> selectJoinPage(ToiletCleaningTaskPageReqVO reqVO) {
        Page<ToiletCleaningTaskWithJoinRespVO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());
        IPage<ToiletCleaningTaskWithJoinRespVO> iPage = this.selectJoinPage(page, reqVO);
        return new PageResult<>(iPage.getRecords(), iPage.getTotal());
    }

    /**
     * 联表查询分页
     */
    IPage<ToiletCleaningTaskWithJoinRespVO> selectJoinPage(Page<?> page, @Param("reqVO") ToiletCleaningTaskPageReqVO reqVO);

    /**
     * 按计划状态统计
     */
    @Select("SELECT " +
            "    COALESCE(sps.name, '未设置') as status_name, " +
            "    COUNT(ptct.id) as count " +
            "FROM public_toilet_cleaning_task ptct " +
            "LEFT JOIN sys_plan_status sps ON ptct.plan_status_id = sps.sys_plan_status_id " +
            "WHERE ptct.deleted = 0 " +
            "GROUP BY ptct.plan_status_id, sps.name")
    List<Map<String, Object>> selectStatisticsByPlanStatus();
}