package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 状态
 */
public enum StayStatusEnum {

    PENDING_CONFIRM("pending_confirm", "待确认"),
    PENDING_AUDIT("pending_audit", "待审核"),
    PASSED("passed", "通过");

    public static final String DICT_TYPE = "stay_mgmt_status";
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
