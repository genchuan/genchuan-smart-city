package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderrefund.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 订单退款驳回 Request VO")
@Data
public class OrderRefundRejectReqVO {

    @Schema(description = "退款申请 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "退款申请 ID 不能为空")
    private Long id;

    @Schema(description = "驳回原因", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "驳回原因不能为空")
    private String rejectReason;
}