package cn.iocoder.yudao.module.chargepark.marketop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CardConfigScopeEnum {
    CHARGE("0", "充电"),
    PARK("1", "停车"),
    COMMON("2", "充停通用");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (CardConfigScopeEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return value;
    }
}
