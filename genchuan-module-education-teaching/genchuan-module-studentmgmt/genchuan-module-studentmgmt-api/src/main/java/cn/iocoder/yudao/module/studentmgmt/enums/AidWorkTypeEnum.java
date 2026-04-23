package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 类型
 */
public enum AidWorkTypeEnum {

    // 关联芋道字典表：aid_work_aid_type
    AID_WORK_STATUS_1("1", "奖学金"),
    AID_WORK_STATUS_2("2", "助学金"),
    AID_WORK_STATUS_3("3", "助学金"),
    AID_WORK_STATUS_4("4", "助学贷款");

    public static final String DICT_TYPE = "aid_work_aid_type";

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
        for (AidWorkTypeEnum value : values()) {
            if (value.status.equals(key)) {
                return value.name;
            }
        }
        return null;
    }

}
