package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 状态
 */
public enum LeaveHandleCheckoutStatusEnum {

    NOT_CHECKED_OUT("not_checked_out", "未退宿"),
    CHECKED_OUT("checked_out", "已退宿");

    public static final String DICT_TYPE = "leave_handle_checkout_status";
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
