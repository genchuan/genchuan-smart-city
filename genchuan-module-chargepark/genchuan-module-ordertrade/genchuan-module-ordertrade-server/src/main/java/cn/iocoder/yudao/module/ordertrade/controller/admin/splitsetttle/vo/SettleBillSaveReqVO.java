package cn.iocoder.yudao.module.ordertrade.controller.admin.splitsetttle.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 结算单据新增/修改 Request VO")
@Data
public class SettleBillSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "合作方ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "合作方ID不能为空")
    private Long partnerId;

    @Schema(description = "收费总额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "收费总额不能为空")
    private BigDecimal totalAmount;

    @Schema(description = "分成金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "分成金额不能为空")
    private BigDecimal splitAmount;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;
}
