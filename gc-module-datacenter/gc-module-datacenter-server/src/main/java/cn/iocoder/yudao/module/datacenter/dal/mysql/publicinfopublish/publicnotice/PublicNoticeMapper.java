package cn.iocoder.yudao.module.datacenter.dal.mysql.publicinfopublish.publicnotice;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicnotice.vo.PublicNoticePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.publicinfopublish.publicnotice.PublicNoticeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 通知公告发布 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PublicNoticeMapper extends BaseMapperX<PublicNoticeDO> {

    default PageResult<PublicNoticeDO> selectPage(PublicNoticePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PublicNoticeDO>()
                .eqIfPresent(PublicNoticeDO::getNoticeId, reqVO.getNoticeId())
                .eqIfPresent(PublicNoticeDO::getNoticeTitle, reqVO.getNoticeTitle())
                .eqIfPresent(PublicNoticeDO::getNoticeContent, reqVO.getNoticeContent())
                .eqIfPresent(PublicNoticeDO::getDeptCode, reqVO.getDeptCode())
                .likeIfPresent(PublicNoticeDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(PublicNoticeDO::getPublishUserId, reqVO.getPublishUserId())
                .likeIfPresent(PublicNoticeDO::getPublishUserName, reqVO.getPublishUserName())
                .betweenIfPresent(PublicNoticeDO::getPublishTime, reqVO.getPublishTime())
                .eqIfPresent(PublicNoticeDO::getNoticeStatus, reqVO.getNoticeStatus())
                .betweenIfPresent(PublicNoticeDO::getEffectiveTime, reqVO.getEffectiveTime())
                .betweenIfPresent(PublicNoticeDO::getExpireTime, reqVO.getExpireTime())
                .eqIfPresent(PublicNoticeDO::getAttachPath, reqVO.getAttachPath())
                .eqIfPresent(PublicNoticeDO::getRemark, reqVO.getRemark())
                .eqIfPresent(PublicNoticeDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(PublicNoticeDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(PublicNoticeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(PublicNoticeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(PublicNoticeDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(PublicNoticeDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(PublicNoticeDO::getId));
    }

}