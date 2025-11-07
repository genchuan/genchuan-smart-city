package cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodingzone;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingzone.vo.GeocodingZonePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingzone.GeocodingZoneDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 地片与区片数据管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface GeocodingZoneMapper extends BaseMapperX<GeocodingZoneDO> {

    default PageResult<GeocodingZoneDO> selectPage(GeocodingZonePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GeocodingZoneDO>()
                .eqIfPresent(GeocodingZoneDO::getZoneId, reqVO.getZoneId())
                .likeIfPresent(GeocodingZoneDO::getZoneName, reqVO.getZoneName())
                .likeIfPresent(GeocodingZoneDO::getZoneCode, reqVO.getZoneCode())
                .likeIfPresent(GeocodingZoneDO::getAlias, reqVO.getAlias())
                .likeIfPresent(GeocodingZoneDO::getOldName, reqVO.getOldName())
                .eqIfPresent(GeocodingZoneDO::getBoundary, reqVO.getBoundary())
                .likeIfPresent(GeocodingZoneDO::getStreetName, reqVO.getStreetName())
                .likeIfPresent(GeocodingZoneDO::getStreetCode, reqVO.getStreetCode())
                .likeIfPresent(GeocodingZoneDO::getCommunityName, reqVO.getCommunityName())
                .likeIfPresent(GeocodingZoneDO::getCommunityCode, reqVO.getCommunityCode())
                .likeIfPresent(GeocodingZoneDO::getSpaceType, reqVO.getSpaceType())
                .eqIfPresent(GeocodingZoneDO::getCoordX, reqVO.getCoordX())
                .eqIfPresent(GeocodingZoneDO::getCoordY, reqVO.getCoordY())
                .likeIfPresent(GeocodingZoneDO::getDataSource, reqVO.getDataSource())
                .betweenIfPresent(GeocodingZoneDO::getInitDate, reqVO.getInitDate())
                .betweenIfPresent(GeocodingZoneDO::getUpdateDate, reqVO.getUpdateDate())
                .likeIfPresent(GeocodingZoneDO::getRemark, reqVO.getRemark())
                .likeIfPresent(GeocodingZoneDO::getExtCategory1, reqVO.getExtCategory1())
                .likeIfPresent(GeocodingZoneDO::getExtCategory2, reqVO.getExtCategory2())
                .likeIfPresent(GeocodingZoneDO::getExtCategory3, reqVO.getExtCategory3())
                .betweenIfPresent(GeocodingZoneDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GeocodingZoneDO::getId));
    }

}