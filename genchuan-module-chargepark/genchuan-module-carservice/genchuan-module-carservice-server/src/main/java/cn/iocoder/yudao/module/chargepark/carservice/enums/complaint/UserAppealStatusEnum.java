package cn.iocoder.yudao.module.chargepark.carservice.enums.complaint;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户申诉状态枚举
 * 关联字典：user_appeal_status
 */
@Getter
@AllArgsConstructor
public enum UserAppealStatusEnum {

    WAITING_AUDIT("待审核"),
    WAITING_HANDLE("待处置"),
    COMPLETED("已完成");

    private final String label;

}
