package cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcementvehicle.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 执法车辆管理新增/修改 Request VO")
@Data
public class LawEnforcementVehicleSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "27157")
    private Long id;

    @Schema(description = "车牌号")
    private String licensePlateNumber;

    @Schema(description = "车辆品牌")
    private String vehicleBrand;

    @Schema(description = "车辆型号")
    private String model;

    @Schema(description = "车辆颜色", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车辆颜色不能为空")
    private String vehicleColor;

    @Schema(description = "车架号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车架号不能为空")
    private String vin;

    @Schema(description = "发动机号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "发动机号不能为空")
    private String engineNo;

    @Schema(description = "购置时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "购置时间不能为空")
    private String purchaseTime;

    @Schema(description = "登记注册日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "登记注册日期不能为空")
    private LocalDateTime registrationDate;

    @Schema(description = "所属执法部门", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所属执法部门不能为空")
    private String belongingDepartment;

    @Schema(description = "使用性质", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "使用性质不能为空")
    private String natureOfUse;

    @Schema(description = "车辆用途", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车辆用途不能为空")
    private String vehicleUsage;

    @Schema(description = "车辆状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "车辆状态不能为空")
    private String vehicleStatus;

    @Schema(description = "年检到期日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "年检到期日期不能为空")
    private LocalDateTime annualInspectionDate;

    @Schema(description = "保险截止日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "保险截止日期不能为空")
    private LocalDateTime insuranceDeadline;

    @Schema(description = "保险类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "保险类型不能为空")
    private String typesOfInsurance;

    @Schema(description = "驾驶员姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "驾驶员姓名不能为空")
    private String driverName;

    @Schema(description = "驾驶员联系方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "驾驶员联系方式不能为空")
    private String driverContactInformation;

    @Schema(description = "行驶里程", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行驶里程不能为空")
    private String mileage;

    @Schema(description = "维修记录", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "维修记录不能为空")
    private String maintenanceRecord;

    @Schema(description = "保养记录", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "保养记录不能为空")
    private String maintenanceRecords;

    @Schema(description = "加油记录", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "加油记录不能为空")
    private String refuelingRecord;

    @Schema(description = "违章记录", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "违章记录不能为空")
    private String violationRecords;

}