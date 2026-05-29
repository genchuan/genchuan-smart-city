package cn.iocoder.yudao.module.smartcampus.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 状态
 */
public enum ArchiveStatusEnum {

    STUDENT_STATUS_0("0", "在籍"),
    STUDENT_STATUS_1("1", "休学"),
    STUDENT_STATUS_2("2", "退学"),
    STUDENT_STATUS_3("3", "异动");

    public static final String DICT_TYPE = "student_archive_status";

    private final String status;
    private final String name;

    // 根据key获取名称
    public static String getNameByKey(String key) {
        for (ArchiveStatusEnum value : values()) {
            if (value.status.equals(key)) {
                return value.name;
            }
        }
        return null;
    }


}
