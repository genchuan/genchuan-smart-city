package cn.iocoder.yudao.module.ordertrade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CollectTrackStatusEnum {
    //追缴跟踪状态
    PENDING("pending",       "待推送"),
    COLLECTING("collecting", "追缴中"),
    COMPLETED("completed",   "已完成");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (CollectTrackStatusEnum e : values()) {
            if (e.value.equals(value))
                return e.label;
        }
        return value; // 找不到就返回原值
    }
}
