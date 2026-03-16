package cn.iocoder.yudao.module.envirhealth.dal.mysql.vehicle;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.vehicle.VehiclePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.VehicleDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.detail.VehicleDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 车辆 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface VehicleMapper extends BaseMapperX<VehicleDO> {

    default PageResult<VehicleDO> selectPage(VehiclePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<VehicleDO>()
                .eqIfPresent(VehicleDO::getSysVehicleId, reqVO.getSysVehicleId())
                .eqIfPresent(VehicleDO::getLicensePlate, reqVO.getLicensePlate())
                .eqIfPresent(VehicleDO::getVehicleTypeId, reqVO.getVehicleTypeId())
                .eqIfPresent(VehicleDO::getModel, reqVO.getModel())
                .eqIfPresent(VehicleDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(VehicleDO::getRouteId, reqVO.getRouteId())
                .eqIfPresent(VehicleDO::getMaintenanceCycle, reqVO.getMaintenanceCycle())
                .eqIfPresent(VehicleDO::getDriverId, reqVO.getDriverId())
                .eqIfPresent(VehicleDO::getVehicleStatusId, reqVO.getVehicleStatusId())
                .eqIfPresent(VehicleDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(VehicleDO::getAbnormalCreateTime, reqVO.getAbnormalCreateTime())
                .betweenIfPresent(VehicleDO::getAbnormalUpdateTime, reqVO.getAbnormalUpdateTime())
                .eqIfPresent(VehicleDO::getTotalWorkHours, reqVO.getTotalWorkHours())
                .betweenIfPresent(VehicleDO::getLastMaintenanceTime, reqVO.getLastMaintenanceTime())
                .eqIfPresent(VehicleDO::getAlarmCount, reqVO.getAlarmCount())
                .eqIfPresent(VehicleDO::getVehiclePhotoUrl, reqVO.getVehiclePhotoUrl())
                .eqIfPresent(VehicleDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(VehicleDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(VehicleDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(VehicleDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(VehicleDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(VehicleDO::getId));
    }

    List<VehicleDetailDO> selectDetailPage(@Param("reqVO") VehiclePageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") VehiclePageReqVO pageReqVO);
}