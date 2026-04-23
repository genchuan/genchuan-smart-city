package cn.iocoder.yudao.module.chargepark.marketop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PointLotterySyncStatusEnum {
    NOT_SYNCED("0", "未同步"),
    SYNCED("1", "已同步"),
    SYNC_FAILED("2", "同步失败");

    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (PointLotterySyncStatusEnum e : values()) {
            if (e.value.equals(value)) return e.label;
        }
        return value;
    }
}
