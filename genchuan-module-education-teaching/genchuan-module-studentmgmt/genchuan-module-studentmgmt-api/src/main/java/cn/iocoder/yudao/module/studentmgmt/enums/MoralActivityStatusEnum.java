package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 德育活动状态
 */
public enum MoralActivityStatusEnum {

    UNPUBLISHED("unpublished","'未发布'"),
    ONGOING("ongoing","'进行中'"),
    ENDED("ended","'已结束'");

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
        for (MoralActivityStatusEnum value : values()) {
            if (value.status.equals(key)) {
                return value.name;
            }
        }
        return null;
    }
}
