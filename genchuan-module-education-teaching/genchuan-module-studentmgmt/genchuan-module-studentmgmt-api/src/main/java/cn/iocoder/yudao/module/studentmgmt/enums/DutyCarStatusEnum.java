package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 出车状态
 */
public enum DutyCarStatusEnum {

    // 状态：待打卡/待调班审批/待出车审批/已完成，关联芋道字典表：duty_mgmt_car_status
    CAR_STATUS_NONE("none", "无"),
    CAR_STATUS_PENDING("pending", "待审批"),
    CAR_STATUS_REJECTED("rejected", "已驳回"),
    CAR_STATUS_APPROVED("approved", "已通过");

    public static final String DICT_TYPE = "duty_mgmt_car_status";

    /**
     * 状态
     * <p>
     */
    private final String status;

    /**
     * 名字
     */
    private final String name;

    // 根据key获取名称
    public static String getNameByKey(String key) {
        for (DutyCarStatusEnum value : values()) {
            if (value.status.equals(key)) {
                return value.name;
            }
        }
        return null;
    }


}
