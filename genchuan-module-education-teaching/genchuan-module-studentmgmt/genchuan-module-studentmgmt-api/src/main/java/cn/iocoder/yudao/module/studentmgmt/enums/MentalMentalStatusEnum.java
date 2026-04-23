package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 心理状态
 */
public enum MentalMentalStatusEnum {

//    正常 / 关注 / 高危，关联芋道字典表：mental_mgmt_mental_status
    MENTAL_MGMT_MENTAL_STATUS_NORMAL ("normal", "正常"),
    MENTAL_MGMT_MENTAL_STATUS_FOCUS("focus", "关注"),
    MENTAL_MGMT_MENTAL_STATUS_HIGH_RISK("high_risk", "高危");
    private static final String DICT_TYPE = "mental_mgmt_mental_status";

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
        for (MentalMentalStatusEnum value : values()) {
            if (value.status.equals(key)) {
                return value.name;
            }
        }
        return null;
    }

}
