package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 规则配置更新 Request VO")
@Data
public class RuleConfigUpdateReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "主键ID不能为空")
    private Long id;

    @Schema(description = "规则名称")
    private String name;

    @Schema(description = "赠送比例")
    private BigDecimal giftRatio;

    @Schema(description = "规则描述")
    private String description;

    @Schema(description = "适用场景")
    private String scene;

}
