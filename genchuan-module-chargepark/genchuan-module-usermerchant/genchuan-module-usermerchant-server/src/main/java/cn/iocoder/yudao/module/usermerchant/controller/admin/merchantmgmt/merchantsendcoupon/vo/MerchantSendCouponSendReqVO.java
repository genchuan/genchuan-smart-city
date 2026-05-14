package cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantsendcoupon.vo;

import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.FlexibleTimestampDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 商户发券 Request VO")
@Data
public class MerchantSendCouponSendReqVO {

    @Schema(description = "商户ID", required = true, example = "1")
    @NotNull(message = "商户ID不能为空")
    private Long merchantId;

    @Schema(description = "优惠券ID", required = true, example = "101")
    @NotNull(message = "优惠券ID不能为空")
    private Long couponId;

    @Schema(description = "发放数量", required = true, example = "500")
    @NotNull(message = "发放数量不能为空")
    private Integer sendCount;

    @Schema(description = "执行时间", example = "2025-12-31 23:59:59")
    @JsonDeserialize(using = FlexibleTimestampDeserializer.class)
    private LocalDateTime execTime;

    @Schema(description = "备注", example = "新用户营销发券")
    private String remark;
}