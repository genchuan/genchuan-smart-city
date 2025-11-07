package cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcementvehicle.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 执法车辆管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class LawEnforcementVehicleRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "27157")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "车牌号")
    @ExcelProperty("车牌号")
    private String licensePlateNumber;

    @Schema(description = "车辆品牌")
    @ExcelProperty("车辆品牌")
    private String vehicleBrand;

    @Schema(description = "车辆型号")
    @ExcelProperty("车辆型号")
    private String model;

    @Schema(description = "车辆颜色", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车辆颜色")
    private String vehicleColor;

    @Schema(description = "车架号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车架号")
    private String vin;

    @Schema(description = "发动机号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发动机号")
    private String engineNo;

    @Schema(description = "购置时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("购置时间")
    private String purchaseTime;

    @Schema(description = "登记注册日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("登记注册日期")
    private LocalDateTime registrationDate;

    @Schema(description = "所属执法部门", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所属执法部门")
    private String belongingDepartment;

    @Schema(description = "使用性质", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("使用性质")
    private String natureOfUse;

    @Schema(description = "车辆用途", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车辆用途")
    private String vehicleUsage;

    @Schema(description = "车辆状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("车辆状态")
    private String vehicleStatus;

    @Schema(description = "年检到期日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("年检到期日期")
    private LocalDateTime annualInspectionDate;

    @Schema(description = "保险截止日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("保险截止日期")
    private LocalDateTime insuranceDeadline;

    @Schema(description = "保险类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("保险类型")
    private String typesOfInsurance;

    @Schema(description = "驾驶员姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("驾驶员姓名")
    private String driverName;

    @Schema(description = "驾驶员联系方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("驾驶员联系方式")
    private String driverContactInformation;

    @Schema(description = "行驶里程", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行驶里程")
    private String mileage;

    @Schema(description = "维修记录", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("维修记录")
    private String maintenanceRecord;

    @Schema(description = "保养记录", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("保养记录")
    private String maintenanceRecords;

    @Schema(description = "加油记录", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("加油记录")
    private String refuelingRecord;

    @Schema(description = "违章记录", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("违章记录")
    private String violationRecords;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}