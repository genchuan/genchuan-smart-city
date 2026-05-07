package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 状态
 */
public enum StudentInfoStatusEnum {

    // 状态（待确认 / 待审核 / 已通过，关联芋道字典表：stay_mgmt_status）
    STUDENT_INFO_STATUS_1("1", "在籍"),
    STUDENT_INFO_STATUS_2("2", "休学"),
    STUDENT_INFO_STATUS_3("3", "退学"),
    STUDENT_INFO_STATUS_4("4", "异动");

    public static final String DICT_TYPE = "student_info_status";
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
