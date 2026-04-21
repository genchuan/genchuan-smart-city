package cn.iocoder.yudao.module.ordertrade.controller.admin.merchantreconcile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 对账记录新增/修改 Request VO")
@Data
public class ReconcileRecordSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "所属对账单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "对账单ID不能为空")
    private Long billId;

    @Schema(description = "对账单号")
    private String billNo;

    @Schema(description = "商户ID")
    private Long merchantId;

    @Schema(description = "订单编号")
    private String orderNo;

    @Schema(description = "系统金额")
    private BigDecimal sysAmount;

    @Schema(description = "商户上报金额")
    private BigDecimal merchantAmount;

    @Schema(description = "差异金额")
    private BigDecimal diffAmount;

    @Schema(description = "对账结果：matched/unmatched/only_sys/only_merchant")
    private String matchResult;

    @Schema(description = "异常原因")
    private String diffReason;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;
}
