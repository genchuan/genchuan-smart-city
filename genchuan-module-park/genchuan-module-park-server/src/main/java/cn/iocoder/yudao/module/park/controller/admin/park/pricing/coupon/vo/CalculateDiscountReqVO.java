package cn.iocoder.yudao.module.park.controller.admin.park.pricing.coupon.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 优惠计算 req VO")
@Data
public class CalculateDiscountReqVO {
    @Schema(description = "[优惠券id]")
    @NotNull(message = "优惠券ID不能为空")
    private Long couponId;

    @Schema(description = "初始总金额", example = "100.00")
    @NotNull(message = "初始总金额 不能为空")
    private BigDecimal originalAmount;
}
