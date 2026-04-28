package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 状态
 */
public enum RepairStatusEnum {

    PENDING_DISPATCH("pending_dispatch", "待派单"),
    REPAIRING("repairing", "维修中"),
    COMPLETED("completed", "已维修");

    public static final String DICT_TYPE = "repair_mgmt_status";
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
