package cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.vehicle;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "环境卫生管理 - 车辆 Response VO")
@Data
@ExcelIgnoreUnannotated
public class VehicleRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "21313")
    @ExcelProperty("业务主键")
    private String sysVehicleId;

    @Schema(description = "车牌号码")
    @ExcelProperty("车牌号码")
    private String licensePlate;

    @Schema(description = "车辆类型（关联sys_vehicle_type.sys_vehicle_type_id）", example = "16606")
    @ExcelProperty("车辆类型")
    private String vehicleTypeId;

    @Schema(description = "车辆型号")
    @ExcelProperty("车辆型号")
    private String model;

    @Schema(description = "所属部门（关联sys_dept.sys_dept_id）", example = "30395")
    @ExcelProperty("所属部门")
    private String deptId;

    @Schema(description = "作业路线（关联sys_route.sys_route_id）", example = "5158")
    @ExcelProperty("作业路线")
    private String routeId;

    @Schema(description = "维护周期（单位：天）")
    @ExcelProperty("维护周期")
    private Integer maintenanceCycle;

    @Schema(description = "驾驶员（关联sys_user.id）", example = "15996")
    @ExcelProperty("驾驶员")
    private String driverId;

    @Schema(description = "车辆状态（关联sys_vehicle_status.sys_vehicle_status_id）", example = "17840")
    @ExcelProperty("车辆状态")
    private String vehicleStatusId;

    @Schema(description = "业务创建人（关联sys_user.id）")
    @ExcelProperty("业务创建人")
    private String createBy;

    @Schema(description = "业务创建时间")
    @ExcelProperty("业务创建时间")
    private LocalDateTime abnormalCreateTime;

    @Schema(description = "业务更新时间")
    @ExcelProperty("业务更新时间")
    private LocalDateTime abnormalUpdateTime;

    @Schema(description = "累计作业时长")
    @ExcelProperty("累计作业时长")
    private BigDecimal totalWorkHours;

    @Schema(description = "最近维护时间")
    @ExcelProperty("最近维护时间")
    private LocalDateTime lastMaintenanceTime;

    @Schema(description = "违规告警次数", example = "13424")
    @ExcelProperty("违规告警次数")
    private Integer alarmCount;

    @Schema(description = "车辆照片URL", example = "https://www.iocoder.cn")
    @ExcelProperty("车辆照片")
    private String vehiclePhotoUrl;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}