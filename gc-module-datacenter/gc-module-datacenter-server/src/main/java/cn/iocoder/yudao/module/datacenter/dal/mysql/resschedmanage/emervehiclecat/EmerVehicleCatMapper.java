package cn.iocoder.yudao.module.datacenter.dal.mysql.resschedmanage.emervehiclecat;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emervehiclecat.vo.EmerVehicleCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emervehiclecat.EmerVehicleCatDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 救援车辆分类配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface EmerVehicleCatMapper extends BaseMapperX<EmerVehicleCatDO> {

    default PageResult<EmerVehicleCatDO> selectPage(EmerVehicleCatPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EmerVehicleCatDO>()
                .eqIfPresent(EmerVehicleCatDO::getVehicleCatId, reqVO.getVehicleCatId())
                .eqIfPresent(EmerVehicleCatDO::getParentCatId, reqVO.getParentCatId())
                .eqIfPresent(EmerVehicleCatDO::getCatLevel, reqVO.getCatLevel())
                .eqIfPresent(EmerVehicleCatDO::getCatCode, reqVO.getCatCode())
                .likeIfPresent(EmerVehicleCatDO::getCatName, reqVO.getCatName())
                .eqIfPresent(EmerVehicleCatDO::getCatDesc, reqVO.getCatDesc())
                .eqIfPresent(EmerVehicleCatDO::getApplyRegionCode, reqVO.getApplyRegionCode())
                .likeIfPresent(EmerVehicleCatDO::getApplyRegionName, reqVO.getApplyRegionName())
                .orderByDesc(EmerVehicleCatDO::getId));
    }

}