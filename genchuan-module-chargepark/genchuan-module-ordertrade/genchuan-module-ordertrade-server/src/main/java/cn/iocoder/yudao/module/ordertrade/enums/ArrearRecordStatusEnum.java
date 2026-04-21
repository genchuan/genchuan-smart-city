package cn.iocoder.yudao.module.ordertrade.enums;

// 路径: enums/ArrearRecordStatusEnum.java

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArrearRecordStatusEnum {

    UNPAID("unpaid", "未结清"),
    CLEARED("cleared", "已结清");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
    if (value == null) return "";
    for (ArrearRecordStatusEnum e : values()) {
        if (e.value.equals(value))
            return e.label;
        }
        return value; // 找不到就返回原值
    }
}
