package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 值班状态
 */
public enum DutyStatusEnum {

    // 状态：待打卡/待调班审批/待出车审批/已完成，关联芋道字典表：duty_mgmt_status
    DUTY_STATUS_PENDING_CHECKIN("pending_checkin", "待打卡"),
    DUTY_STATUS_PENDING_TRANSFER("pending_transfer", "待调班审批"),
    DUTY_STATUS_PENDING_CAR("pending_car", "待出车审批"),
    DUTY_STATUS_COMPLETED("completed", "已完成");

    public static final String DICT_TYPE = "duty_mgmt_status";
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
        for (DutyStatusEnum value : values()) {
            if (value.status.equals(key)) {
                return value.name;
            }
        }
        return null;
    }


}
