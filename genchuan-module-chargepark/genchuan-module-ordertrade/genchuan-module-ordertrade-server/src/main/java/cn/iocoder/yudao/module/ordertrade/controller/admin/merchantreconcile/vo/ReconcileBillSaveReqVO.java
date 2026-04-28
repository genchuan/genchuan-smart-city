package cn.iocoder.yudao.module.ordertrade.controller.admin.merchantreconcile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 商户对账单新增/修改 Request VO")
@Data
public class ReconcileBillSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "商户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "商户ID不能为空")
    private Long merchantId;

    @Schema(description = "对账周期，如 2026-03、2026-W14", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "对账周期不能为空")
    private String cycle;

    @Schema(description = "平台金额(元)")
    private BigDecimal platformAmount;

    @Schema(description = "商户金额(元)")
    private BigDecimal merchantAmount;

    @Schema(description = "对账状态：pending/reconciled/abnormal")
    private String status;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;
}
