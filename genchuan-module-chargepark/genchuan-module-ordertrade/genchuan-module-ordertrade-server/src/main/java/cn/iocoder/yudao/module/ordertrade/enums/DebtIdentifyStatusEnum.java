package cn.iocoder.yudao.module.ordertrade.enums;

// 路径: enums/ArrearRecordStatusEnum.java

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DebtIdentifyStatusEnum {

    // 逃费识别状态
    PENDING("pending",       "待识别"),
    IDENTIFIED("identified", "已识别"),
    MARKED("marked",         "已标记");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
    if (value == null) return "";
    for (DebtIdentifyStatusEnum e : values()) {
        if (e.value.equals(value))
            return e.label;
        }
        return value; // 找不到就返回原值
    }
}
