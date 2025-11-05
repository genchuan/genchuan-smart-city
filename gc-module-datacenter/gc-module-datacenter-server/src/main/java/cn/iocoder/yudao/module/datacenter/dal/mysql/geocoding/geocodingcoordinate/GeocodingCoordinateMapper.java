package cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodingcoordinate;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingcoordinate.vo.GeocodingCoordinatePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingcoordinate.GeocodingCoordinateDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 坐标系配置 Mapper
 *
 * @author zcq
 */
@Mapper
public interface GeocodingCoordinateMapper extends BaseMapperX<GeocodingCoordinateDO> {

    default PageResult<GeocodingCoordinateDO> selectPage(GeocodingCoordinatePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GeocodingCoordinateDO>()
                .likeIfPresent(GeocodingCoordinateDO::getCoordinateId, reqVO.getCoordinateId())
                .likeIfPresent(GeocodingCoordinateDO::getCoordinateName, reqVO.getCoordinateName())
                .likeIfPresent(GeocodingCoordinateDO::getCoordinateCode, reqVO.getCoordinateCode())
                .eqIfPresent(GeocodingCoordinateDO::getEnableStatus, reqVO.getEnableStatus())
                .likeIfPresent(GeocodingCoordinateDO::getConfigUser, reqVO.getConfigUser())
                .betweenIfPresent(GeocodingCoordinateDO::getConfigTime, reqVO.getConfigTime())
                .likeIfPresent(GeocodingCoordinateDO::getRemark, reqVO.getRemark())
                .likeIfPresent(GeocodingCoordinateDO::getExtCategory1, reqVO.getExtCategory1())
                .likeIfPresent(GeocodingCoordinateDO::getExtCategory2, reqVO.getExtCategory2())
                .likeIfPresent(GeocodingCoordinateDO::getExtCategory3, reqVO.getExtCategory3())
                .betweenIfPresent(GeocodingCoordinateDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GeocodingCoordinateDO::getId));
    }

}