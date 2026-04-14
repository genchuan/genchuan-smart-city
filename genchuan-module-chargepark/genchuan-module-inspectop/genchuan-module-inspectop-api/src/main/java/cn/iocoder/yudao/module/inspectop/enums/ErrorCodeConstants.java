package cn.iocoder.yudao.module.inspectop.enums;


import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * inspectop 错误码枚举类
 * inspectop 系统，使用 500 错误码
 */
public interface ErrorCodeConstants {

    // ========== 车位状态监测==========
    ErrorCode SPACE_MONITOR_NOT_EXISTS = new ErrorCode(500, "车位状态监测不存在");



}
