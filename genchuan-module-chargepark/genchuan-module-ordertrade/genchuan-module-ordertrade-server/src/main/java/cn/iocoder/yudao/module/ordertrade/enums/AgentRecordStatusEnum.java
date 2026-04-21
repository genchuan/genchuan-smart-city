package cn.iocoder.yudao.module.ordertrade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AgentRecordStatusEnum {
    NORMAL("normal",     "正常记录"),
    ABNORMAL("abnormal", "异常记录");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (AgentRecordStatusEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return value;
    }
}
