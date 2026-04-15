package cn.iocoder.yudao.module.ordertrade.controller.admin.refundmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 金额核算新增/修改 Request VO")
@Data
public class AmountCheckSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "订单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "订单不能为空")
    private Long orderId;

    @Schema(description = "核算金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "核算金额不能为空")
    private BigDecimal checkAmount;

    @Schema(description = "实际金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "实际金额不能为空")
    private BigDecimal realAmount;

    @Schema(description = "核算时间")
    private LocalDateTime checkTime;

    @Schema(description = "状态：pending/checked")
    private String status;

    @Schema(description = "备注")
    private String remark;
}
