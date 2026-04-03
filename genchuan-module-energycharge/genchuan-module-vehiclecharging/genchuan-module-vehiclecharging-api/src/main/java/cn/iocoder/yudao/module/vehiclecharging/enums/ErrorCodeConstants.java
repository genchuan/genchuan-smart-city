package cn.iocoder.yudao.module.vehiclecharging.enums;


import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * vehiclecharging 错误码枚举类
 * vehiclecharging 系统，使用500错误码
 */
public interface ErrorCodeConstants {
// ========== 实时监测 TODO 补充编号 ==========
    ErrorCode STATUS_MONITOR_NOT_EXISTS = new ErrorCode(500, "实时监测不存在");

    // ========== 充电站==========
    ErrorCode CHARGING_STATION_NOT_EXISTS = new ErrorCode(500, "充电站不存在");
    ErrorCode CHARGING_STATION_CODE_EXISTS = new ErrorCode(500, "充电站编号已存在");
    // ========== 充电桩告警==========
    ErrorCode PILEALARM_NOT_EXISTS = new ErrorCode(500, "充电桩告警不存在");



    // ========== 充电车位==========
    ErrorCode CHARGING_LOT_NOT_EXISTS = new ErrorCode(500, "充电车位不存在");

    // ========== 互联互通表错误码=========
    ErrorCode INTERCONNECTION_NOT_EXISTS = new ErrorCode(500, "互联互通表不存在");
    ErrorCode INTERCONNECTION_STATUS_ERROR = new ErrorCode(500, "互联互通表状态错误");
    // ========== 费率设置==========
    ErrorCode RATE_SETTING_NOT_EXISTS = new ErrorCode(500, "费率设置不存在");


    // ========== 订单告警 ==========
    ErrorCode ORDER_ALARM_NOT_EXISTS = new ErrorCode(500, "订单告警不存在");
    // ========== 订单告警状态错误==========
    ErrorCode ORDER_ALARM_STATUS_ERROR = new ErrorCode(500, "订单告警状态错误");
    // ========== 订单不存在==========
    ErrorCode ORDER_NOT_EXISTS = new ErrorCode(500, "订单不存在");

    // ========== 订单退款==========
    ErrorCode ORDER_REFUND_NOT_EXISTS = new ErrorCode(500, "订单退款不存在");

}
