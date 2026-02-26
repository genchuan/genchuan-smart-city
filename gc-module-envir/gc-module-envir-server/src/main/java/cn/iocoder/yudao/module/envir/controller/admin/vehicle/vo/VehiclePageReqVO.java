package cn.iocoder.yudao.module.envir.controller.admin.vehicle.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 车辆分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class VehiclePageReqVO extends PageParam {

    @Schema(description = "业务主键（UUID）", example = "17302")
    private String sysVehicleId;

    @Schema(description = "车牌号码")
    private String licensePlate;

    @Schema(description = "车辆类型（关联sys_vehicle_type.sys_vehicle_type_id）", example = "2021")
    private String vehicleTypeId;

    @Schema(description = "车辆型号")
    private String model;

    @Schema(description = "所属部门（关联sys_dept.sys_dept_id）", example = "20491")
    private String deptId;

    @Schema(description = "作业路线（关联sys_route.sys_route_id）", example = "17411")
    private String routeId;

    @Schema(description = "维护周期（单位：天）")
    private Integer maintenanceCycle;

    @Schema(description = "驾驶员（关联sys_user.id）", example = "32257")
    private String driverId;

    @Schema(description = "车辆状态（关联sys_vehicle_status.sys_vehicle_status_id）", example = "12523")
    private String vehicleStatusId;

    @Schema(description = "业务创建人（关联sys_user.id）")
    private String abnormalCreateBy;

    @Schema(description = "业务创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] abnormalCreateTime;

    @Schema(description = "业务更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] abnormalUpdateTime;

    @Schema(description = "累计作业时长")
    private BigDecimal totalWorkHours;

    @Schema(description = "最近维护时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastMaintenanceTime;

    @Schema(description = "违规告警次数", example = "31929")
    private Integer alarmCount;

    @Schema(description = "车辆照片URL", example = "https://www.iocoder.cn")
    private String vehiclePhotoUrl;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}