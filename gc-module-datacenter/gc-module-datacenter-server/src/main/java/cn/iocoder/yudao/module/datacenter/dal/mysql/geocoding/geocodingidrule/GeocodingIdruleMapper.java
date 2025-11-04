package cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodingidrule;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingidrule.vo.GeocodingIdrulePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingidrule.GeocodingIdruleDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 标识码编码规则配置 Mapper
 *
 * @author zcq
 */
@Mapper
public interface GeocodingIdruleMapper extends BaseMapperX<GeocodingIdruleDO> {

    default PageResult<GeocodingIdruleDO> selectPage(GeocodingIdrulePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GeocodingIdruleDO>()
                .eqIfPresent(GeocodingIdruleDO::getIdRuleId, reqVO.getIdRuleId())
                .likeIfPresent(GeocodingIdruleDO::getRuleName, reqVO.getRuleName())
                .eqIfPresent(GeocodingIdruleDO::getAdminCodeLength, reqVO.getAdminCodeLength())
                .eqIfPresent(GeocodingIdruleDO::getStreetCodeLength, reqVO.getStreetCodeLength())
                .eqIfPresent(GeocodingIdruleDO::getLayerCodeLength, reqVO.getLayerCodeLength())
                .eqIfPresent(GeocodingIdruleDO::getSeqCodeLength, reqVO.getSeqCodeLength())
                .eqIfPresent(GeocodingIdruleDO::getSeqGenRule, reqVO.getSeqGenRule())
                .eqIfPresent(GeocodingIdruleDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(GeocodingIdruleDO::getConfigUser, reqVO.getConfigUser())
                .betweenIfPresent(GeocodingIdruleDO::getConfigTime, reqVO.getConfigTime())
                .eqIfPresent(GeocodingIdruleDO::getRemark, reqVO.getRemark())
                .eqIfPresent(GeocodingIdruleDO::getExtCategory1, reqVO.getExtCategory1())
                .eqIfPresent(GeocodingIdruleDO::getExtCategory2, reqVO.getExtCategory2())
                .eqIfPresent(GeocodingIdruleDO::getExtCategory3, reqVO.getExtCategory3())
                .betweenIfPresent(GeocodingIdruleDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GeocodingIdruleDO::getId));
    }

}