package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 状态
 */
public enum HonorStatusEnum {

    // 状态：待评估/咨询中/已干预，关联芋道字典表：honor_mgmt_status
    HONOR_MGMT_STATUS_0("0", "待审核"),
    HONOR_MGMT_STATUS_1("1", "已通过"),
    HONOR_MGMT_STATUS_2("2", "已推送"),
    HONOR_MGMT_STATUS_3("3", "不通过");

    public static final String DICT_TYPE = "honor_mgmt_status";
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
        for (HonorStatusEnum value : values()) {
            if (value.status.equals(key)) {
                return value.name;
            }
        }
        return null;
    }


}
