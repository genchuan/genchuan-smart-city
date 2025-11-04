package cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodinghouse;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodinghouse.vo.GeocodingHousePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodinghouse.GeocodingHouseDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 门(楼)牌数据管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface GeocodingHouseMapper extends BaseMapperX<GeocodingHouseDO> {

    default PageResult<GeocodingHouseDO> selectPage(GeocodingHousePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GeocodingHouseDO>()
                .likeIfPresent(GeocodingHouseDO::getHouseId, reqVO.getHouseId())
                .likeIfPresent(GeocodingHouseDO::getHouseName, reqVO.getHouseName())
                .likeIfPresent(GeocodingHouseDO::getHouseCode, reqVO.getHouseCode())
                .likeIfPresent(GeocodingHouseDO::getBdGridCode, reqVO.getBdGridCode())
                .likeIfPresent(GeocodingHouseDO::getStreetName, reqVO.getStreetName())
                .likeIfPresent(GeocodingHouseDO::getZoneName, reqVO.getZoneName())
                .likeIfPresent(GeocodingHouseDO::getHouseNum, reqVO.getHouseNum())
                .likeIfPresent(GeocodingHouseDO::getParentStreetName, reqVO.getParentStreetName())
                .likeIfPresent(GeocodingHouseDO::getStreetCode, reqVO.getStreetCode())
                .likeIfPresent(GeocodingHouseDO::getCommunityName, reqVO.getCommunityName())
                .likeIfPresent(GeocodingHouseDO::getCommunityCode, reqVO.getCommunityCode())
                .eqIfPresent(GeocodingHouseDO::getCoordX, reqVO.getCoordX())
                .eqIfPresent(GeocodingHouseDO::getCoordY, reqVO.getCoordY())
                .likeIfPresent(GeocodingHouseDO::getDataSource, reqVO.getDataSource())
                .betweenIfPresent(GeocodingHouseDO::getInitDate, reqVO.getInitDate())
                .betweenIfPresent(GeocodingHouseDO::getUpdateDate, reqVO.getUpdateDate())
                .likeIfPresent(GeocodingHouseDO::getRemark, reqVO.getRemark())
                .likeIfPresent(GeocodingHouseDO::getExtCategory1, reqVO.getExtCategory1())
                .likeIfPresent(GeocodingHouseDO::getExtCategory2, reqVO.getExtCategory2())
                .likeIfPresent(GeocodingHouseDO::getExtCategory3, reqVO.getExtCategory3())
                .betweenIfPresent(GeocodingHouseDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GeocodingHouseDO::getId));
    }

}