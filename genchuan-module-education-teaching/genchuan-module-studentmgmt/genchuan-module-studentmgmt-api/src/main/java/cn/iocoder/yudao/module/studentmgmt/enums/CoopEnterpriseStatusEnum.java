package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 状态
 */
public enum CoopEnterpriseStatusEnum {
    // 状态（合作中 / 已结束，关联芋道字典表：coop_enterprise_status）
    PENDING("cooperating", "合作中"),
    ENDED("ended", "已结束");

    public static final String DICT_TYPE = "coop_enterprise_status";
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
