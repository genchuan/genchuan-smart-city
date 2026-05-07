package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 状态
 */
public enum ParentReplyReadStatusEnum {

    // 回复状态：未回复/已回复，关联芋道字典表：parent_reply_status
    UNREPLIED("unreplied", "未回复"),
    REPLIED("replied", "已回复");

    public static final String DICT_TYPE = "parent_reply_status";
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
