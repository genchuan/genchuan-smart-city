package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangeorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 兑换订单取消 Request VO")
@Data
public class ExchangeOrderCancelReqVO {

    @Schema(description = "兑换订单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "兑换订单ID不能为空")
    private Long id;

    @Schema(description = "取消原因", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "取消原因不能为空")
    private String cancelReason;
}
