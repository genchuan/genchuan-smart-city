package cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 代付规则新增/修改 Request VO")
@Data
public class AgentRuleSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "规则名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "规则名称不能为空")
    private String name;

    @Schema(description = "商户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "商户ID不能为空")
    private Long merchantId;

    @Schema(description = "代付类型：merchant/enterprise/public", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "代付类型不能为空")
    private String agentType;

    @Schema(description = "单次限额")
    private BigDecimal singleLimit;

    @Schema(description = "日累计限额")
    private BigDecimal dayLimit;

    @Schema(description = "适用场景")
    private String scene;

    @Schema(description = "状态：pending/enabled/disabled")
    private String status;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;
}
