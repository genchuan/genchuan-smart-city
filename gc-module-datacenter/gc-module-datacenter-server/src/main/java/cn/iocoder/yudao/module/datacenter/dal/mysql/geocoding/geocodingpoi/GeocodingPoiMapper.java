package cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodingpoi;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingpoi.vo.GeocodingPoiPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingpoi.GeocodingPoiDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 兴趣点数据管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface GeocodingPoiMapper extends BaseMapperX<GeocodingPoiDO> {

    default PageResult<GeocodingPoiDO> selectPage(GeocodingPoiPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GeocodingPoiDO>()
                .eqIfPresent(GeocodingPoiDO::getPoiId, reqVO.getPoiId())
                .likeIfPresent(GeocodingPoiDO::getPoiName, reqVO.getPoiName())
                .eqIfPresent(GeocodingPoiDO::getPoiCode, reqVO.getPoiCode())
                .eqIfPresent(GeocodingPoiDO::getIntraCode, reqVO.getIntraCode())
                .eqIfPresent(GeocodingPoiDO::getBdGridCode, reqVO.getBdGridCode())
                .likeIfPresent(GeocodingPoiDO::getOldName, reqVO.getOldName())
                .eqIfPresent(GeocodingPoiDO::getFloor, reqVO.getFloor())
                .eqIfPresent(GeocodingPoiDO::getCoordX, reqVO.getCoordX())
                .eqIfPresent(GeocodingPoiDO::getCoordY, reqVO.getCoordY())
                .eqIfPresent(GeocodingPoiDO::getPoiType, reqVO.getPoiType())
                .eqIfPresent(GeocodingPoiDO::getDataSource, reqVO.getDataSource())
                .betweenIfPresent(GeocodingPoiDO::getInitDate, reqVO.getInitDate())
                .betweenIfPresent(GeocodingPoiDO::getUpdateDate, reqVO.getUpdateDate())
                .eqIfPresent(GeocodingPoiDO::getRemark, reqVO.getRemark())
                .eqIfPresent(GeocodingPoiDO::getExtCategory1, reqVO.getExtCategory1())
                .eqIfPresent(GeocodingPoiDO::getExtCategory2, reqVO.getExtCategory2())
                .eqIfPresent(GeocodingPoiDO::getExtCategory3, reqVO.getExtCategory3())
                .betweenIfPresent(GeocodingPoiDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GeocodingPoiDO::getId));
    }

}