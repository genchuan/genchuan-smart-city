package cn.iocoder.yudao.module.envirhealth.dal.mysql.vehicle;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.vehicletype.VehicleTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.VehicleTypeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 车辆类型字典 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface VehicleTypeMapper extends BaseMapperX<VehicleTypeDO> {

    default PageResult<VehicleTypeDO> selectPage(VehicleTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<VehicleTypeDO>()
                .eqIfPresent(VehicleTypeDO::getSysVehicleTypeId, reqVO.getSysVehicleTypeId())
                .likeIfPresent(VehicleTypeDO::getName, reqVO.getName())
                .eqIfPresent(VehicleTypeDO::getCode, reqVO.getCode())
                .eqIfPresent(VehicleTypeDO::getStatus, reqVO.getStatus())
                .eqIfPresent(VehicleTypeDO::getDescription, reqVO.getDescription())
                .eqIfPresent(VehicleTypeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(VehicleTypeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(VehicleTypeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(VehicleTypeDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(VehicleTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(VehicleTypeDO::getId));
    }

}