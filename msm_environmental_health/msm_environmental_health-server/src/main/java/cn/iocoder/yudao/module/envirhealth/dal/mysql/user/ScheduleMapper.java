package cn.iocoder.yudao.module.envirhealth.dal.mysql.user;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.schedule.SchedulePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.ScheduleDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 排班计划 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ScheduleMapper extends BaseMapperX<ScheduleDO> {

    default PageResult<ScheduleDO> selectPage(SchedulePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ScheduleDO>()
                .eqIfPresent(ScheduleDO::getScheduleId, reqVO.getScheduleId())
                .eqIfPresent(ScheduleDO::getUserId, reqVO.getUserId())
                .eqIfPresent(ScheduleDO::getJobTypeId, reqVO.getJobTypeId())
                .eqIfPresent(ScheduleDO::getTeamId, reqVO.getTeamId())
                .eqIfPresent(ScheduleDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(ScheduleDO::getCycle, reqVO.getCycle())
                .eqIfPresent(ScheduleDO::getWorkTimePeriod, reqVO.getWorkTimePeriod())
                .eqIfPresent(ScheduleDO::getScheduleStatusId, reqVO.getScheduleStatusId())
                .eqIfPresent(ScheduleDO::getSwapStatus, reqVO.getSwapStatus())
                .eqIfPresent(ScheduleDO::getSwapApplicantId, reqVO.getSwapApplicantId())
                .eqIfPresent(ScheduleDO::getSwapTargetId, reqVO.getSwapTargetId())
                .betweenIfPresent(ScheduleDO::getSwapDate, reqVO.getSwapDate())
                .eqIfPresent(ScheduleDO::getSwapReason, reqVO.getSwapReason())
                .eqIfPresent(ScheduleDO::getReviewResult, reqVO.getReviewResult())
                .eqIfPresent(ScheduleDO::getReviewOpinion, reqVO.getReviewOpinion())
                .eqIfPresent(ScheduleDO::getCoverageRate, reqVO.getCoverageRate())
                .eqIfPresent(ScheduleDO::getVacancyReminder, reqVO.getVacancyReminder())
                .eqIfPresent(ScheduleDO::getSwapApplyCount, reqVO.getSwapApplyCount())
                .eqIfPresent(ScheduleDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ScheduleDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ScheduleDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ScheduleDO::getExtCommon4, reqVO.getExtCommon4())
                .eqIfPresent(ScheduleDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(ScheduleDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ScheduleDO::getId));
    }

}