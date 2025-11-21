package cn.iocoder.yudao.module.datacenter.dal.mysql.govcivicexchange.publicfeedback;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicfeedback.vo.PublicFeedbackPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.govcivicexchange.publicfeedback.PublicFeedbackDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 意见反馈 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PublicFeedbackMapper extends BaseMapperX<PublicFeedbackDO> {

    default PageResult<PublicFeedbackDO> selectPage(PublicFeedbackPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PublicFeedbackDO>()
                .eqIfPresent(PublicFeedbackDO::getFeedbackId, reqVO.getFeedbackId())
                .eqIfPresent(PublicFeedbackDO::getFeedbackNo, reqVO.getFeedbackNo())
                .eqIfPresent(PublicFeedbackDO::getFeedbackTypeCode, reqVO.getFeedbackTypeCode())
                .likeIfPresent(PublicFeedbackDO::getFeedbackTypeName, reqVO.getFeedbackTypeName())
                .eqIfPresent(PublicFeedbackDO::getFeedbackContent, reqVO.getFeedbackContent())
                .eqIfPresent(PublicFeedbackDO::getImgPath, reqVO.getImgPath())
                .eqIfPresent(PublicFeedbackDO::getFeedbackUserId, reqVO.getFeedbackUserId())
                .likeIfPresent(PublicFeedbackDO::getFeedbackUserName, reqVO.getFeedbackUserName())
                .eqIfPresent(PublicFeedbackDO::getFeedbackUserPhone, reqVO.getFeedbackUserPhone())
                .eqIfPresent(PublicFeedbackDO::getEvalStatus, reqVO.getEvalStatus())
                .eqIfPresent(PublicFeedbackDO::getEvalOpinion, reqVO.getEvalOpinion())
                .eqIfPresent(PublicFeedbackDO::getEvalUserId, reqVO.getEvalUserId())
                .betweenIfPresent(PublicFeedbackDO::getEvalTime, reqVO.getEvalTime())
                .betweenIfPresent(PublicFeedbackDO::getFeedbackTime, reqVO.getFeedbackTime())
                .eqIfPresent(PublicFeedbackDO::getRemark, reqVO.getRemark())
                .eqIfPresent(PublicFeedbackDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(PublicFeedbackDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(PublicFeedbackDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(PublicFeedbackDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(PublicFeedbackDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(PublicFeedbackDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(PublicFeedbackDO::getId));
    }

}