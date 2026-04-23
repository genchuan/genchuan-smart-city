package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 调班状态
 */
public enum DutyTransferStatusEnum {

    // 调班状态：无/待审批/已通过/已驳回，关联芋道字典表：duty_mgmt_transfer_status
    TRANSFER_STATUS_PENDING_NONE("none", "无"),
    TRANSFER_STATUS_PENDING_PENDING("pending", "待审批"),
    TRANSFER_STATUS_PENDING_APPROVED("approved", "已通过"),
    TRANSFER_STATUS_REJECTED("rejected", "已驳回");


    /**
     * 状态
     * <p>
     */
    private final String status;

    /**
     * 名字
     */
    private final String name;

    // 根据key获取名称
    public static String getNameByKey(String key) {
        for (DutyTransferStatusEnum value : values()) {
            if (value.status.equals(key)) {
                return value.name;
            }
        }
        return null;
    }


}
