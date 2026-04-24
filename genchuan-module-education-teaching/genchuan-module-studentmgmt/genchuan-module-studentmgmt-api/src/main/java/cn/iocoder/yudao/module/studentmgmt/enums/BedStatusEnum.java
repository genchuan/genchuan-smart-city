package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 床位状态
 */
public enum BedStatusEnum {

    // 状态 (未分配 / 已分配)，关联芋道字典表：bed_mgmt_status;
    BED_STATUS_UNALLOCATED("unallocated", "未分配"),
    BED_STATUS_ALLOCATED("allocated", "已分配");

    public static final String DICT_TYPE = "bed_mgmt_status";

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
        for (BedStatusEnum value : values()) {
            if (value.status.equals(key)) {
                return value.name;
            }
        }
        return null;
    }


}
