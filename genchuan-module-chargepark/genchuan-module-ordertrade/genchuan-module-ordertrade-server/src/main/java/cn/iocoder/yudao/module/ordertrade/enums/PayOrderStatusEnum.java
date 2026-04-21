package cn.iocoder.yudao.module.ordertrade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PayOrderStatusEnum {
    WAITING(0,    "待支付"),
    SUCCESS(10,   "已支付"),
    REFUNDING(20, "退款中"),
    REFUNDED(30,  "已退款"),
    CLOSED(40,    "已关闭");

    private final Integer value;
    private final String label;

    public static String labelOf(Integer value) {
        if (value == null) return "";
        for (PayOrderStatusEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return String.valueOf(value);
    }
}
