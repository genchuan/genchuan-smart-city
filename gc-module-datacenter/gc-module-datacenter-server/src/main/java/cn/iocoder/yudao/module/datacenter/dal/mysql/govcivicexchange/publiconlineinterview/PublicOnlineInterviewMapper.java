package cn.iocoder.yudao.module.datacenter.dal.mysql.govcivicexchange.publiconlineinterview;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publiconlineinterview.vo.PublicOnlineInterviewPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.govcivicexchange.publiconlineinterview.PublicOnlineInterviewDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 在线访谈 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PublicOnlineInterviewMapper extends BaseMapperX<PublicOnlineInterviewDO> {

    default PageResult<PublicOnlineInterviewDO> selectPage(PublicOnlineInterviewPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PublicOnlineInterviewDO>()
                .eqIfPresent(PublicOnlineInterviewDO::getInterviewId, reqVO.getInterviewId())
                .eqIfPresent(PublicOnlineInterviewDO::getInterviewTopic, reqVO.getInterviewTopic())
                .eqIfPresent(PublicOnlineInterviewDO::getDeptCode, reqVO.getDeptCode())
                .likeIfPresent(PublicOnlineInterviewDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(PublicOnlineInterviewDO::getGuestInfo, reqVO.getGuestInfo())
                .betweenIfPresent(PublicOnlineInterviewDO::getInterviewTime, reqVO.getInterviewTime())
                .eqIfPresent(PublicOnlineInterviewDO::getInterviewEndure, reqVO.getInterviewEndure())
                .eqIfPresent(PublicOnlineInterviewDO::getInterviewStatus, reqVO.getInterviewStatus())
                .eqIfPresent(PublicOnlineInterviewDO::getQuestionCount, reqVO.getQuestionCount())
                .eqIfPresent(PublicOnlineInterviewDO::getReplyCount, reqVO.getReplyCount())
                .eqIfPresent(PublicOnlineInterviewDO::getRecContent, reqVO.getRecContent())
                .betweenIfPresent(PublicOnlineInterviewDO::getRecPublishTime, reqVO.getRecPublishTime())
                .eqIfPresent(PublicOnlineInterviewDO::getPublishUserId, reqVO.getPublishUserId())
                .likeIfPresent(PublicOnlineInterviewDO::getPublishUserName, reqVO.getPublishUserName())
                .eqIfPresent(PublicOnlineInterviewDO::getRemark, reqVO.getRemark())
                .eqIfPresent(PublicOnlineInterviewDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(PublicOnlineInterviewDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(PublicOnlineInterviewDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(PublicOnlineInterviewDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(PublicOnlineInterviewDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(PublicOnlineInterviewDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(PublicOnlineInterviewDO::getId));
    }

}