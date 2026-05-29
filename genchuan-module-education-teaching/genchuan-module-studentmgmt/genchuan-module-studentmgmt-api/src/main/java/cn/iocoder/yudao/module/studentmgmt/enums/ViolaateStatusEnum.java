package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 违纪类型
 */
public enum ViolaateStatusEnum {

//    待审批 / 已执行 / 已预警，关联芋道字典表：violate_mgmt_status
    VIOLATE_MGMT_VIOLATE_STATUS_PENDING ("pending", "待审批"),
    VIOLATE_MGMT_VIOLATE_STATUS_APPROVED("executed", "已执行"),
    VIOLATE_MGMT_VIOLATE_STATUS_WARNED("warned", "已预警");

    public static final String DICT_TYPE = "violate_mgmt_status";
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
