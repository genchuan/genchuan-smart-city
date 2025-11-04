package cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodingregion;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingregion.vo.GeocodingRegionPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingregion.GeocodingRegionDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 区域数据管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface GeocodingRegionMapper extends BaseMapperX<GeocodingRegionDO> {

    default PageResult<GeocodingRegionDO> selectPage(GeocodingRegionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GeocodingRegionDO>()
                .eqIfPresent(GeocodingRegionDO::getRegionId, reqVO.getRegionId())
                .likeIfPresent(GeocodingRegionDO::getRegionName, reqVO.getRegionName())
                .likeIfPresent(GeocodingRegionDO::getRegionCode, reqVO.getRegionCode())
                .likeIfPresent(GeocodingRegionDO::getAlias, reqVO.getAlias())
                .likeIfPresent(GeocodingRegionDO::getOldName, reqVO.getOldName())
                .likeIfPresent(GeocodingRegionDO::getBoundaryDesc, reqVO.getBoundaryDesc())
                .likeIfPresent(GeocodingRegionDO::getParentRegionCode, reqVO.getParentRegionCode())
                .likeIfPresent(GeocodingRegionDO::getStreetCode, reqVO.getStreetCode())
                .likeIfPresent(GeocodingRegionDO::getDataSource, reqVO.getDataSource())
                .betweenIfPresent(GeocodingRegionDO::getInitDate, reqVO.getInitDate())
                .betweenIfPresent(GeocodingRegionDO::getUpdateDate, reqVO.getUpdateDate())
                .likeIfPresent(GeocodingRegionDO::getRemark, reqVO.getRemark())
                .likeIfPresent(GeocodingRegionDO::getExtCategory1, reqVO.getExtCategory1())
                .likeIfPresent(GeocodingRegionDO::getExtCategory2, reqVO.getExtCategory2())
                .likeIfPresent(GeocodingRegionDO::getExtCategory3, reqVO.getExtCategory3())
                .betweenIfPresent(GeocodingRegionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GeocodingRegionDO::getId));
    }

}