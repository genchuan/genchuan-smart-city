package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.review.inspectdailyreview;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectdailyreview.vo.InspectDailyReviewPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.review.inspectdailyreview.InspectDailyReviewDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 巡查巡检日常结果复核 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectDailyReviewMapper extends BaseMapperX<InspectDailyReviewDO> {

    default PageResult<InspectDailyReviewDO> selectPage(InspectDailyReviewPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectDailyReviewDO>()
                .eqIfPresent(InspectDailyReviewDO::getDailyReviewId, reqVO.getDailyReviewId())
                .eqIfPresent(InspectDailyReviewDO::getDailyReviewCode, reqVO.getDailyReviewCode())
                .eqIfPresent(InspectDailyReviewDO::getDailyExecId, reqVO.getDailyExecId())
                .eqIfPresent(InspectDailyReviewDO::getDailyExecCode, reqVO.getDailyExecCode())
                .eqIfPresent(InspectDailyReviewDO::getExecUserId, reqVO.getExecUserId())
                .likeIfPresent(InspectDailyReviewDO::getExecUserName, reqVO.getExecUserName())
                .likeIfPresent(InspectDailyReviewDO::getExecAreaName, reqVO.getExecAreaName())
                .eqIfPresent(InspectDailyReviewDO::getReviewContent, reqVO.getReviewContent())
                .eqIfPresent(InspectDailyReviewDO::getReviewResult, reqVO.getReviewResult())
                .eqIfPresent(InspectDailyReviewDO::getReviewOpinion, reqVO.getReviewOpinion())
                .eqIfPresent(InspectDailyReviewDO::getReviewBasis, reqVO.getReviewBasis())
                .eqIfPresent(InspectDailyReviewDO::getReviewUser, reqVO.getReviewUser())
                .betweenIfPresent(InspectDailyReviewDO::getReviewTime, reqVO.getReviewTime())
                .eqIfPresent(InspectDailyReviewDO::getRectifyRequirement, reqVO.getRectifyRequirement())
                .eqIfPresent(InspectDailyReviewDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectDailyReviewDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectDailyReviewDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectDailyReviewDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectDailyReviewDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectDailyReviewDO::getId));
    }

}