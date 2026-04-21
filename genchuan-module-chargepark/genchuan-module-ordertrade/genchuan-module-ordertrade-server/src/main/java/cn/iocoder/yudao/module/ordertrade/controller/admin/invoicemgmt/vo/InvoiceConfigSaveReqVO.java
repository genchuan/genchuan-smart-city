package cn.iocoder.yudao.module.ordertrade.controller.admin.invoicemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 发票配置新增/修改 Request VO")
@Data
public class InvoiceConfigSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "开票类目", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "开票类目不能为空")
    private String category;

    @Schema(description = "税率(%)")
    private BigDecimal taxRate;

    @Schema(description = "开票主体")
    private String taxBody;

    @Schema(description = "状态：pending/enabled/disabled")
    private String status;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;
}
