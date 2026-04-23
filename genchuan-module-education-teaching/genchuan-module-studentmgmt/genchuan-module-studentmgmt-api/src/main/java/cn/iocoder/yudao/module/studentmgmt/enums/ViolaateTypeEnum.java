package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 违纪类型
 */
public enum ViolaateTypeEnum {
    //违纪类型（仪容仪表 / 行为违规 / 其他，关联芋道字典表：violate_mgmt_violate_type）
    VIOLATE_MGMT_VIOLATE_TYPE_APPEARANCE ("appearance", "仪容仪表"),
    VIOLATE_MGMT_VIOLATE_TYPE_BEHAVIOR("behavior", "行为违规"),
    VIOLATE_MGMT_VIOLATE_TYPE_OTHER("other", "其他");

    public static final String DICT_TYPE = "violate_mgmt_violate_type";
    /**
     * 状态
     * <p>
     */
    private final String type;

    /**
     * 名字
     */
    private final String name;



}
