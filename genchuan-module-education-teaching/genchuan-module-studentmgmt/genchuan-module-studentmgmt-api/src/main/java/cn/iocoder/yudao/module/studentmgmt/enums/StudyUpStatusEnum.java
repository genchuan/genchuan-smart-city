package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 状态
 */
public enum StudyUpStatusEnum {
    //    状态（待规划 / 已规划），关联芋道字典表：study_up_status
    PENDING_PLAN("pending_plan", "待规划"),
    PLANNED("planned", "已规划");

    public static final String DICT_TYPE = "study_up_status";
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
