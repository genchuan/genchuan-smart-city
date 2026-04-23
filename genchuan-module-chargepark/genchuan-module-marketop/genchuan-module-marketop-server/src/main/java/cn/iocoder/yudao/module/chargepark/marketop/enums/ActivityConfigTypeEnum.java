package cn.iocoder.yudao.module.chargepark.marketop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActivityConfigTypeEnum {
    NEW_USER("0", "新用户"),
    HOLIDAY("1", "节假日"),
    STORE_CELEBRATION("2", "店庆"),
    DAILY("3", "日常");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (ActivityConfigTypeEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return value;
    }
}
