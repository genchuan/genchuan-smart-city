package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 状态
 */
public enum CheckInStatusEnum {

    // 状态（待确认 / 待审核 / 已通过，关联芋道字典表：check_in_status）
    PENDING_CONFIRM("pending_confirm", "待确认"),
    PENDING_AUDIT("pending_audit", "待审核"),
    CHECKED_IN("checked_in", "已报到");

    public static final String DICT_TYPE = "check_in_status";
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
