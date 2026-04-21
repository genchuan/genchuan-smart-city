package cn.iocoder.yudao.module.ordertrade.controller.admin.invoicemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 发票列表新增/修改 Request VO")
@Data
public class InvoiceListSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "关联订单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "关联订单ID不能为空")
    private Long orderId;

    @Schema(description = "发票抬头", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "发票抬头不能为空")
    private String title;

    @Schema(description = "税号")
    private String taxNo;

    @Schema(description = "开票金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "开票金额不能为空")
    private BigDecimal amount;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "下载链接")
    private String downloadUrl;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;
}
