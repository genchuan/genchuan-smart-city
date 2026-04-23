package cn.iocoder.yudao.module.ordertrade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InvoiceAuditStatusEnum {
    PENDING("pending",   "待审核"),
    APPROVED("approved", "已通过"),
    REJECTED("rejected", "已驳回");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (InvoiceAuditStatusEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return value;
    }
}
