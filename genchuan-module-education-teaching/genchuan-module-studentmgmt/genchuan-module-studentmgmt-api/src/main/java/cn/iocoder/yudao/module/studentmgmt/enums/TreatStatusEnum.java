package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 状态
 */
public enum TreatStatusEnum {

    // 状态（待确认 / 待审核 / 已通过，关联芋道字典表：stay_mgmt_status）
    PENDING("pending", "待审核"),
    VISITED("visited", "已就诊");

    public static final String DICT_TYPE = "treat_mgmt_status";
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
