package cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 逃费识别新增/修改 Request VO")
@Data
public class DebtIdentifySaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "订单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "订单不能为空")
    private Long orderId;

    @Schema(description = "欠费金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "欠费金额不能为空")
    private BigDecimal debtAmount;

    @Schema(description = "识别时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "识别时间不能为空")
    private LocalDateTime identifyTime;

    @Schema(description = "状态：pending/identified")
    private String status;

    @Schema(description = "备注")
    private String remark;
}
