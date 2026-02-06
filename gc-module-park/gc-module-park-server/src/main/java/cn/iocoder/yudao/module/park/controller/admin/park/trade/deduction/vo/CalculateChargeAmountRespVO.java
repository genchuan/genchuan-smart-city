package cn.iocoder.yudao.module.park.controller.admin.park.trade.deduction.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 计算收费金额 Response VO")
@Data
public class CalculateChargeAmountRespVO {
    @Schema(description = "原始收费金额（元）", example = "100")
    BigDecimal originalAmount;

    @Schema(description = "收费金额（元）", example = "80")
    BigDecimal chargeAmount;


    @Schema(description = "优惠金额（元）", example = "20")
    BigDecimal discountAmount;
}
