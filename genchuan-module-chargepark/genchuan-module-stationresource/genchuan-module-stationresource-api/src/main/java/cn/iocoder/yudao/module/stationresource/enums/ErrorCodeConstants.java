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
    // ========== 场站配置==========
    ErrorCode STATION_CONFIG_NOT_EXISTS = new ErrorCode(500, "场站配置不存在");
    // ========== 联合追缴拓场配置==========
    ErrorCode DEBT_EXPAND_NOT_EXISTS = new ErrorCode(500, "联合追缴拓场配置不存在");
    // ========== 车位信息==========
    ErrorCode PARKING_SPACE_INFO_NOT_EXISTS = new ErrorCode(500, "车位信息不存在");

    // ========== 时段权限==========
    ErrorCode TIME_PERMISSION_NOT_EXISTS = new ErrorCode(500, "时段权限不存在");

    // ========== 黑白名单==========
    ErrorCode BLACK_WHITE_LIST_NOT_EXISTS = new ErrorCode(500, "黑白名单不存在");
    // ========== 充停联动==========
    ErrorCode CHARGE_PARK_LINK_NOT_EXISTS = new ErrorCode(500, "充停联动不存在");
    // ========== 押金方案==========
    ErrorCode DEPOSIT_PLAN_NOT_EXISTS = new ErrorCode(500, "押金方案不存在");
    // ========== 收费规则==========
    ErrorCode FEE_RULE_NOT_EXISTS = new ErrorCode(500, "收费规则不存在");
    // ========== 错时规则==========
    ErrorCode OFFTIME_RULE_NOT_EXISTS = new ErrorCode(500, "错时规则不存在");

}
