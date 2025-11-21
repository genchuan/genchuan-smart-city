package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.result.inspectresultfeedback;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.result.inspectresultfeedback.vo.InspectResultFeedbackPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.result.inspectresultfeedback.InspectResultFeedbackDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 巡查巡检结果反馈 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectResultFeedbackMapper extends BaseMapperX<InspectResultFeedbackDO> {

    default PageResult<InspectResultFeedbackDO> selectPage(InspectResultFeedbackPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectResultFeedbackDO>()
                .eqIfPresent(InspectResultFeedbackDO::getFeedbackId, reqVO.getFeedbackId())
                .eqIfPresent(InspectResultFeedbackDO::getFeedbackCode, reqVO.getFeedbackCode())
                .eqIfPresent(InspectResultFeedbackDO::getPublicId, reqVO.getPublicId())
                .eqIfPresent(InspectResultFeedbackDO::getPublicTitle, reqVO.getPublicTitle())
                .likeIfPresent(InspectResultFeedbackDO::getFeedbackUserName, reqVO.getFeedbackUserName())
                .eqIfPresent(InspectResultFeedbackDO::getFeedbackUserPhone, reqVO.getFeedbackUserPhone())
                .eqIfPresent(InspectResultFeedbackDO::getFeedbackContent, reqVO.getFeedbackContent())
                .eqIfPresent(InspectResultFeedbackDO::getFeedbackPhotoUrls, reqVO.getFeedbackPhotoUrls())
                .betweenIfPresent(InspectResultFeedbackDO::getFeedbackTime, reqVO.getFeedbackTime())
                .eqIfPresent(InspectResultFeedbackDO::getFeedbackStatus, reqVO.getFeedbackStatus())
                .eqIfPresent(InspectResultFeedbackDO::getVerifyUserId, reqVO.getVerifyUserId())
                .likeIfPresent(InspectResultFeedbackDO::getVerifyUserName, reqVO.getVerifyUserName())
                .eqIfPresent(InspectResultFeedbackDO::getVerifyResult, reqVO.getVerifyResult())
                .eqIfPresent(InspectResultFeedbackDO::getVerifyOpinion, reqVO.getVerifyOpinion())
                .eqIfPresent(InspectResultFeedbackDO::getProblemId, reqVO.getProblemId())
                .eqIfPresent(InspectResultFeedbackDO::getHandleResult, reqVO.getHandleResult())
                .eqIfPresent(InspectResultFeedbackDO::getHandleUser, reqVO.getHandleUser())
                .betweenIfPresent(InspectResultFeedbackDO::getHandleTime, reqVO.getHandleTime())
                .eqIfPresent(InspectResultFeedbackDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectResultFeedbackDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectResultFeedbackDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectResultFeedbackDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectResultFeedbackDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectResultFeedbackDO::getId));
    }

}