package cn.iocoder.yudao.module.chargepark.marketop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActivityConfigUserGroupEnum {
    NEW_USER("0", "新用户"),
    OLD_USER("1", "老用户"),
    ALL("2", "全部");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (ActivityConfigUserGroupEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return value;
    }
}
