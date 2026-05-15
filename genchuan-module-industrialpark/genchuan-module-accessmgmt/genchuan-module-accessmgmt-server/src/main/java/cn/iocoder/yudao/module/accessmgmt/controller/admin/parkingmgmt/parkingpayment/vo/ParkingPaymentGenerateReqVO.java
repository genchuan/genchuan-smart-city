package cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.parkingpayment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 停车缴费生成账单 Request VO")
@Data
public class ParkingPaymentGenerateReqVO {

    @Schema(description = "车牌号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "车牌号不能为空")
    private String plateNo;

    @Schema(description = "停车时长（分钟）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "停车时长不能为空")
    private Integer parkDuration;

    @Schema(description = "费用金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "费用金额不能为空")
    private BigDecimal feeAmount;

}
