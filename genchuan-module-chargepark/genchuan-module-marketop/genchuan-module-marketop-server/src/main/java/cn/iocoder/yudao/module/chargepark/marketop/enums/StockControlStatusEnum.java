package cn.iocoder.yudao.module.chargepark.marketop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StockControlStatusEnum {
    NORMAL("0", "正常库存"),
    LOW("1", "低库存"),
    WARNING("2", "预警库存");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (StockControlStatusEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return value;
    }
}
