package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 异常类型
 */
public enum DormCheckAbnormalTypeEnum {

    // 异常类型（无 / 晚归 / 未归），关联芋道字典表：dorm_check_abnormal_type
    DORM_CHECK_ABNORMAL_TYPE_0("0", "无"),
    DORM_CHECK_ABNORMAL_TYPE_1("1", "晚归"),
    DORM_CHECK_ABNORMAL_TYPE_2("2", "未归");

    public static final String DICT_TYPE = "dorm_check_abnormal_type";
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
        for (DormCheckAbnormalTypeEnum value : values()) {
            if (value.status.equals(key)) {
                return value.name;
            }
        }
        return null;
    }


}
