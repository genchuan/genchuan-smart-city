package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.review.inspectspecreview;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectspecreview.vo.InspectSpecReviewPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.review.inspectspecreview.InspectSpecReviewDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 巡查巡检专项结果复核 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectSpecReviewMapper extends BaseMapperX<InspectSpecReviewDO> {

    default PageResult<InspectSpecReviewDO> selectPage(InspectSpecReviewPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectSpecReviewDO>()
                .eqIfPresent(InspectSpecReviewDO::getSpecReviewId, reqVO.getSpecReviewId())
                .eqIfPresent(InspectSpecReviewDO::getSpecReviewCode, reqVO.getSpecReviewCode())
                .eqIfPresent(InspectSpecReviewDO::getSpecExecId, reqVO.getSpecExecId())
                .eqIfPresent(InspectSpecReviewDO::getSpecExecCode, reqVO.getSpecExecCode())
                .eqIfPresent(InspectSpecReviewDO::getSpecPlanId, reqVO.getSpecPlanId())
                .likeIfPresent(InspectSpecReviewDO::getSpecPlanName, reqVO.getSpecPlanName())
                .eqIfPresent(InspectSpecReviewDO::getExecUserId, reqVO.getExecUserId())
                .likeIfPresent(InspectSpecReviewDO::getExecUserName, reqVO.getExecUserName())
                .eqIfPresent(InspectSpecReviewDO::getSpecTopic, reqVO.getSpecTopic())
                .eqIfPresent(InspectSpecReviewDO::getReviewFocus, reqVO.getReviewFocus())
                .eqIfPresent(InspectSpecReviewDO::getReviewResult, reqVO.getReviewResult())
                .eqIfPresent(InspectSpecReviewDO::getReviewOpinion, reqVO.getReviewOpinion())
                .eqIfPresent(InspectSpecReviewDO::getReviewUser, reqVO.getReviewUser())
                .betweenIfPresent(InspectSpecReviewDO::getReviewTime, reqVO.getReviewTime())
                .eqIfPresent(InspectSpecReviewDO::getReckRequirement, reqVO.getReckRequirement())
                .eqIfPresent(InspectSpecReviewDO::getCreateUser, reqVO.getCreateUser())
                .eqIfPresent(InspectSpecReviewDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(InspectSpecReviewDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectSpecReviewDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectSpecReviewDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectSpecReviewDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectSpecReviewDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectSpecReviewDO::getId));
    }

}