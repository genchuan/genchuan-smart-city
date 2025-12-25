package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkpay.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 停车缴费 支付 Request VO")
@Data
public class ParkPayPayReqVO {

    @Schema(description = "缴费记录ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "缴费记录ID不能为空")
    private Long id;

    @Schema(description = "支付方式（微信支付/支付宝支付/银行卡支付）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "支付方式不能为空")
    private String payMethod;

    @Schema(description = "实际支付金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "支付金额不能为空")
    private BigDecimal actualPayAmount;
}
