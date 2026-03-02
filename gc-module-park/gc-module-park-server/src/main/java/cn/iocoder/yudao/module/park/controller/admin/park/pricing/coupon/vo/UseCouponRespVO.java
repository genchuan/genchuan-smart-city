package cn.iocoder.yudao.module.park.controller.admin.park.pricing.coupon.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 使用优惠券 resp VO")
@Data
public class UseCouponRespVO {
    @Schema(description = "是否成功标识", example = "true")
    private Boolean isSuccess;

    @Schema(description = "失败原因", example = "优惠券不存在")
    private String failureReason;
}
