package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 状态
 */
public enum CheckInAccountStatusEnum {

    NOT_CREATED("not_created", "未创建"),
    CREATED("created", "已创建");

    public static final String DICT_TYPE = "check_in_account_status";
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
