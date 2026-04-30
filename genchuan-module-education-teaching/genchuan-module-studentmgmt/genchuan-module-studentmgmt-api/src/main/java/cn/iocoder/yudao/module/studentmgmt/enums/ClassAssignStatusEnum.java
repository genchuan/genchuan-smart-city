package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 状态
 */
public enum ClassAssignStatusEnum {

    UNASSIGNED("unassigned", "未分班"),
    ASSIGNED("assigned", "已分班");

    public static final String DICT_TYPE = "class_assign_status";
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
