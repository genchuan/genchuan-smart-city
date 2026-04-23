package cn.iocoder.yudao.module.chargepark.marketop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CardOrderPayStatusEnum {
    WAITING("0", "待支付"),
    PAID("1", "已支付"),
    COMPLETED("2", "已完成"),
    CANCELLED("3", "已取消");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (CardOrderPayStatusEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return value;
    }
}
