package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 评比管理状态枚举
 */
public enum CompareStatusEnum {

    SCORING("scoring","'打分中'"),
    SUMMARIZED("summarized","'已汇总'");

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
        for (CompareStatusEnum value : values()) {
            if (value.status.equals(key)) {
                return value.name;
            }
        }
        return null;
    }
}
