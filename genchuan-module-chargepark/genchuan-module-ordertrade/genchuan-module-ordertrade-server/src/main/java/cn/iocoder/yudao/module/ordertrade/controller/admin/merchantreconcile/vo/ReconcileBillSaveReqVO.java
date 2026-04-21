package cn.iocoder.yudao.module.ordertrade.controller.admin.merchantreconcile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "管理后台 - 商户对账单新增/修改 Request VO")
@Data
public class ReconcileBillSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "商户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "商户ID不能为空")
    private Long merchantId;

    @Schema(description = "商户名称")
    private String merchantName;

    @Schema(description = "对账日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "对账日期不能为空")
    private LocalDate billDate;

    @Schema(description = "系统订单总金额")
    private BigDecimal sysAmount;

    @Schema(description = "商户上报总金额")
    private BigDecimal merchantAmount;

    @Schema(description = "差异金额")
    private BigDecimal diffAmount;

    @Schema(description = "对账状态：pending/confirmed/disputed/resolved")
    private String status;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;
}
