package cn.iocoder.yudao.module.chargepark.marketop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CardConfigTypeEnum {
    DAY("0", "日卡"),
    WEEK("1", "周卡"),
    MONTH("2", "月卡"),
    QUARTER("3", "季卡"),
    YEAR("4", "年卡");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (CardConfigTypeEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return value;
    }
}
