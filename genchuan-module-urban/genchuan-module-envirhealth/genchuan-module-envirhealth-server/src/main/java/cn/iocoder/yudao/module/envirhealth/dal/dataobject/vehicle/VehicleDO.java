package cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 车辆 DO
 *
 * @author 芋道源码
 */
@TableName("sys_vehicle")
@KeySequence("sys_vehicle_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 业务主键（UUID）
     */
    private String sysVehicleId;
    /**
     * 车牌号码
     */
    private String licensePlate;
    /**
     * 车辆类型（关联sys_vehicle_type.sys_vehicle_type_id）
     */
    private String vehicleTypeId;
    /**
     * 车辆型号
     */
    private String model;
    /**
     * 所属部门（关联sys_dept.sys_dept_id）
     */
    private String deptId;
    /**
     * 作业路线（关联sys_route.sys_route_id）
     */
    private String routeId;
    /**
     * 维护周期（单位：天）
     */
    private Integer maintenanceCycle;
    /**
     * 驾驶员（关联sys_user.id）
     */
    private String driverId;
    /**
     * 车辆状态（关联sys_vehicle_status.sys_vehicle_status_id）
     */
    private String vehicleStatusId;
    /**
     * 业务创建人（关联sys_user.id）
     */
    private String createBy;
    /**
     * 业务创建时间
     */
    private LocalDateTime abnormalCreateTime;
    /**
     * 业务更新时间
     */
    private LocalDateTime abnormalUpdateTime;
    /**
     * 累计作业时长
     */
    private BigDecimal totalWorkHours;
    /**
     * 最近维护时间
     */
    private LocalDateTime lastMaintenanceTime;
    /**
     * 违规告警次数
     */
    private Integer alarmCount;
    /**
     * 车辆照片URL
     */
    private String vehiclePhotoUrl;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    private String extCommon4;

    private String planStatusId;

    private String workStatusId;

    private String violationTypeId;

    private String violationStatusId;

    private String maintenanceTypeId;

    private String taskTypeId;
}