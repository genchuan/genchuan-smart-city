package cn.iocoder.yudao.module.ordertrade.enums;

// 路径: enums/ArrearRecordStatusEnum.java

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DebtRecordStatusEnum {

    // 追缴状态
    UNCOLLECTED("uncollected", "未追缴"),
    COLLECTING("collecting",   "追缴中"),
    COMPLETED("completed",     "已完成");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
    if (value == null) return "";
    for (DebtRecordStatusEnum e : values()) {
        if (e.value.equals(value))
            return e.label;
        }
        return value; // 找不到就返回原值
    }
}
