package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 状态
 */
public enum AidWorkProcessStatusEnum {

    // 状态（待审核 / 已通过 / 已完成），关联芋道字典表：violate_mgmt_status
    AID_WORK_PROCESS_STATUS_ENUM_0("0", "跟进中"),
    AID_WORK_PROCESS_STATUS_ENUM_1("1", "已完成");

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
        for (AidWorkProcessStatusEnum value : values()) {
            if (value.status.equals(key)) {
                return value.name;
            }
        }
        return null;
    }

}
