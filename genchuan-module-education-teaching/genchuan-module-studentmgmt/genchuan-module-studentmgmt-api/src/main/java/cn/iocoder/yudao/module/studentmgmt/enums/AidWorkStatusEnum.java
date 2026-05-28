package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 状态
 */
public enum AidWorkStatusEnum {

    // 状态（待审核 / 已通过 / 已完成），关联芋道字典表：aid_work_status
    AID_WORK_STATUS_0("0", "待审核"),
    AID_WORK_STATUS_1("1", "已通过"),
    AID_WORK_STATUS_2("2", "已完成");
    public static final String DICT_TYPE = "aid_work_status";
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
        for (AidWorkStatusEnum value : values()) {
            if (value.status.equals(key)) {
                return value.name;
            }
        }
        return "";
    }

}
