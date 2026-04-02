package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderrefund.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 订单退款重新申请 Request VO")
@Data
public class OrderRefundReapplyReqVO {

    @Schema(description = "原退款申请 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "原退款申请 ID 不能为空")
    private Long id;

    @Schema(description = "新退款金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "新退款金额不能为空")
    private BigDecimal refundAmount;

    @Schema(description = "新退款原因", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "新退款原因不能为空")
    private String refundReason;
}