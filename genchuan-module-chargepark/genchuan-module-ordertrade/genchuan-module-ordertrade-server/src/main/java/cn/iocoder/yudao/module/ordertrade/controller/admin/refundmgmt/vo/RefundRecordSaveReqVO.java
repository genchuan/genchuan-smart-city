package cn.iocoder.yudao.module.ordertrade.controller.admin.refundmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 退款记录新增/修改 Request VO")
@Data
public class RefundRecordSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "记录编号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String recordNo;
    @Schema(description = "关联退款申请ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long applyId;
    @Schema(description = "关联订单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long orderId;
    @Schema(description = "退款金额", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal refundAmount;
    @Schema(description = "退款时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime refundTime;
    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED)
    private String status;
    @Schema(description = "核查理由", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String checkReason;
    @Schema(description = "操作人ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long operatorId;
}
