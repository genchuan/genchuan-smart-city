package cn.iocoder.yudao.module.ordertrade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InvoiceConfigStatusEnum {
    PENDING("pending",   "未生效"),
    ENABLED("enabled",   "已生效"),
    DISABLED("disabled", "已禁用");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (InvoiceConfigStatusEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return value;
    }
}
