package cn.iocoder.yudao.module.facility.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * System 错误码枚举类
 * system 系统，使用 1-002-000-000 段
 */
public interface ErrorCodeConstants {
    // ========== 道路监测==========
    ErrorCode MONITOR_NOT_EXISTS = new ErrorCode(500, "道路监测不存在");
    // ========== 道路监测配置==========
    ErrorCode ROAD_CONFIG_NOT_EXISTS = new ErrorCode(500, "道路监测配置不存在");

}
