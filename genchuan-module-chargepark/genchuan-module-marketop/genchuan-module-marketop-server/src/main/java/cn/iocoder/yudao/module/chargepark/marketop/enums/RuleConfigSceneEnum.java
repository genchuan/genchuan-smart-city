package cn.iocoder.yudao.module.chargepark.marketop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RuleConfigSceneEnum {
    CHARGE("0", "充电"),
    PARK("1", "停车"),
    ACTIVITY("2", "活动"),
    OTHER("3", "其他");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (RuleConfigSceneEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return value;
    }
}
