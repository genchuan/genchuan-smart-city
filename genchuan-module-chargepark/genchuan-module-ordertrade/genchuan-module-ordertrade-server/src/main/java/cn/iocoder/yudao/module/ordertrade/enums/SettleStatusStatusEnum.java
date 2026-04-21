package cn.iocoder.yudao.module.ordertrade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SettleStatusStatusEnum {
    NORMAL("normal",     "正常状态"),
    ABNORMAL("abnormal", "异常状态");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (SettleStatusStatusEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return value;
    }
}
