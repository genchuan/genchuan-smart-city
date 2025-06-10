package cn.iocoder.yudao.module.smartcity.controller.admin.parkingfee.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 停车收费管理新增/修改 Request VO")
@Data
public class ParkingFeeSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "22566")
    private Long id;

    @Schema(description = "车牌号")
    private String licensePlateNumber;

    @Schema(description = "入场时间")
    private LocalDateTime admissionTime;

    @Schema(description = "出场时间")
    private LocalDateTime exitTime;

    @Schema(description = "停车时长")
    private String parkingDuration;

    @Schema(description = "收费金额")
    private String feeAmount;

    @Schema(description = "缴费方式")
    private String paymentMethod;

    @Schema(description = "实收金额")
    private String actualReceivedAmount;

    @Schema(description = "车位编号")
    private String parkingSpaceNumber;

}