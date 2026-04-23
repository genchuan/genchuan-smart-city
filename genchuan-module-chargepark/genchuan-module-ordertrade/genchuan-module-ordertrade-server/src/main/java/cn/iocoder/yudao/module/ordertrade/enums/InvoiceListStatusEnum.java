package cn.iocoder.yudao.module.ordertrade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InvoiceListStatusEnum {
    PENDING_AUDIT("pending_audit",     "待审核"),
    PENDING_INVOICE("pending_invoice", "待开票"),
    INVOICED("invoiced",               "已开票"),
    REJECTED("rejected",               "已驳回");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (InvoiceListStatusEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return value;
    }
}
