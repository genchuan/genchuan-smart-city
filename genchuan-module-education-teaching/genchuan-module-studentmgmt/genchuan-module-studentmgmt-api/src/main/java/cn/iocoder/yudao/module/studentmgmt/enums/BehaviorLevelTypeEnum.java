package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 状态
 */
public enum BehaviorLevelTypeEnum {

    // 请假类型：事假/病假/其他，关联芋道字典表：behavior_mgmt_leave_type
    BEHAVIOR_MGMT_LEVEL_TYPE_1("1", "事假"),
    BEHAVIOR_MGMT_LEVEL_TYPE_2("2", "病假"),
    BEHAVIOR_MGMT_LEVEL_TYPE_3("3", "其他");
    public static final String DICT_TYPE = "behavior_mgmt_leave_type";

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
        for (BehaviorLevelTypeEnum value : values()) {
            if (value.status.equals(key)) {
                return value.name;
            }
        }
        return null;
    }


}
