package cn.iocoder.yudao.module.chargepark.marketop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RuleConfigTypeEnum {
    OBTAIN("0", "获取规则"),
    CONSUME("1", "消耗规则"),
    GIFT("2", "赠送规则");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (RuleConfigTypeEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return value;
    }
}
