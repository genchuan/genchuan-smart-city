package cn.iocoder.yudao.module.ordertrade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 支付方式（共用）
@Getter
@AllArgsConstructor
public enum PayMethodEnum {
    WECHAT("wechat", "微信"),
    ALIPAY("alipay", "支付宝"),
    BANK("bank", "银行卡"),
    CASH("cash", "现金");
    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (PayMethodEnum e : values()) {
            if (e.value.equals(value))
                return e.label;
        }
        return value; // 找不到就返回原值
    }
}
