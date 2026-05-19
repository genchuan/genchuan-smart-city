package cn.iocoder.yudao.module.integratedsecurity.enums;


import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * enterprisesvc 错误码枚举类
 * enterprisesvc 系统，使用500错误码
 */
public interface ErrorCodeConstants {
    ErrorCode TIME_MONITOR_NOT_EXISTS = new ErrorCode(500, "监控记录不存在");
}
