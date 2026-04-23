package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 状态
 */
public enum BehaviorAttendanceSyncEnum {

    // 状态：待评估/咨询中/已干预，关联芋道字典表：behavior_mgmt_status
    ATTENDANCE_SYNC_0("0", "未同步"),
    ATTENDANCE_SYNC_1("1", "已同步");

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
        for (BehaviorAttendanceSyncEnum value : values()) {
            if (value.status.equals(key)) {
                return value.name;
            }
        }
        return null;
    }


}
