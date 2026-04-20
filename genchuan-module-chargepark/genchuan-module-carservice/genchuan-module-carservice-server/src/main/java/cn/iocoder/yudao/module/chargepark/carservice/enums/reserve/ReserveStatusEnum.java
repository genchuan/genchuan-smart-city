package cn.iocoder.yudao.module.chargepark.carservice.enums.reserve;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 预约状态枚举
 * 关联字典：reserve_list_status
 */
@Getter
@AllArgsConstructor
public enum ReserveStatusEnum {

    WAITING_AUDIT("待审核"),
    EFFECTIVE("已生效"),
    COMPLETED("已完成"),
    CANCELLED("已取消");

    private final String label;

    public static boolean isValid(String label) {
        return Arrays.stream(values()).anyMatch(e -> e.label.equals(label));
    }

}
