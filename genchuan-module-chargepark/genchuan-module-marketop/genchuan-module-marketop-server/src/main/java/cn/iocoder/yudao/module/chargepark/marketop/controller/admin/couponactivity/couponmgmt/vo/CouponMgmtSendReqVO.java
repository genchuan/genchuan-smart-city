package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.couponmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 优惠券发放 Request VO")
@Data
public class CouponMgmtSendReqVO {

    @Schema(description = "优惠券ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "优惠券ID不能为空")
    private Long id;

    @Schema(description = "用户ID列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "用户ID列表不能为空")
    private Long receiverId;

}
