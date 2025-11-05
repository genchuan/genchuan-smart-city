package cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodingquality;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingquality.vo.GeocodingQualityPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingquality.GeocodingQualityDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 地理编码数据质量统计报表 Mapper
 *
 * @author zcq
 */
@Mapper
public interface GeocodingQualityMapper extends BaseMapperX<GeocodingQualityDO> {

    default PageResult<GeocodingQualityDO> selectPage(GeocodingQualityPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GeocodingQualityDO>()
                .eqIfPresent(GeocodingQualityDO::getQualityStatId, reqVO.getQualityStatId())
                .likeIfPresent(GeocodingQualityDO::getStatCycle, reqVO.getStatCycle())
                .betweenIfPresent(GeocodingQualityDO::getStatTime, reqVO.getStatTime())
                .likeIfPresent(GeocodingQualityDO::getAdminCode, reqVO.getAdminCode())
                .likeIfPresent(GeocodingQualityDO::getAdminName, reqVO.getAdminName())
                .eqIfPresent(GeocodingQualityDO::getTotalCheckCount, reqVO.getTotalCheckCount())
                .eqIfPresent(GeocodingQualityDO::getAttributeIntegrityRate, reqVO.getAttributeIntegrityRate())
                .eqIfPresent(GeocodingQualityDO::getPositionAccuracyRate, reqVO.getPositionAccuracyRate())
                .eqIfPresent(GeocodingQualityDO::getDataUpdateTimelyRate, reqVO.getDataUpdateTimelyRate())
                .eqIfPresent(GeocodingQualityDO::getAttributeErrorCount, reqVO.getAttributeErrorCount())
                .eqIfPresent(GeocodingQualityDO::getPositionErrorCount, reqVO.getPositionErrorCount())
                .eqIfPresent(GeocodingQualityDO::getUpdateDelayedCount, reqVO.getUpdateDelayedCount())
                .eqIfPresent(GeocodingQualityDO::getQualityImprovementSuggestion, reqVO.getQualityImprovementSuggestion())
                .eqIfPresent(GeocodingQualityDO::getStatUser, reqVO.getStatUser())
                .eqIfPresent(GeocodingQualityDO::getRemark, reqVO.getRemark())
                .eqIfPresent(GeocodingQualityDO::getExtCategory1, reqVO.getExtCategory1())
                .eqIfPresent(GeocodingQualityDO::getExtCategory2, reqVO.getExtCategory2())
                .eqIfPresent(GeocodingQualityDO::getExtCategory3, reqVO.getExtCategory3())
                .betweenIfPresent(GeocodingQualityDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GeocodingQualityDO::getId));
    }

}