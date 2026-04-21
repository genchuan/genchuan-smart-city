package cn.iocoder.yudao.module.ordertrade.enums;

// 路径: enums/ArrearRecordStatusEnum.java

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AbnormalStatusEnum {

    // 异常处置状态
    UNHANDLED("unhandled", "未处理"),
    HANDLING("handling",   "处理中"),
    CLOSED("closed",       "已关闭");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
    if (value == null) return "";
    for (AbnormalStatusEnum e : values()) {
        if (e.value.equals(value))
            return e.label;
        }
        return value; // 找不到就返回原值
    }
}
