package cn.iocoder.yudao.module.smartcity.controller.admin.vehicleinformation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 车辆信息新增/修改 Request VO")
@Data
public class VehicleInformationSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "8025")
    private Long id;

    @Schema(description = "车牌号")
    private String licensePlateNumber;

    @Schema(description = "车辆品牌")
    private String vehicleBrand;

    @Schema(description = "车辆型号")
    private String model;

    @Schema(description = "车辆类型", example = "2")
    private String vehicleType;

    @Schema(description = "车架号")
    private String vin;

    @Schema(description = "发动机号")
    private String engineNo;

    @Schema(description = "注册日期")
    private LocalDateTime registrationDate;

    @Schema(description = "登记车主姓名", example = "芋艿")
    private String registerTheName;

    @Schema(description = "车主身份证号")
    private String ownerIdNumberNumber;

    @Schema(description = "车主联系电话")
    private String carOwnerPhoneNumber;

    @Schema(description = "车辆颜色")
    private String vehicleColor;

    @Schema(description = "座位数")
    private String seats;

    @Schema(description = "出厂日期")
    private String dateOfProduction;

    @Schema(description = "购置价格", example = "186")
    private String purchasePrice;

    @Schema(description = "使用性质")
    private String natureOfUse;

    @Schema(description = "年检有效期")
    private String annualInspectionPeriod;

    @Schema(description = "保险有效期")
    private String periodOfInsurance;

    @Schema(description = "保险类型")
    private String typesOfInsurance;

    @Schema(description = "环保排放标准")
    private String environmentalStandards;

    @Schema(description = "车辆用途")
    private String vehicleUsage;

}