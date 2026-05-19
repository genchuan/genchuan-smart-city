package cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.parkingpayment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 停车缴费优惠 Request VO")
@Data
public class ParkingPaymentDiscountReqVO {

    @Schema(description = "账单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "账单ID不能为空")
    private Long id;

    @Schema(description = "优惠金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "优惠金额不能为空")
    private BigDecimal discountAmount;

    @Schema(description = "优惠原因", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "优惠原因不能为空")
    private String discountReason;

}
