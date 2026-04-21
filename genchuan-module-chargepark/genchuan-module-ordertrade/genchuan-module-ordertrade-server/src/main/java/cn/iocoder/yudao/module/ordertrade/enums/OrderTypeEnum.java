package cn.iocoder.yudao.module.ordertrade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderTypeEnum {
    TEMP_PARK("temp_park", "临时停车"),
    OFFTIME_PARK("offtime_park", "错时停车"),
    CAR_CHARGE("car_charge", "汽车充电"),
    BIKE_CHARGE("bike_charge", "两轮充电"),
    SHARE_CHARGE("share_charge", "共享充电");
    private final String value;
    private final String label;

    public static String labelOf(String value) {
        if (value == null) return "";
        for (OrderTypeEnum e : values()) {
            if (e.value.equals(value))
                return e.label;
        }
        return value; // 找不到就返回原值
    }
}
