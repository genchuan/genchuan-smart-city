package cn.iocoder.yudao.module.chargepark.marketop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PrizeMgmtStatusEnum {
    NORMAL("0", "正常"),
    DISABLED("1", "禁用");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (PrizeMgmtStatusEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return value;
    }
}
