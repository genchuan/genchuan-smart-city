package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangeorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 兑换订单发货 Request VO")
@Data
public class ExchangeOrderDeliverReqVO {

    @Schema(description = "兑换订单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "兑换订单ID不能为空")
    private Long id;

    @Schema(description = "快递单号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "快递单号不能为空")
    private String logisticsInfo;

}
