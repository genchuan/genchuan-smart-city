package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.review.evalreview;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalreview.vo.EvalReviewPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.review.evalreview.EvalReviewDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 复核办理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalReviewMapper extends BaseMapperX<EvalReviewDO> {

    default PageResult<EvalReviewDO> selectPage(EvalReviewPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalReviewDO>()
                .eqIfPresent(EvalReviewDO::getReviewId, reqVO.getReviewId())
                .eqIfPresent(EvalReviewDO::getAppealId, reqVO.getAppealId())
                .eqIfPresent(EvalReviewDO::getAppealCode, reqVO.getAppealCode())
                .eqIfPresent(EvalReviewDO::getEvalTaskId, reqVO.getEvalTaskId())
                .eqIfPresent(EvalReviewDO::getEvalObjectId, reqVO.getEvalObjectId())
                .likeIfPresent(EvalReviewDO::getEvalObjectName, reqVO.getEvalObjectName())
                .eqIfPresent(EvalReviewDO::getReviewUserId, reqVO.getReviewUserId())
                .likeIfPresent(EvalReviewDO::getReviewUserName, reqVO.getReviewUserName())
                .eqIfPresent(EvalReviewDO::getReviewOpinion, reqVO.getReviewOpinion())
                .eqIfPresent(EvalReviewDO::getReviewMats, reqVO.getReviewMats())
                .eqIfPresent(EvalReviewDO::getReviewStatus, reqVO.getReviewStatus())
                .betweenIfPresent(EvalReviewDO::getAssignTime, reqVO.getAssignTime())
                .betweenIfPresent(EvalReviewDO::getReviewCompleteTime, reqVO.getReviewCompleteTime())
                .eqIfPresent(EvalReviewDO::getAssignUserId, reqVO.getAssignUserId())
                .likeIfPresent(EvalReviewDO::getAssignUserName, reqVO.getAssignUserName())
                .eqIfPresent(EvalReviewDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalReviewDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalReviewDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalReviewDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalReviewDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalReviewDO::getId));
    }

}