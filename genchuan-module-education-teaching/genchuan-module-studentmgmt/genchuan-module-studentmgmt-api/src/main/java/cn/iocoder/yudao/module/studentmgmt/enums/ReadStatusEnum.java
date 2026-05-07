package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 新阅读状态
 */
public enum ReadStatusEnum {

    // 阅读状态：未读/已读，关联芋道字典表：parent_reply_read_status

    UNREAD("unread", "未读"),
    READ("read", "已读");

    public static final String DICT_TYPE = "parent_reply_read_status";
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
