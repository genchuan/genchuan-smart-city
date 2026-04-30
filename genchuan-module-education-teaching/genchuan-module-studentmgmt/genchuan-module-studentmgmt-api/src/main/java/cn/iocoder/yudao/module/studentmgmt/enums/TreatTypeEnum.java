package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 就诊类型
 */
public enum TreatTypeEnum {

    // 状态（待确认 / 待审核 / 已通过，关联芋道字典表：stay_mgmt_status）
    OUTPATIENT("outpatient", "门诊"),
    EMERGENCY("emergency", "急诊"),
    OTHER("other", "其他");

    public static final String DICT_TYPE = "treat_mgmt_treat_type";
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
