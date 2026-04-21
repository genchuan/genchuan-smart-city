package cn.iocoder.yudao.module.ordertrade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AgentOrderPayTypeEnum {
    WECHAT("wechat", "微信支付"),
    ALIPAY("alipay", "支付宝支付"),
    BANK("bank",     "银行卡支付");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (AgentOrderPayTypeEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return value;
    }
}
