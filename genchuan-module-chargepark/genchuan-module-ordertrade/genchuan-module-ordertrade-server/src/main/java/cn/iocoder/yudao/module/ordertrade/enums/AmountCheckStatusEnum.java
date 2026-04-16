package cn.iocoder.yudao.module.ordertrade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AmountCheckStatusEnum {
    //金额核算状态
    PENDING("pending",     "待核算"),
    CHECKED("checked",     "已核算"),
    CONFIRMED("confirmed", "已确认");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (AmountCheckStatusEnum e : values()) {
            if (e.value.equals(value))
                return e.label;
        }
        return value; // 找不到就返回原值
    }
}
