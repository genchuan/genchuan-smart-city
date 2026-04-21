package cn.iocoder.yudao.module.ordertrade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RefundRecordStatusEnum {
    //退款记录状态
    NORMAL("normal",     "正常记录"),
    ABNORMAL("abnormal", "异常记录");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (RefundRecordStatusEnum e : values()) {
            if (e.value.equals(value))
                return e.label;
        }
        return value; // 找不到就返回原值
    }
}
