package cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodingstat;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingstat.vo.GeocodingStatPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingstat.GeocodingStatDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 基本地点数据统计报表 Mapper
 *
 * @author zcq
 */
@Mapper
public interface GeocodingStatMapper extends BaseMapperX<GeocodingStatDO> {

    default PageResult<GeocodingStatDO> selectPage(GeocodingStatPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GeocodingStatDO>()
                .eqIfPresent(GeocodingStatDO::getStatId, reqVO.getStatId())
                .eqIfPresent(GeocodingStatDO::getStatCycle, reqVO.getStatCycle())
                .eqIfPresent(GeocodingStatDO::getStatTime, reqVO.getStatTime())
                .eqIfPresent(GeocodingStatDO::getAdminCode, reqVO.getAdminCode())
                .likeIfPresent(GeocodingStatDO::getAdminName, reqVO.getAdminName())
                .eqIfPresent(GeocodingStatDO::getRegionCount, reqVO.getRegionCount())
                .eqIfPresent(GeocodingStatDO::getZoneCount, reqVO.getZoneCount())
                .eqIfPresent(GeocodingStatDO::getStreetCount, reqVO.getStreetCount())
                .eqIfPresent(GeocodingStatDO::getHouseCount, reqVO.getHouseCount())
                .eqIfPresent(GeocodingStatDO::getPoiCount, reqVO.getPoiCount())
                .eqIfPresent(GeocodingStatDO::getNewDataCount, reqVO.getNewDataCount())
                .eqIfPresent(GeocodingStatDO::getUpdateDataCount, reqVO.getUpdateDataCount())
                .eqIfPresent(GeocodingStatDO::getStatUser, reqVO.getStatUser())
                .eqIfPresent(GeocodingStatDO::getRemark, reqVO.getRemark())
                .eqIfPresent(GeocodingStatDO::getExtCategory1, reqVO.getExtCategory1())
                .eqIfPresent(GeocodingStatDO::getExtCategory2, reqVO.getExtCategory2())
                .eqIfPresent(GeocodingStatDO::getExtCategory3, reqVO.getExtCategory3())
                .betweenIfPresent(GeocodingStatDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GeocodingStatDO::getId));
    }

}