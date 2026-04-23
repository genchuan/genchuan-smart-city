package cn.iocoder.yudao.module.ordertrade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
// 充电订单状态
@Getter
@AllArgsConstructor
public enum ChargeOrderStatusEnum {
    CHARGING("charging", "充电中"),
    LENDING("lending", "借出中"),
    PENDING_PAY("pending_pay", "待支付"),
    PAID("paid", "已支付"),
    COMPLETED("completed", "已完成"),
    CANCELLED("cancelled", "已取消"),
    REFUNDING("refunding", "退款中");
    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (ChargeOrderStatusEnum e : values()) {
            if (e.value.equals(value))
                return e.label;
        }
        return value; // 找不到就返回原值
    }
}
