package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 状态
 */
public enum FundSystemStatusEnum {

    // 状态：待审核/已汇总 关联芋道字典表：fund_system_status
    FUND_SYSTEM_STATUS_0("0", "待审核"),
    FUND_SYSTEM_STATUS_1("1", "已汇总");

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
        for (FundSystemStatusEnum value : values()) {
            if (value.status.equals(key)) {
                return value.name;
            }
        }
        return null;
    }


}
