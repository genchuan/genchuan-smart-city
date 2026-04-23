package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 打卡状态
 */
public enum DutyCheckInStatusEnum {

    // 状态（待审核 / 已通过 / 已建档），关联芋道字典表：duty_mgmt_check_in_status
    DUTY_CHCECK_IN_STATUS_NOT_CHECKED_IN("not_checked_in", "未打卡"),
    DUTY_CHCECK_IN_STATUS_CHECKED_IN("checked_in", "已打卡");

    public static final String DICT_TYPE = "duty_mgmt_check_in_status";

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
        for (DutyCheckInStatusEnum value : values()) {
            if (value.status.equals(key)) {
                return value.name;
            }
        }
        return null;
    }


}
