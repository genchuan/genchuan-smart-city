package cn.iocoder.yudao.module.chargepark.marketop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StockControlWarnStatusEnum {
    NOT_WARNED("0", "未告警"),
    WARNED("1", "已告警");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (StockControlWarnStatusEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return value;
    }
}
