package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 状态
 */
public enum RegisterStatusEnum {

    PENDING("pending", "待审核"),
    ADMITTED("admitted", "已录取");

    public static final String DICT_TYPE = "register_mgmt_status";
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
