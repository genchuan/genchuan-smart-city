package cn.iocoder.yudao.module.chargepark.carservice.enums.rescue;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 救援归档状态枚举
 * 关联字典：rescue_info_archive_status
 */
@Getter
@AllArgsConstructor
public enum RescueArchiveStatusEnum {

    UNARCHIVED("未归档"),
    ARCHIVED("已归档");

    private final String label;

}
