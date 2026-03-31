package cn.iocoder.yudao.module.vehiclecharging.enums;


import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * vehiclecharging 错误码枚举类
 * vehiclecharging 系统，使用500错误码
 */
public interface ErrorCodeConstants {

    // ========== 充电车位==========
    ErrorCode CHARGING_LOT_NOT_EXISTS = new ErrorCode(500, "充电车位不存在");


}
