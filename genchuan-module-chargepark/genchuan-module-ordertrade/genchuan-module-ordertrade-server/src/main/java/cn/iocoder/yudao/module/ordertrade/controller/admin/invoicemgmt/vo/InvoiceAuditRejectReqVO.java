package cn.iocoder.yudao.module.ordertrade.controller.admin.invoicemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 开票审核驳回 Request VO")
@Data
public class InvoiceAuditRejectReqVO {

    @Schema(description = "审核 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "审核 ID 不能为空")
    private Long id;

    @Schema(description = "驳回理由", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "驳回理由不能为空")
    private String rejectReason;

}
