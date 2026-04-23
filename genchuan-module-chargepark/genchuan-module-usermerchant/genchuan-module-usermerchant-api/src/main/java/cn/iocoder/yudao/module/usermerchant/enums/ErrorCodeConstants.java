package cn.iocoder.yudao.module.usermerchant.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * System 错误码枚举类
 * system 系统，使用 1-002-000-000 段
 */
public interface ErrorCodeConstants {
    ErrorCode ILLEGAL_STATUS = new ErrorCode(500, "不合法的状态");
    // ========== 用户信息==========
    ErrorCode USER_INFO_NOT_EXISTS = new ErrorCode(500, "用户信息不存在");
    ErrorCode USER_INFO_NO_REACHED_LIMIT = new ErrorCode(500, "用户信息编号达到极限");
    // ========== 用户车辆==========
    ErrorCode USER_CAR_NOT_EXISTS = new ErrorCode(500, "用户车辆不存在");
    // ========== 车牌认证==========
    ErrorCode PLATE_AUTH_NOT_EXISTS = new ErrorCode(500, "车牌认证不存在");
    // ========== 商户信息==========
    ErrorCode MERCHANT_INFO_NOT_EXISTS = new ErrorCode(500, "商户信息不存在");
    // ========== 商户对接==========
    ErrorCode MERCHANT_LINK_NOT_EXISTS = new ErrorCode(500, "商户对接不存在");

}
