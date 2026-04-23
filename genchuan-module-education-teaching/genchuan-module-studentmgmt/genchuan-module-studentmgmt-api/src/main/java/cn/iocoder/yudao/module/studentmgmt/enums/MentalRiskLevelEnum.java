package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 心理状态
 */
public enum MentalRiskLevelEnum {

    // 风险等级：低/中/高，关联芋道字典表：mental_mgmt_risk_level
    MENTAL_MGMT_RISK_LEVEL_LOW("low", "低"),
    MENTAL_MGMT_RISK_LEVEL_MEDIUM("medium", "中"),
    MENTAL_MGMT_RISK_LEVEL_HIGH("high", "高");

    public static final String DICT_TYPE = "mental_mgmt_risk_level";

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
        for (MentalRiskLevelEnum value : values()) {
            if (value.status.equals(key)) {
                return value.name;
            }
        }
        return null;
    }

}
