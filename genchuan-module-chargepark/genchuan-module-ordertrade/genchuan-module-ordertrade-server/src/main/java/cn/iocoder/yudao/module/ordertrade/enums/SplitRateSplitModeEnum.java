package cn.iocoder.yudao.module.ordertrade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SplitRateSplitModeEnum {
    FIXED("fixed",   "固定比例"),
    LADDER("ladder", "阶梯比例");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (SplitRateSplitModeEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return value;
    }
}
