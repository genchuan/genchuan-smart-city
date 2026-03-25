package cn.iocoder.yudao.module.smartcity.controller.admin.vehicleinformation.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 车辆信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class VehicleInformationPageReqVO extends PageParam {

    @Schema(description = "车牌号")
    private String licensePlateNumber;

    @Schema(description = "车辆品牌")
    private String vehicleBrand;

    @Schema(description = "车辆型号")
    private String model;

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

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}