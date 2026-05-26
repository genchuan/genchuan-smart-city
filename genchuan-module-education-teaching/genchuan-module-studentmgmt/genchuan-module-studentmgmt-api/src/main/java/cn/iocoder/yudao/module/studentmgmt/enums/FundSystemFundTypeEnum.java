package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 资助类型
 */
public enum FundSystemFundTypeEnum {

    // 资助类型（助学金 / 勤工俭学 / 其他），关联芋道字典表：fund_system_fund_type
    FUND_SYSTEM_STATUS_1("1", "'助学金'"),
    FUND_SYSTEM_STATUS_2("2", "'勤工俭学'"),
    FUND_SYSTEM_STATUS_3("3", "其他");

    public static final String DICT_TYPE = "fund_system_fund_type";
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
        for (FundSystemFundTypeEnum value : values()) {
            if (value.status.equals(key)) {
                return value.name;
            }
        }
        return null;
    }


}
