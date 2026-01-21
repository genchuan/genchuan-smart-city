package cn.iocoder.yudao.module.park.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * System 错误码枚举类
 * system 系统，使用 1-002-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== 收费异常==========
    ErrorCode CHARGE_ABNORMAL_NOT_EXISTS = new ErrorCode(500, "收费异常不存在");

}
