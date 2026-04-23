package cn.iocoder.yudao.module.chargepark.marketop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CouponMgmtStatusEnum {
    NOT_RECEIVED("0", "未领取"),
    RECEIVED("1", "已领取"),
    USED("2", "已使用"),
    EXPIRED("3", "已过期");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (CouponMgmtStatusEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return value;
    }
}
