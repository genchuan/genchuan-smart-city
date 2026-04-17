package cn.iocoder.yudao.module.chargepark.carservice.enums.carguide;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 空位推送状态枚举
 * 关联字典：space_push_status
 */
@Getter
@AllArgsConstructor
public enum SpacePushStatusEnum {

    WAITING_PUSH("待推送"),
    PUSHED("已推送");

    private final String label;

}
