package cn.iocoder.yudao.module.chargepark.marketop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PointActivityTypeEnum {
    SIGN("0", "签到"),
    CONSUME("1", "消费"),
    INVITE("2", "邀请");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (PointActivityTypeEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return value;
    }
}
