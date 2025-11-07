package cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodingupdatecycle;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingupdatecycle.vo.GeocodingUpdatecyclePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingupdatecycle.GeocodingUpdatecycleDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据更新周期配置 Mapper
 *
 * @author zcq
 */
@Mapper
public interface GeocodingUpdatecycleMapper extends BaseMapperX<GeocodingUpdatecycleDO> {

    default PageResult<GeocodingUpdatecycleDO> selectPage(GeocodingUpdatecyclePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GeocodingUpdatecycleDO>()
                .eqIfPresent(GeocodingUpdatecycleDO::getUpdateCycleId, reqVO.getUpdateCycleId())
                .eqIfPresent(GeocodingUpdatecycleDO::getUpdateObjectType, reqVO.getUpdateObjectType())
                .eqIfPresent(GeocodingUpdatecycleDO::getRegularCycle, reqVO.getRegularCycle())
                .eqIfPresent(GeocodingUpdatecycleDO::getSpecialTriggerCondition, reqVO.getSpecialTriggerCondition())
                .eqIfPresent(GeocodingUpdatecycleDO::getUpdateDept, reqVO.getUpdateDept())
                .eqIfPresent(GeocodingUpdatecycleDO::getUpdateProcessDesc, reqVO.getUpdateProcessDesc())
                .eqIfPresent(GeocodingUpdatecycleDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(GeocodingUpdatecycleDO::getConfigUser, reqVO.getConfigUser())
                .eqIfPresent(GeocodingUpdatecycleDO::getConfigTime, reqVO.getConfigTime())
                .eqIfPresent(GeocodingUpdatecycleDO::getRemark, reqVO.getRemark())
                .eqIfPresent(GeocodingUpdatecycleDO::getExtCategory1, reqVO.getExtCategory1())
                .eqIfPresent(GeocodingUpdatecycleDO::getExtCategory2, reqVO.getExtCategory2())
                .eqIfPresent(GeocodingUpdatecycleDO::getExtCategory3, reqVO.getExtCategory3())
                .betweenIfPresent(GeocodingUpdatecycleDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GeocodingUpdatecycleDO::getId));
    }

}