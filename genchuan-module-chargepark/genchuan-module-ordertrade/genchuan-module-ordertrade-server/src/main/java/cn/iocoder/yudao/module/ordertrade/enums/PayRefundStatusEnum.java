package cn.iocoder.yudao.module.ordertrade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PayRefundStatusEnum {
    WAITING(0,   "待退款"),
    RUNNING(10,  "退款中"),
    SUCCESS(20,  "退款成功"),
    FAILURE(30,  "退款失败");

    private final Integer value;
    private final String label;

    public static String labelOf(Integer value) {
        if (value == null) return "";
        for (PayRefundStatusEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return String.valueOf(value);
    }
}
