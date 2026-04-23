package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 考评类型
 */
public enum AssessTypeEnum {

    CLASS_CLEAN("class_clean","'教室卫生评比'"),
    MORNING_EXERCISE("morning_exercise","'早操评比'"),
    CIVILIZED_CLASS("civilized_class","'文明班级评比'"),
    BLACKBOARD("blackboard","'黑板报评比'");

    public static final String DICT_TYPE = "assess_mgmt_assess_type";
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
