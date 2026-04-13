package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 处分类型
 */
public enum PunishTypeEnum {
    // 处分类型（警告 / 记过 / 留校察看 / 开除，关联芋道字典表：violate_mgmt_punish_type）
    VIOLATE_MGMT_PUNISH_TYPE_WARN(1, "警告"),
    VIOLATE_MGMT_PUNISH_TYPE_DEMERIT(2, "记过"),
    VIOLATE_MGMT_PUNISH_TYPE_PROBATION(3, "留校察看"),
    VIOLATE_MGMT_PUNISH_TYPE_DISMISSAL(4, "开除");

    /**
     * 状态
     * <p>
     */
    private final Integer type;

    /**
     * 名字
     */
    private final String name;

}
