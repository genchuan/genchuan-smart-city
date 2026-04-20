package cn.iocoder.yudao.module.ordertrade.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RefundApplyStatusEnum {

    // 退款申请状态
    PENDING_AUDIT("pending_audit", "待审核"),
    PENDING_EXEC("pending_exec",   "待执行"),
    COMPLETED("completed",         "已完成"),
    REJECTED("rejected",           "已驳回");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
    if (value == null) return "";
    for (RefundApplyStatusEnum e : values()) {
        if (e.value.equals(value))
            return e.label;
        }
        return value; // 找不到就返回原值
    }
}
