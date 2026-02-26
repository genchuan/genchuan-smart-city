package cn.iocoder.yudao.module.envir.dal.dataobject.vehicle;

import lombok.Data;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/12 9:48
 */
@Data
public class VehicleDetailDO extends VehicleDO {
    /**
     * 车辆类型（关联sys_vehicle_type.sys_vehicle_type_id）
     */
    private String vehicleTypeName;
    /**
     * 所属部门（关联sys_dept.sys_dept_id）
     */
    private String deptName;
    /**
     * 作业路线（关联sys_route.sys_route_id）
     */
    private String routeName;
    /**
     * 驾驶员（关联sys_user.id）
     */
    private String driverName;
    /**
     * 车辆状态（关联sys_vehicle_status.sys_vehicle_status_id）
     */
    private String vehicleStatusName;
}