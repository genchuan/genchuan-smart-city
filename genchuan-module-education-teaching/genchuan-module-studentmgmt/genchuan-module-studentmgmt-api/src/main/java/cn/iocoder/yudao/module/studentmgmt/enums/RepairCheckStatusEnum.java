package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 状态
 */
public enum RepairCheckStatusEnum {

    unchecked("unchecked", "未验收"),
    checked("checked", "已验收");

    public static final String DICT_TYPE = "repair_mgmt_check_status";
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
