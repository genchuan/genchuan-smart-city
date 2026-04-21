package cn.iocoder.yudao.module.chargepark.carservice.enums.serviceconfig;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 客服话术状态枚举
 * 关联字典：wording_mgmt_status
 */
@Getter
@AllArgsConstructor
public enum WordingMgmtStatusEnum {

    DISABLED("未生效"),
    ENABLED("已生效");

    private final String label;

}
