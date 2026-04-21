package cn.iocoder.yudao.module.ordertrade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AgentOrderStatusEnum {
    PENDING_PAY("pending_pay", "待支付"),
    PAID("paid",               "已支付"),
    COMPLETED("completed",     "已完成"),
    CANCELLED("cancelled",     "已取消");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (AgentOrderStatusEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return value;
    }
}
