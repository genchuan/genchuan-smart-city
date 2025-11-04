package cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodingaccuracy;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingaccuracy.vo.GeocodingAccuracyPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingaccuracy.GeocodingAccuracyDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 位置精度标准配置 Mapper
 *
 * @author zcq
 */
@Mapper
public interface GeocodingAccuracyMapper extends BaseMapperX<GeocodingAccuracyDO> {

    default PageResult<GeocodingAccuracyDO> selectPage(GeocodingAccuracyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GeocodingAccuracyDO>()
                .eqIfPresent(GeocodingAccuracyDO::getAccuracyConfigId, reqVO.getAccuracyConfigId())
                .eqIfPresent(GeocodingAccuracyDO::getTerrainType, reqVO.getTerrainType())
                .eqIfPresent(GeocodingAccuracyDO::getPositionErrorStd, reqVO.getPositionErrorStd())
                .eqIfPresent(GeocodingAccuracyDO::getMaxTolerance, reqVO.getMaxTolerance())
                .eqIfPresent(GeocodingAccuracyDO::getApplicableDataType, reqVO.getApplicableDataType())
                .eqIfPresent(GeocodingAccuracyDO::getScaleDesc, reqVO.getScaleDesc())
                .eqIfPresent(GeocodingAccuracyDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(GeocodingAccuracyDO::getConfigUser, reqVO.getConfigUser())
                .betweenIfPresent(GeocodingAccuracyDO::getConfigTime, reqVO.getConfigTime())
                .eqIfPresent(GeocodingAccuracyDO::getRemark, reqVO.getRemark())
                .eqIfPresent(GeocodingAccuracyDO::getExtCategory1, reqVO.getExtCategory1())
                .eqIfPresent(GeocodingAccuracyDO::getExtCategory2, reqVO.getExtCategory2())
                .eqIfPresent(GeocodingAccuracyDO::getExtCategory3, reqVO.getExtCategory3())
                .betweenIfPresent(GeocodingAccuracyDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GeocodingAccuracyDO::getId));
    }

}