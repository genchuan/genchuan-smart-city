package cn.iocoder.yudao.module.stationresource.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * System 错误码枚举类
 * system 系统，使用 1-002-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== 片区信息==========
    ErrorCode AREA_INFO_NOT_EXISTS = new ErrorCode(500, "片区信息不存在");
    // ========== 场站信息==========
    ErrorCode STATION_INFO_NOT_EXISTS = new ErrorCode(500, "场站信息不存在");

}
