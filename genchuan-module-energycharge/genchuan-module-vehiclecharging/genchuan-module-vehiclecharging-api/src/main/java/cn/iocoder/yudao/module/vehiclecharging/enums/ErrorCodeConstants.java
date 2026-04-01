package cn.iocoder.yudao.module.vehiclecharging.enums;


import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * vehiclecharging 错误码枚举类
 * vehiclecharging 系统，使用500错误码
 */
public interface ErrorCodeConstants {
// ========== 实时监测 TODO 补充编号 ==========
    ErrorCode STATUS_MONITOR_NOT_EXISTS = new ErrorCode(500, "实时监测不存在");

    // ========== 充电车位==========
    ErrorCode CHARGING_LOT_NOT_EXISTS = new ErrorCode(500, "充电车位不存在");

    // ========== 互联互通表错误码=========
    ErrorCode INTERCONNECTION_NOT_EXISTS = new ErrorCode(500, "互联互通表不存在");
    ErrorCode INTERCONNECTION_STATUS_ERROR = new ErrorCode(500, "互联互通表状态错误");
}
