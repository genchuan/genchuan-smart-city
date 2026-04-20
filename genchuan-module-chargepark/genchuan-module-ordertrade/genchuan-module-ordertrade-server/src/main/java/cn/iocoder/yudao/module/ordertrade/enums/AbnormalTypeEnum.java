package cn.iocoder.yudao.module.ordertrade.enums;

// 路径: enums/ArrearRecordStatusEnum.java

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AbnormalTypeEnum {

    // 异常类型
    PAYMENT_ERROR("payment_error", "支付异常"),
    BILLING_ERROR("billing_error", "计费异常"),
    STATUS_ERROR("status_error",   "状态异常");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
    if (value == null) return "";
    for (AbnormalTypeEnum e : values()) {
        if (e.value.equals(value))
            return e.label;
        }
        return value; // 找不到就返回原值
    }
}
