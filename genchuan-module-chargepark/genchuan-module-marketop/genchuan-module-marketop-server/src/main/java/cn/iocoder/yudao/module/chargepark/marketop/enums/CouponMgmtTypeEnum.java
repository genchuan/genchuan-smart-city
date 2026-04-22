package cn.iocoder.yudao.module.chargepark.marketop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CouponMgmtTypeEnum {
    FULL_REDUCE("0", "满减"),
    DISCOUNT("1", "折扣"),
    DURATION("2", "时长"),
    DIRECT_REDUCE("3", "立减");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (CouponMgmtTypeEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return value;
    }
}
