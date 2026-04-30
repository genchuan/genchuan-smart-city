package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 状态
 */
public enum StudentInfoTypeEnum {

    STATUS_1("1", "普通生"),
    STATUS_2("2", "特长生"),
    STATUS_3("3", "转学生");

    public static final String DICT_TYPE = "student_info_student_type";
    /**
     * 状态
     * <p>
     */
    private final String status;

    /**
     * 名字
     */
    private final String name;


}
