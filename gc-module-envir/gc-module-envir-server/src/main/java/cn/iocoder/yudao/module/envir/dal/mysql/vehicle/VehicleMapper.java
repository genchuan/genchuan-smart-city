package cn.iocoder.yudao.module.envir.dal.mysql.vehicle;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envir.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.user.UserDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.route.RouteDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.vehicle.VehicleDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.vehicle.VehicleDetailDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.vehicletype.VehicleTypeDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.vehiclestatus.VehicleStatusDO;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.envir.controller.admin.vehicle.vo.*;

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
                .eqIfPresent(VehicleDO::getAbnormalCreateBy, reqVO.getAbnormalCreateBy())
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

    default List<VehicleDetailDO> selectListDetail() {
        return selectJoinList(VehicleDetailDO.class, new MPJLambdaWrapper<VehicleDO>()
                .selectAll(VehicleDO.class)
                .selectAs(VehicleTypeDO::getName, VehicleDetailDO::getVehicleTypeName)
                .selectAs(DeptDO::getName, VehicleDetailDO::getDeptName)
                .selectAs(RouteDO::getName, VehicleDetailDO::getRouteName)
                .selectAs(UserDO::getUserName, VehicleDetailDO::getDriverName)
                .selectAs(VehicleStatusDO::getName, VehicleDetailDO::getVehicleStatusName)
                .leftJoin(VehicleTypeDO.class, VehicleTypeDO::getSysVehicleTypeId, VehicleDO::getVehicleTypeId)
                .leftJoin(DeptDO.class, DeptDO::getSysDeptId, VehicleDO::getDeptId)
                .leftJoin(RouteDO.class, RouteDO::getSysRouteId, VehicleDO::getRouteId)
                .leftJoin(UserDO.class, UserDO::getUserId, VehicleDO::getDriverId)
                .leftJoin(VehicleStatusDO.class, VehicleStatusDO::getSysVehicleStatusId, VehicleDO::getVehicleStatusId)
        );
    }
}