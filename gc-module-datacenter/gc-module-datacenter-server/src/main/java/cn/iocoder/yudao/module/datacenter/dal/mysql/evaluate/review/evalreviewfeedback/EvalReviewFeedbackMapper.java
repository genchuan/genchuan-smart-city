package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.review.evalreviewfeedback;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalreviewfeedback.vo.EvalReviewFeedbackPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.review.evalreviewfeedback.EvalReviewFeedbackDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 复核结果反馈 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalReviewFeedbackMapper extends BaseMapperX<EvalReviewFeedbackDO> {

    default PageResult<EvalReviewFeedbackDO> selectPage(EvalReviewFeedbackPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalReviewFeedbackDO>()
                .eqIfPresent(EvalReviewFeedbackDO::getReviewFeedbackId, reqVO.getReviewFeedbackId())
                .eqIfPresent(EvalReviewFeedbackDO::getReviewId, reqVO.getReviewId())
                .eqIfPresent(EvalReviewFeedbackDO::getAppealId, reqVO.getAppealId())
                .eqIfPresent(EvalReviewFeedbackDO::getAppealCode, reqVO.getAppealCode())
                .eqIfPresent(EvalReviewFeedbackDO::getEvalObjectId, reqVO.getEvalObjectId())
                .likeIfPresent(EvalReviewFeedbackDO::getEvalObjectName, reqVO.getEvalObjectName())
                .eqIfPresent(EvalReviewFeedbackDO::getOriginalEvalLevel, reqVO.getOriginalEvalLevel())
                .eqIfPresent(EvalReviewFeedbackDO::getOriginalEvalScore, reqVO.getOriginalEvalScore())
                .eqIfPresent(EvalReviewFeedbackDO::getFinalReviewResult, reqVO.getFinalReviewResult())
                .eqIfPresent(EvalReviewFeedbackDO::getRevisedLevel, reqVO.getRevisedLevel())
                .eqIfPresent(EvalReviewFeedbackDO::getRevisedScore, reqVO.getRevisedScore())
                .eqIfPresent(EvalReviewFeedbackDO::getFeedbackOpinion, reqVO.getFeedbackOpinion())
                .betweenIfPresent(EvalReviewFeedbackDO::getFeedbackTime, reqVO.getFeedbackTime())
                .eqIfPresent(EvalReviewFeedbackDO::getFeedbackMethod, reqVO.getFeedbackMethod())
                .eqIfPresent(EvalReviewFeedbackDO::getCreateUserBiz, reqVO.getCreateUserBiz())
                .eqIfPresent(EvalReviewFeedbackDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(EvalReviewFeedbackDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalReviewFeedbackDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalReviewFeedbackDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalReviewFeedbackDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalReviewFeedbackDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalReviewFeedbackDO::getId));
    }

}