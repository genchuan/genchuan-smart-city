package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.alarmrule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 预警告警规则配置校验 Request VO")
@Data
public class AlarmRuleValidateReqVO {

    @Schema(description = "排除的规则ID（用于更新时排除自身）")
    private Long excludeId;

    @Schema(description = "规则名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "规则名称不能为空")
    private String ruleName;

    @Schema(description = "关联对象范围", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联对象范围不能为空")
    private String relatedObjectScope;

    @Schema(description = "预警触发条件", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "预警触发条件不能为空")
    private String triggerCondition;

    @Schema(description = "预警阈值上限")
    private BigDecimal warningThresholdUpper;

    @Schema(description = "预警阈值下限")
    private BigDecimal warningThresholdLower;

}