package cn.iocoder.yudao.module.ordertrade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PayCallbackStatusEnum {
    WAITING(0,  "待通知"),
    SUCCESS(10, "通知成功"),
    FAILURE(20, "通知失败");

    private final Integer value;
    private final String label;

    public static String labelOf(Integer value) {
        if (value == null) return "";
        for (PayCallbackStatusEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return String.valueOf(value);
    }
}
