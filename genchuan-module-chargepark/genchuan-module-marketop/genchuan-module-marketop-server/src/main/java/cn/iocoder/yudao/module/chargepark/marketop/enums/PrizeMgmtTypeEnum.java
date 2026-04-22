package cn.iocoder.yudao.module.chargepark.marketop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PrizeMgmtTypeEnum {
    PHYSICAL("0", "实物"),
    VIRTUAL("1", "虚拟"),
    COUPON("2", "优惠券"),
    CARD("3", "卡种");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (PrizeMgmtTypeEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return value;
    }
}
