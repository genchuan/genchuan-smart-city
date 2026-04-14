package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 考评状态
 */
public enum AssessStatusEnum {

    UN_PUBLISH("un_publish", "未发布"),
    PUBLISHED("published", "已发布"),
    ;

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
