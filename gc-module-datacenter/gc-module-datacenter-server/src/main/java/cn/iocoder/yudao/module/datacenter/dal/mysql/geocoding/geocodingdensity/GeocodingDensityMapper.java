package cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodingdensity;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingdensity.vo.GeocodingDensityPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingdensity.GeocodingDensityDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据采集密度配置 Mapper
 *
 * @author zcq
 */
@Mapper
public interface GeocodingDensityMapper extends BaseMapperX<GeocodingDensityDO> {

    default PageResult<GeocodingDensityDO> selectPage(GeocodingDensityPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GeocodingDensityDO>()
                .eqIfPresent(GeocodingDensityDO::getDensityConfigId, reqVO.getDensityConfigId())
                .eqIfPresent(GeocodingDensityDO::getRegionCategory, reqVO.getRegionCategory())
                .eqIfPresent(GeocodingDensityDO::getRegionCategoryDesc, reqVO.getRegionCategoryDesc())
                .eqIfPresent(GeocodingDensityDO::getHouseInterval, reqVO.getHouseInterval())
                .eqIfPresent(GeocodingDensityDO::getPoiInterval, reqVO.getPoiInterval())
                .eqIfPresent(GeocodingDensityDO::getSpecialPoiRequirement, reqVO.getSpecialPoiRequirement())
                .eqIfPresent(GeocodingDensityDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(GeocodingDensityDO::getConfigUser, reqVO.getConfigUser())
                .betweenIfPresent(GeocodingDensityDO::getConfigTime, reqVO.getConfigTime())
                .eqIfPresent(GeocodingDensityDO::getRemark, reqVO.getRemark())
                .eqIfPresent(GeocodingDensityDO::getExtCategory1, reqVO.getExtCategory1())
                .eqIfPresent(GeocodingDensityDO::getExtCategory2, reqVO.getExtCategory2())
                .eqIfPresent(GeocodingDensityDO::getExtCategory3, reqVO.getExtCategory3())
                .betweenIfPresent(GeocodingDensityDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GeocodingDensityDO::getId));
    }

}