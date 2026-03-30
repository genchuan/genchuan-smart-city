package cn.iocoder.yudao.module.park.controller.admin.park.pricing.coupon.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 取消使用优惠券 req VO")
@Data
public class CancelUseCouponRespVO {
    @Schema(description = "是否成功标识", example = "true")
    private Boolean isSuccess;

    @Schema(description = "失败原因", example = "优惠券不存在")
    private String failureReason;
}
