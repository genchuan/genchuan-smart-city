package cn.iocoder.yudao.module.chargepark.carservice.enums.rescue;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 救援信息状态枚举
 * 关联字典：rescue_info_status
 *
 * 状态字面值与客户文档保持一致（中文 VARCHAR）
 */
@Getter
@AllArgsConstructor
public enum RescueStatusEnum {

    WAITING_DISPATCH("待派发"),
    WAITING_CLAIM("待认领"),
    PROCESSING("处理中"),
    COMPLETED("已完成");

    private final String label;

    public static boolean isValid(String label) {
        return Arrays.stream(values()).anyMatch(e -> e.label.equals(label));
    }

    public static RescueStatusEnum of(String label) {
        return Arrays.stream(values()).filter(e -> e.label.equals(label)).findFirst().orElse(null);
    }

}
