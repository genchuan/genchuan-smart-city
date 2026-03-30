package cn.iocoder.yudao.module.park.controller.admin.park.pricing.coupon.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Schema(description = "管理后台 - 获取临停订单可用优惠券列表 VO")
@Data
public class ListOrderTempAvailableCouponReqVO {

    @Schema(description = "[订单记录Id]", example = "1")
    private Long orderTempId;

}
