package cn.iocoder.yudao.module.ordertrade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PayAppStatusEnum {
    DISABLED(0, "未生效"),
    ENABLED(1,  "已生效");

    private final Integer value;
    private final String label;

    public static String labelOf(Integer value) {
        if (value == null) return "";
        for (PayAppStatusEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return String.valueOf(value);
    }
}
