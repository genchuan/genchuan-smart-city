package cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodingstreet;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingstreet.vo.GeocodingStreetPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingstreet.GeocodingStreetDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 街巷数据管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface GeocodingStreetMapper extends BaseMapperX<GeocodingStreetDO> {

    default PageResult<GeocodingStreetDO> selectPage(GeocodingStreetPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GeocodingStreetDO>()
                .eqIfPresent(GeocodingStreetDO::getStreetId, reqVO.getStreetId())
                .likeIfPresent(GeocodingStreetDO::getStreetName, reqVO.getStreetName())
                .likeIfPresent(GeocodingStreetDO::getStreetCode, reqVO.getStreetCode())
                .likeIfPresent(GeocodingStreetDO::getAlias, reqVO.getAlias())
                .likeIfPresent(GeocodingStreetDO::getOldName, reqVO.getOldName())
                .eqIfPresent(GeocodingStreetDO::getMinHouseNum, reqVO.getMinHouseNum())
                .eqIfPresent(GeocodingStreetDO::getMaxHouseNum, reqVO.getMaxHouseNum())
                .likeIfPresent(GeocodingStreetDO::getBeginningName, reqVO.getBeginningName())
                .likeIfPresent(GeocodingStreetDO::getEndingName, reqVO.getEndingName())
                .eqIfPresent(GeocodingStreetDO::getDirection, reqVO.getDirection())
                .eqIfPresent(GeocodingStreetDO::getCenterLineCoords, reqVO.getCenterLineCoords())
                .eqIfPresent(GeocodingStreetDO::getDataSource, reqVO.getDataSource())
                .betweenIfPresent(GeocodingStreetDO::getInitDate, reqVO.getInitDate())
                .betweenIfPresent(GeocodingStreetDO::getUpdateDate, reqVO.getUpdateDate())
                .likeIfPresent(GeocodingStreetDO::getRemark, reqVO.getRemark())
                .likeIfPresent(GeocodingStreetDO::getExtCategory1, reqVO.getExtCategory1())
                .likeIfPresent(GeocodingStreetDO::getExtCategory2, reqVO.getExtCategory2())
                .likeIfPresent(GeocodingStreetDO::getExtCategory3, reqVO.getExtCategory3())
                .betweenIfPresent(GeocodingStreetDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GeocodingStreetDO::getId));
    }

}