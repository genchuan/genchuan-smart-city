package cn.iocoder.yudao.module.chargepark.carservice.enums.complaint;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 纠纷调解状态枚举
 * 关联字典：dispute_mediate_status
 */
@Getter
@AllArgsConstructor
public enum DisputeMediateStatusEnum {

    WAITING_MEDIATE("待调解"),
    MEDIATING("调解中"),
    COMPLETED("已完成"),
    CLOSED("已关闭");

    private final String label;

}
