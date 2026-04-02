package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderlist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 退款申请 Request VO")
@Data
public class OrderListRefundApplyReqVO extends OrderListSaveReqVO {

    @Schema(description = "退款金额", requiredMode = Schema.RequiredMode.REQUIRED, example = "45.75")
    @NotNull(message = "退款金额不能为空")
    @DecimalMin(value = "0.01", message = "退款金额必须大于0")
    private BigDecimal refundAmount;

    @Schema(description = "退款原因", requiredMode = Schema.RequiredMode.REQUIRED, example = "充电异常，申请全额退款")
    @NotNull(message = "退款原因不能为空")
    private String refundReason;

}
