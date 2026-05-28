package cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 代付订单申请开票 Request VO")
@Data
public class AgentOrderInvoiceReqVO {

    @Schema(description = "代付订单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "代付订单ID不能为空")
    private Long id;

    @Schema(description = "发票抬头", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "发票抬头不能为空")
    private String invoiceTitle;

    @Schema(description = "发票税号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "发票税号不能为空")
    private String invoiceTaxNo;

    @Schema(description = "接收邮箱", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "接收邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String invoiceEmail;

    @Schema(description = "备注")
    private String remark;
}