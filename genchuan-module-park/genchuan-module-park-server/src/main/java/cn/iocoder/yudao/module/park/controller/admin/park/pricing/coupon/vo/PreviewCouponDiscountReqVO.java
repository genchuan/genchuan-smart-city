package cn.iocoder.yudao.module.park.controller.admin.park.pricing.coupon.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 优惠券优惠金额预览 req VO")
@Data
public class PreviewCouponDiscountReqVO {
    @Schema(description = "[优惠券id]")
    @NotNull(message = "优惠券ID不能为空")
    private Long couponId;

    @Schema(description = "[订单 id]")
    @NotNull(message = "订单 id")
    private Long orderId;
}
