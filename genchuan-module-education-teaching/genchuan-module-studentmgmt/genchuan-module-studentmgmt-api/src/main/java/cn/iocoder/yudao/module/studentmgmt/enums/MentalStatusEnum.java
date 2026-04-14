package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 状态
 */
public enum MentalStatusEnum {

    // 状态：待评估/咨询中/已干预，关联芋道字典表：mental_mgmt_status
    MENTAL_STATUS_WAIT_EVALUATE("wait_evaluate", "待评估"),
    MENTAL_STATUS_CONSULTING("consulting", "咨询中"),
    MENTAL_STATUS_INTERVENED("intervened", "已干预");

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
