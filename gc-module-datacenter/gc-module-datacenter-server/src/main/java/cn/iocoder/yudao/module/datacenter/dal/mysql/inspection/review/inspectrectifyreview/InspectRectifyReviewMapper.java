package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.review.inspectrectifyreview;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectrectifyreview.vo.InspectRectifyReviewPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.review.inspectrectifyreview.InspectRectifyReviewDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 巡查巡检整改结果复核 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectRectifyReviewMapper extends BaseMapperX<InspectRectifyReviewDO> {

    default PageResult<InspectRectifyReviewDO> selectPage(InspectRectifyReviewPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectRectifyReviewDO>()
                .eqIfPresent(InspectRectifyReviewDO::getRectifyReviewId, reqVO.getRectifyReviewId())
                .eqIfPresent(InspectRectifyReviewDO::getRectifyReviewCode, reqVO.getRectifyReviewCode())
                .eqIfPresent(InspectRectifyReviewDO::getAcceptId, reqVO.getAcceptId())
                .eqIfPresent(InspectRectifyReviewDO::getAcceptCode, reqVO.getAcceptCode())
                .eqIfPresent(InspectRectifyReviewDO::getRectifyTaskId, reqVO.getRectifyTaskId())
                .likeIfPresent(InspectRectifyReviewDO::getProblemName, reqVO.getProblemName())
                .likeIfPresent(InspectRectifyReviewDO::getDeptName, reqVO.getDeptName())
                .likeIfPresent(InspectRectifyReviewDO::getLeaderUserName, reqVO.getLeaderUserName())
                .eqIfPresent(InspectRectifyReviewDO::getReviewMethod, reqVO.getReviewMethod())
                .eqIfPresent(InspectRectifyReviewDO::getReviewPhotoUrls, reqVO.getReviewPhotoUrls())
                .eqIfPresent(InspectRectifyReviewDO::getReviewResult, reqVO.getReviewResult())
                .eqIfPresent(InspectRectifyReviewDO::getReviewOpinion, reqVO.getReviewOpinion())
                .eqIfPresent(InspectRectifyReviewDO::getReviewUser, reqVO.getReviewUser())
                .betweenIfPresent(InspectRectifyReviewDO::getReviewTime, reqVO.getReviewTime())
                .eqIfPresent(InspectRectifyReviewDO::getCreateUser, reqVO.getCreateUser())
                .eqIfPresent(InspectRectifyReviewDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectRectifyReviewDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectRectifyReviewDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectRectifyReviewDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectRectifyReviewDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectRectifyReviewDO::getId));
    }

}