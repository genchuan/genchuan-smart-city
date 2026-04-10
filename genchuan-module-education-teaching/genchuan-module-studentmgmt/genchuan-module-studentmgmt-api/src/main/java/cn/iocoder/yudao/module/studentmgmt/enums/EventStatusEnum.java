package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventStatusEnum {
    NOT_PUBLISH(0, "未发布"),
    PUBLISHED(1, "已发布");

    /**
     * 状态
     * <p>
     */
    private final Integer status;

    /**
     * 名字
     */
    private final String name;
}
