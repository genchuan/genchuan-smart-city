package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 出入申请状态枚举
 */
public enum AccessApplyStatusEnum {

//    待审批 / 已执行 / 已预警，关联芋道字典表：access_apply_status
    PENDING ("pending", "待审批"),
    APPROVE("approve", "已通过");

    public static final String DICT_TYPE = "access_apply_status";
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
