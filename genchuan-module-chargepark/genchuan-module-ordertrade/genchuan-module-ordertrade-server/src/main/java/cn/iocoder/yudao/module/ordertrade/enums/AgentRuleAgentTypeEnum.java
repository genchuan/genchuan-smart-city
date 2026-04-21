package cn.iocoder.yudao.module.ordertrade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AgentRuleAgentTypeEnum {
    MERCHANT("merchant",   "商户代付"),
    ENTERPRISE("enterprise","企业代付"),
    PUBLIC("public",       "公益代付");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (AgentRuleAgentTypeEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return value;
    }
}
