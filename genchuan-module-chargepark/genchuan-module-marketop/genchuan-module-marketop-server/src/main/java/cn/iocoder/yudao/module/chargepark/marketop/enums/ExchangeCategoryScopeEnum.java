package cn.iocoder.yudao.module.chargepark.marketop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExchangeCategoryScopeEnum {
    ALL_PLATFORM("0", "全平台"),
    SPECIFY_STATION("1", "指定场站");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (ExchangeCategoryScopeEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return value;
    }

    public static String valueOfLabel(String label) {
        if (label == null) return null;
        for (ExchangeCategoryScopeEnum e : values()) {
            if (e.label.equals(label)) return e.value;
        }
        return null;
    }
}
