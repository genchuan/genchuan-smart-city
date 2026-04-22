package cn.iocoder.yudao.module.chargepark.marketop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PointActivityStatusEnum {
    PENDING("0", "待生效"),
    IN_PROGRESS("1", "进行中"),
    ENDED("2", "已结束"),
    PAUSED("3", "已暂停");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (PointActivityStatusEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return value;
    }
}
