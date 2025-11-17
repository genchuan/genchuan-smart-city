package cn.iocoder.yudao.module.datacenter.dal.mysql.publicinfopublish.publicsvcinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicsvcinfo.vo.PublicSvcInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.publicinfopublish.publicsvcinfo.PublicSvcInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 服务信息发布 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PublicSvcInfoMapper extends BaseMapperX<PublicSvcInfoDO> {

    default PageResult<PublicSvcInfoDO> selectPage(PublicSvcInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PublicSvcInfoDO>()
                .eqIfPresent(PublicSvcInfoDO::getSvcInfoId, reqVO.getSvcInfoId())
                .eqIfPresent(PublicSvcInfoDO::getSvcTitle, reqVO.getSvcTitle())
                .eqIfPresent(PublicSvcInfoDO::getSvcTypeCode, reqVO.getSvcTypeCode())
                .likeIfPresent(PublicSvcInfoDO::getSvcTypeName, reqVO.getSvcTypeName())
                .eqIfPresent(PublicSvcInfoDO::getSvcContent, reqVO.getSvcContent())
                .eqIfPresent(PublicSvcInfoDO::getSvcProvider, reqVO.getSvcProvider())
                .eqIfPresent(PublicSvcInfoDO::getContactPhone, reqVO.getContactPhone())
                .betweenIfPresent(PublicSvcInfoDO::getEffectiveTime, reqVO.getEffectiveTime())
                .betweenIfPresent(PublicSvcInfoDO::getExpireTime, reqVO.getExpireTime())
                .eqIfPresent(PublicSvcInfoDO::getPublishUserId, reqVO.getPublishUserId())
                .betweenIfPresent(PublicSvcInfoDO::getPublishTime, reqVO.getPublishTime())
                .eqIfPresent(PublicSvcInfoDO::getInfoStatus, reqVO.getInfoStatus())
                .eqIfPresent(PublicSvcInfoDO::getRemark, reqVO.getRemark())
                .eqIfPresent(PublicSvcInfoDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(PublicSvcInfoDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(PublicSvcInfoDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(PublicSvcInfoDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(PublicSvcInfoDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(PublicSvcInfoDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(PublicSvcInfoDO::getId));
    }

}