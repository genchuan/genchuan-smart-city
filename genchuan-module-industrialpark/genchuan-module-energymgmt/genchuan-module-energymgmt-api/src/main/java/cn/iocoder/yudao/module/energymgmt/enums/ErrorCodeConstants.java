package cn.iocoder.yudao.module.energymgmt.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * energymgmt 错误码枚举类
 * energymgmt 系统，使用500错误码
 */
public interface ErrorCodeConstants {

    // ========== 通用==========
    ErrorCode ILLEGAL_STATUS = new ErrorCode(500, "非法的状态");
    // ========== 能耗采集==========
    ErrorCode ENERGY_COLLECT_NOT_EXISTS = new ErrorCode(500, "能耗采集不存在");
    // ========== 分区能耗 ==========
    ErrorCode AREA_MONITOR_NOT_EXISTS = new ErrorCode(500, "分区能耗不存在");

}
