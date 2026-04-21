package cn.iocoder.yudao.module.ordertrade.controller.admin.invoicemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 开票审核新增/修改 Request VO")
@Data
public class InvoiceAuditSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "关联发票ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "关联发票ID不能为空")
    private Long applyId;

    @Schema(description = "申请人ID")
    private Long applicantId;

    @Schema(description = "申请时间")
    private LocalDateTime applyTime;

    @Schema(description = "状态：pending/approved/rejected")
    private String status;

    @Schema(description = "审核结果")
    private String auditResult;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;
}
