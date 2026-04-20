package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 规则配置创建 Request VO")
@Data
public class RuleConfigCreateReqVO {

    @Schema(description = "规则名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "规则名称不能为空")
    private String name;

    @Schema(description = "规则类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "规则类型不能为空")
    private String type;

    @Schema(description = "赠送比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "赠送比例不能为空")
    private BigDecimal giftRatio;

    @Schema(description = "规则描述")
    private String description;

    @Schema(description = "适用场景", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "适用场景不能为空")
    private String scene;

}
