package cn.iocoder.yudao.module.ordertrade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PayTransferStatusEnum {
    WAITING(0,   "待转账"),
    RUNNING(10,  "转账中"),
    SUCCESS(20,  "转账成功"),
    FAILURE(30,  "转账失败");

    private final Integer value;
    private final String label;

    public static String labelOf(Integer value) {
        if (value == null) return "";
        for (PayTransferStatusEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return String.valueOf(value);
    }
}
