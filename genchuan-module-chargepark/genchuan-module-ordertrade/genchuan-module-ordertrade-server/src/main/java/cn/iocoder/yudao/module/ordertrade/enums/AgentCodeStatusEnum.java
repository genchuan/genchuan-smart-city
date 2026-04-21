package cn.iocoder.yudao.module.ordertrade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AgentCodeStatusEnum {
    UNUSED("unused",   "未使用"),
    USED("used",       "已使用"),
    EXPIRED("expired", "已过期");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (AgentCodeStatusEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return value;
    }
}
