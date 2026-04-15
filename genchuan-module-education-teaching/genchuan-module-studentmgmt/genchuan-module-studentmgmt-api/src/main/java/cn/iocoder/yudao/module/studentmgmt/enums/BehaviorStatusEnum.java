package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 状态
 */
public enum BehaviorStatusEnum {

    // 状态：待评估/咨询中/已干预，关联芋道字典表：behavior_mgmt_status
    BEHAVIOR_MGMT_STATUS_0("0", "待审批"),
    BEHAVIOR_MGMT_STATUS_1("1", "已通过"),
    BEHAVIOR_MGMT_STATUS_2("2", "已驳回"),
    BEHAVIOR_MGMT_STATUS_3("3", "已撤销");

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
        for (BehaviorStatusEnum value : values()) {
            if (value.status.equals(key)) {
                return value.name;
            }
        }
        return null;
    }


}
