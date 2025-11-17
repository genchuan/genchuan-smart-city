package cn.iocoder.yudao.module.datacenter.dal.mysql.bizservicedemand.publichotlinelink;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publichotlinelink.vo.PublicHotlineLinkPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.bizservicedemand.publichotlinelink.PublicHotlineLinkDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 热线对接 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PublicHotlineLinkMapper extends BaseMapperX<PublicHotlineLinkDO> {

    default PageResult<PublicHotlineLinkDO> selectPage(PublicHotlineLinkPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PublicHotlineLinkDO>()
                .eqIfPresent(PublicHotlineLinkDO::getHotlineId, reqVO.getHotlineId())
                .eqIfPresent(PublicHotlineLinkDO::getHotlineNo, reqVO.getHotlineNo())
                .eqIfPresent(PublicHotlineLinkDO::getPlatformNo, reqVO.getPlatformNo())
                .eqIfPresent(PublicHotlineLinkDO::getAppealTypeCode, reqVO.getAppealTypeCode())
                .likeIfPresent(PublicHotlineLinkDO::getAppealTypeName, reqVO.getAppealTypeName())
                .eqIfPresent(PublicHotlineLinkDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(PublicHotlineLinkDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(PublicHotlineLinkDO::getAppealContent, reqVO.getAppealContent())
                .likeIfPresent(PublicHotlineLinkDO::getContactName, reqVO.getContactName())
                .eqIfPresent(PublicHotlineLinkDO::getContactPhone, reqVO.getContactPhone())
                .eqIfPresent(PublicHotlineLinkDO::getHotlineSource, reqVO.getHotlineSource())
                .eqIfPresent(PublicHotlineLinkDO::getSyncStatus, reqVO.getSyncStatus())
                .eqIfPresent(PublicHotlineLinkDO::getHandleStatus, reqVO.getHandleStatus())
                .eqIfPresent(PublicHotlineLinkDO::getAcceptDeptCode, reqVO.getAcceptDeptCode())
                .likeIfPresent(PublicHotlineLinkDO::getAcceptDeptName, reqVO.getAcceptDeptName())
                .eqIfPresent(PublicHotlineLinkDO::getHandleResult, reqVO.getHandleResult())
                .betweenIfPresent(PublicHotlineLinkDO::getSyncTime, reqVO.getSyncTime())
                .betweenIfPresent(PublicHotlineLinkDO::getFinishTime, reqVO.getFinishTime())
                .eqIfPresent(PublicHotlineLinkDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(PublicHotlineLinkDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(PublicHotlineLinkDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(PublicHotlineLinkDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(PublicHotlineLinkDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(PublicHotlineLinkDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(PublicHotlineLinkDO::getId));
    }

}