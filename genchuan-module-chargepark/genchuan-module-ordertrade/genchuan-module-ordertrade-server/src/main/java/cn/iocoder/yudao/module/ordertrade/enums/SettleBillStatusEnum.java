package cn.iocoder.yudao.module.ordertrade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SettleBillStatusEnum {
    PENDING_AUDIT("pending_audit",   "待审核"),
    PENDING_SETTLE("pending_settle", "待结算"),
    SETTLED("settled",               "已结算"),
    REJECTED("rejected",             "已驳回");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (SettleBillStatusEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return value;
    }
}
