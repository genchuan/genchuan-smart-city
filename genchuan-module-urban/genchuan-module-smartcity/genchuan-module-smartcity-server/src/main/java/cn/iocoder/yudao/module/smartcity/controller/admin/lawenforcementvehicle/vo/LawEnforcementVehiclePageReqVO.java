package cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcementvehicle.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 执法车辆管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LawEnforcementVehiclePageReqVO extends PageParam {

    @Schema(description = "车牌号")
    private String licensePlateNumber;

    @Schema(description = "车辆品牌")
    private String vehicleBrand;

    @Schema(description = "车辆型号")
    private String model;

    @Schema(description = "车辆颜色")
    private String vehicleColor;

    @Schema(description = "车架号")
    private String vin;

    @Schema(description = "发动机号")
    private String engineNo;

    @Schema(description = "购置时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private String[] purchaseTime;

    @Schema(description = "登记注册日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] registrationDate;

    @Schema(description = "所属执法部门")
    private String belongingDepartment;

    @Schema(description = "使用性质")
    private String natureOfUse;

    @Schema(description = "车辆用途")
    private String vehicleUsage;

    @Schema(description = "车辆状态", example = "1")
    private String vehicleStatus;

    @Schema(description = "年检到期日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] annualInspectionDate;

    @Schema(description = "保险截止日期")
    private LocalDateTime insuranceDeadline;

    @Schema(description = "保险类型")
    private String typesOfInsurance;

    @Schema(description = "驾驶员姓名", example = "张三")
    private String driverName;

    @Schema(description = "驾驶员联系方式")
    private String driverContactInformation;

    @Schema(description = "行驶里程")
    private String mileage;

    @Schema(description = "维修记录")
    private String maintenanceRecord;

    @Schema(description = "保养记录")
    private String maintenanceRecords;

    @Schema(description = "加油记录")
    private String refuelingRecord;

    @Schema(description = "违章记录")
    private String violationRecords;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}