package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 目标管理计分方式
 */
public enum TargetTypeEnum {

    cumulative("cumulative","'通过累加方式计分'"),
    api("api","'通过接口方式计分'");

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
        for (TargetTypeEnum value : values()) {
            if (value.status.equals(key)) {
                return value.name;
            }
        }
        return null;
    }
}
