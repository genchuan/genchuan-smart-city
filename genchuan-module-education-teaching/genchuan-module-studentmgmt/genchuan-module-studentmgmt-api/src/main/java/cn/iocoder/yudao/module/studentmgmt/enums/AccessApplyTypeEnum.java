package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 出入申请状态枚举
 */
public enum AccessApplyTypeEnum {

//    申请类型（应急出入 / 其他，关联芋道字典表：access_apply_apply_type）
    EMERGENCY ("emergency", "应急出入"),
    OTHER("other", "其他");

    public static final String DICT_TYPE = "access_apply_apply_type";
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
