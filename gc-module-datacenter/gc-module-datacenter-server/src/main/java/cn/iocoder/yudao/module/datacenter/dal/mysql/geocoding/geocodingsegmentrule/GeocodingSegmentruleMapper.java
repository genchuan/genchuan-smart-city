package cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodingsegmentrule;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingsegmentrule.vo.GeocodingSegmentrulePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingsegmentrule.GeocodingSegmentruleDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 地理编码分段组合规则配置 Mapper
 *
 * @author zcq
 */
@Mapper
public interface GeocodingSegmentruleMapper extends BaseMapperX<GeocodingSegmentruleDO> {

    default PageResult<GeocodingSegmentruleDO> selectPage(GeocodingSegmentrulePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GeocodingSegmentruleDO>()
                .eqIfPresent(GeocodingSegmentruleDO::getSegmentRuleId, reqVO.getSegmentRuleId())
                .likeIfPresent(GeocodingSegmentruleDO::getLocationType, reqVO.getLocationType())
                .likeIfPresent(GeocodingSegmentruleDO::getSegmentRule, reqVO.getSegmentRule())
                .likeIfPresent(GeocodingSegmentruleDO::getCombineRule, reqVO.getCombineRule())
                .eqIfPresent(GeocodingSegmentruleDO::getDirectionOptions, reqVO.getDirectionOptions())
                .eqIfPresent(GeocodingSegmentruleDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(GeocodingSegmentruleDO::getConfigUser, reqVO.getConfigUser())
                .betweenIfPresent(GeocodingSegmentruleDO::getConfigTime, reqVO.getConfigTime())
                .eqIfPresent(GeocodingSegmentruleDO::getRemark, reqVO.getRemark())
                .eqIfPresent(GeocodingSegmentruleDO::getExtCategory1, reqVO.getExtCategory1())
                .eqIfPresent(GeocodingSegmentruleDO::getExtCategory2, reqVO.getExtCategory2())
                .eqIfPresent(GeocodingSegmentruleDO::getExtCategory3, reqVO.getExtCategory3())
                .betweenIfPresent(GeocodingSegmentruleDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GeocodingSegmentruleDO::getId));
    }

}