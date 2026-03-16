package cn.iocoder.yudao.module.envirhealth.dal.mysql.user;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.assessment.AssessmentPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.AssessmentDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 考核 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface AssessmentMapper extends BaseMapperX<AssessmentDO> {

    default PageResult<AssessmentDO> selectPage(AssessmentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AssessmentDO>()
                .eqIfPresent(AssessmentDO::getAssessmentId, reqVO.getAssessmentId())
                .eqIfPresent(AssessmentDO::getUserId, reqVO.getUserId())
                .eqIfPresent(AssessmentDO::getJobTypeId, reqVO.getJobTypeId())
                .eqIfPresent(AssessmentDO::getTeamId, reqVO.getTeamId())
                .eqIfPresent(AssessmentDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(AssessmentDO::getCycle, reqVO.getCycle())
                .eqIfPresent(AssessmentDO::getAttendanceScore, reqVO.getAttendanceScore())
                .eqIfPresent(AssessmentDO::getWorkQualityScore, reqVO.getWorkQualityScore())
                .eqIfPresent(AssessmentDO::getProblemSolvingScore, reqVO.getProblemSolvingScore())
                .eqIfPresent(AssessmentDO::getInitialTotalScore, reqVO.getInitialTotalScore())
                .eqIfPresent(AssessmentDO::getFinalTotalScore, reqVO.getFinalTotalScore())
                .eqIfPresent(AssessmentDO::getAssessmentGradeId, reqVO.getAssessmentGradeId())
                .eqIfPresent(AssessmentDO::getReviewOpinion, reqVO.getReviewOpinion())
                .eqIfPresent(AssessmentDO::getAssessBy, reqVO.getAssessBy())
                .betweenIfPresent(AssessmentDO::getReviewTime, reqVO.getReviewTime())
                .eqIfPresent(AssessmentDO::getProofUrl, reqVO.getProofUrl())
                .eqIfPresent(AssessmentDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AssessmentDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(AssessmentDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(AssessmentDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(AssessmentDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AssessmentDO::getId));
    }

}