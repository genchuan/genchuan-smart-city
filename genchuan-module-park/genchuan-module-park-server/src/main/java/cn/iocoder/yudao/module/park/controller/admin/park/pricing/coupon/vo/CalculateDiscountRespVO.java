package cn.iocoder.yudao.module.park.controller.admin.park.pricing.coupon.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 优惠计算 req VO")
@Data
public class CalculateDiscountRespVO {

    @Schema(description = "优惠后金额", example = "80.00")
    private BigDecimal afterDiscountAmount;

    @Schema(description = "优惠金额", example = "20.00")
    private BigDecimal discountAmount;
}
