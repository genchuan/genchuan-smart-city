package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 状态
 */
public enum LeaveHandleStatusEnum {

    PENDING_CONFIRM("pending_confirm", "待确认"),
    PENDING_HANDLE("pending_handle", "待办理"),
    LEFT("left", "已离校");

    public static final String DICT_TYPE = "leave_handle_status";
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
