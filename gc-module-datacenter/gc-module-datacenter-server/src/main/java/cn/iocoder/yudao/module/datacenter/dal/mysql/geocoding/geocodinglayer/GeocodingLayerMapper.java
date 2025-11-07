package cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodinglayer;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodinglayer.vo.GeocodingLayerPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodinglayer.GeocodingLayerDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 图层代码配置 Mapper
 *
 * @author zcq
 */
@Mapper
public interface GeocodingLayerMapper extends BaseMapperX<GeocodingLayerDO> {

    default PageResult<GeocodingLayerDO> selectPage(GeocodingLayerPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GeocodingLayerDO>()
                .likeIfPresent(GeocodingLayerDO::getLayerConfigId, reqVO.getLayerConfigId())
                .likeIfPresent(GeocodingLayerDO::getLayerCode, reqVO.getLayerCode())
                .likeIfPresent(GeocodingLayerDO::getLayerName, reqVO.getLayerName())
                .likeIfPresent(GeocodingLayerDO::getLayerDesc, reqVO.getLayerDesc())
                .eqIfPresent(GeocodingLayerDO::getEnableStatus, reqVO.getEnableStatus())
                .likeIfPresent(GeocodingLayerDO::getConfigUser, reqVO.getConfigUser())
                .betweenIfPresent(GeocodingLayerDO::getConfigTime, reqVO.getConfigTime())
                .likeIfPresent(GeocodingLayerDO::getExtCategory1, reqVO.getExtCategory1())
                .likeIfPresent(GeocodingLayerDO::getExtCategory2, reqVO.getExtCategory2())
                .likeIfPresent(GeocodingLayerDO::getExtCategory3, reqVO.getExtCategory3())
                .betweenIfPresent(GeocodingLayerDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GeocodingLayerDO::getId));
    }

}