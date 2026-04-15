package cn.iocoder.yudao.module.ordertrade.controller.admin.refundmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 退款申请新增/修改 Request VO")
@Data
public class RefundApplySaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "订单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "订单不能为空")
    private Long orderId;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "用户不能为空")
    private Long userId;

    @Schema(description = "退款原因", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "退款原因不能为空")
    private String reason;

    @Schema(description = "退款金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "退款金额不能为空")
    private BigDecimal amount;

    @Schema(description = "申请时间")
    private LocalDateTime applyTime;

    @Schema(description = "审核状态：pending_audit/pending_exec/completed/rejected")
    private String auditStatus;

    @Schema(description = "审核人ID")
    private Long auditorId;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "备注")
    private String remark;
}
