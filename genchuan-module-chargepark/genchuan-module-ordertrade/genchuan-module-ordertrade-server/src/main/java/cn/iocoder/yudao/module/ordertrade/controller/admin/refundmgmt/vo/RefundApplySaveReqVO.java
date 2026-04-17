package cn.iocoder.yudao.module.ordertrade.controller.admin.refundmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 退款申请新增/修改 Request VO")
@Data
public class RefundApplySaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "申请编号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String applyNo;
    @Schema(description = "关联订单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long orderId;
    @Schema(description = "退款金额", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal refundAmount;
    @Schema(description = "退款原因", requiredMode = Schema.RequiredMode.REQUIRED)
    private String refundReason;
    @Schema(description = "申请时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private LocalDateTime applyTime;
    @Schema(description = "状态", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String status;
    @Schema(description = "申请人ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long applicantId;
    @Schema(description = "审核人ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long auditUserId;
    @Schema(description = "审核时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private LocalDateTime auditTime;
    @Schema(description = "操作人ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long operatorId;
}
