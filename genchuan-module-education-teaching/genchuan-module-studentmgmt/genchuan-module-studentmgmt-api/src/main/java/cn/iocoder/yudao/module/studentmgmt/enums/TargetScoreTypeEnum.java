package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 目标管理计分方式
 */
public enum TargetScoreTypeEnum {

    cumulative("cumulative","'通过累加方式计分'"),
    api("api","'通过接口方式计分'");

    public static final String DICT_TYPE = "target_mgmt_score_type";

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
        for (TargetScoreTypeEnum value : values()) {
            if (value.status.equals(key)) {
                return value.name;
            }
        }
        return null;
    }
}
