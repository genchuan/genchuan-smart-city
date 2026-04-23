package cn.iocoder.yudao.module.chargepark.marketop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExchangeCategoryStatusEnum {
    NOT_EFFECTIVE("0", "未生效"),
    EFFECTIVE("1", "已生效"),
    DISABLED("2", "已禁用");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (ExchangeCategoryStatusEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return value;
    }
}
