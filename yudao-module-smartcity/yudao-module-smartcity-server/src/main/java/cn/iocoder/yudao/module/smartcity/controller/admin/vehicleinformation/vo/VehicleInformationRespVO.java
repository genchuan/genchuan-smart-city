package cn.iocoder.yudao.module.smartcity.controller.admin.vehicleinformation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 车辆信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class VehicleInformationRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "8025")
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

    @Schema(description = "车辆类型", example = "2")
    @ExcelProperty("车辆类型")
    private String vehicleType;

    @Schema(description = "车架号")
    @ExcelProperty("车架号")
    private String vin;

    @Schema(description = "发动机号")
    @ExcelProperty("发动机号")
    private String engineNo;

    @Schema(description = "注册日期")
    @ExcelProperty("注册日期")
    private LocalDateTime registrationDate;

    @Schema(description = "登记车主姓名", example = "芋艿")
    @ExcelProperty("登记车主姓名")
    private String registerTheName;

    @Schema(description = "车主身份证号")
    @ExcelProperty("车主身份证号")
    private String ownerIdNumberNumber;

    @Schema(description = "车主联系电话")
    @ExcelProperty("车主联系电话")
    private String carOwnerPhoneNumber;

    @Schema(description = "车辆颜色")
    @ExcelProperty("车辆颜色")
    private String vehicleColor;

    @Schema(description = "座位数")
    @ExcelProperty("座位数")
    private String seats;

    @Schema(description = "出厂日期")
    @ExcelProperty("出厂日期")
    private String dateOfProduction;

    @Schema(description = "购置价格", example = "186")
    @ExcelProperty("购置价格")
    private String purchasePrice;

    @Schema(description = "使用性质")
    @ExcelProperty("使用性质")
    private String natureOfUse;

    @Schema(description = "年检有效期")
    @ExcelProperty("年检有效期")
    private String annualInspectionPeriod;

    @Schema(description = "保险有效期")
    @ExcelProperty("保险有效期")
    private String periodOfInsurance;

    @Schema(description = "保险类型")
    @ExcelProperty("保险类型")
    private String typesOfInsurance;

    @Schema(description = "环保排放标准")
    @ExcelProperty("环保排放标准")
    private String environmentalStandards;

    @Schema(description = "车辆用途")
    @ExcelProperty("车辆用途")
    private String vehicleUsage;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}