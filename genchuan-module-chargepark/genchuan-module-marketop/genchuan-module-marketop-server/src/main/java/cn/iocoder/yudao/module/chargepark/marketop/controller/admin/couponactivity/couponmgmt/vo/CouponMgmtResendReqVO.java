package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.couponmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 优惠券重新发放 Request VO")
@Data
public class CouponMgmtResendReqVO {

    @Schema(description = "优惠券ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "优惠券ID不能为空")
    private Long id;

    @Schema(description = "领取人用户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "领取人用户ID不能为空")
    private Long receiverId;

    @Schema(description = "新的有效期（时间戳毫秒）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "新的有效期不能为空")
    private Long newValidTime;

}
