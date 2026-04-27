package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 状态
 */
public enum DormCheckStatusEnum {

    // 考勤状态（正常 / 迟到 / 未到），关联芋道字典表：dorm_check_status
    DORM_CHECK_STATUS_0("0", "正常"),
    DORM_CHECK_STATUS_1("1", "异常");

    public static final String DICT_TYPE = "dorm_check_status";
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
        for (DormCheckStatusEnum value : values()) {
            if (value.status.equals(key)) {
                return value.name;
            }
        }
        return null;
    }


}
