package cn.iocoder.yudao.module.chargepark.carservice.enums.complaint;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 意见建议状态枚举
 * 关联字典：suggestion_status
 */
@Getter
@AllArgsConstructor
public enum SuggestionStatusEnum {

    PENDING("待处理"),
    PROCESSING("处理中"),
    COMPLETED("已完成");

    private final String label;

}
