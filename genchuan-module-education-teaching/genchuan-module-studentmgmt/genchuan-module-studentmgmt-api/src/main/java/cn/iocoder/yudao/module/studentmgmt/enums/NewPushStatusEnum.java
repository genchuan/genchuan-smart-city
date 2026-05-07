package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 状态
 */
public enum NewPushStatusEnum {

    UNPUSHED("unpushed", "未推送"),
    PUSHED("pushed", "已推送");

    public static final String DICT_TYPE = "new_push_status";
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
