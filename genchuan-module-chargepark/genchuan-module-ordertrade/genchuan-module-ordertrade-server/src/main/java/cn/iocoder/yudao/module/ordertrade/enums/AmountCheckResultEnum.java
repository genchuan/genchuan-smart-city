package cn.iocoder.yudao.module.ordertrade.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AmountCheckResultEnum {

    // 金额核算结果
    PASS("pass", "通过"),
    FAIL("fail", "不通过");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
    if (value == null) return "";
    for (AmountCheckResultEnum e : values()) {
        if (e.value.equals(value))
            return e.label;
        }
        return value; // 找不到就返回原值
    }
}
