package cn.iocoder.yudao.module.park.controller.admin.park.pricing.coupon.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 使用优惠券 req VO")
@Data
public class UseCouponReqVO {
    @Schema(description = "[订单记录Id]", example = "1")
    private Long orderTempId;
    @Schema(description = "[优惠券Id]", example = "1")
    private Long couponId;
}
