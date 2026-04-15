package cn.iocoder.yudao.module.usermerchant.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * System 错误码枚举类
 * system 系统，使用 1-002-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== 片区信息==========
    ErrorCode USER_INFO_NOT_EXISTS = new ErrorCode(500, "用户信息不存在");

}
