package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 违纪类型
 */
public enum DormCompareStatusEnum {

//状态（打分中 / 已汇总），关联芋道字典表：dorm_compare_status。
    DORM_COMPARE_STATUS_UN_SCORED ("unscored", "未打分"),
    DORM_COMPARE_STATUS_SCORING ("scoring", "打分中"),
    DORM_COMPARE_STATUS_SUMMARIZED("summarized", "已汇总");

    public static final String DICT_TYPE = "dorm_compare_status";
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
