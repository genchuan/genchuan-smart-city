package cn.iocoder.yudao.module.ordertrade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CollectMethodEnum {
    //追缴方式（CollectTrack 和 CollectConfig 共用）
    SMS("sms",      "短信"),
    NOTIFY("notify","站内信"),
    PHONE("phone",  "电话");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (CollectMethodEnum e : values()) {
            if (e.value.equals(value))
                return e.label;
        }
        return value; // 找不到就返回原值
    }
}
