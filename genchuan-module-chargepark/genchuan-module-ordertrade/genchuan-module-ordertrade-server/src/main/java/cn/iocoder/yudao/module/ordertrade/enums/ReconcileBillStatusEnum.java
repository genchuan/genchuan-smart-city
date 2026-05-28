package cn.iocoder.yudao.module.ordertrade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReconcileBillStatusEnum {
    PENDING("pending", "待对账"),
    RECONCILED("reconciled", "已对账"),
    ABNORMAL("abnormal", "异常"),
    CONFIRMED("confirmed", "已确认");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (ReconcileBillStatusEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return value;
    }
}
