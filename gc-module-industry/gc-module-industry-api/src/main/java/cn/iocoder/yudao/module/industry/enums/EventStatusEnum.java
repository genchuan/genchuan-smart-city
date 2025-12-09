package cn.iocoder.yudao.module.industry.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventStatusEnum {
    NOT_PAD(0, "待派单"),
    PADDED(1, "已派单");

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
