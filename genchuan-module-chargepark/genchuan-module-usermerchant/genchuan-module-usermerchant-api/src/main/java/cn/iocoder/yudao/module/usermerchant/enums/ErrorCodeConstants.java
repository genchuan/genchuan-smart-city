package cn.iocoder.yudao.module.usermerchant.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * System 错误码枚举类
 * system 系统，使用 1-002-000-000 段
 */
public interface ErrorCodeConstants {

    ErrorCode EMPTY_LIST = new ErrorCode(500, "数据为空");
    ErrorCode ILLEGAL_FORMAT = new ErrorCode(500, "不合法的格式");
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
    // ========== 商户充值==========
    ErrorCode MERCHANT_RECHARGE_NOT_EXISTS = new ErrorCode(500, "商户充值不存在");
    ErrorCode ORDER_NO_REACHED_LIMIT = new ErrorCode(500, "充值订单编号达到极限");
    // ========== 商户发券==========
    ErrorCode MERCHANT_SEND_COUPON_NOT_EXISTS = new ErrorCode(500, "商户发券不存在");
    // ========== 集团信息==========
    ErrorCode GROUP_INFO_NOT_EXISTS = new ErrorCode(500, "集团信息不存在");
    // ========== 集团车辆==========
    ErrorCode GROUP_CAR_NOT_EXISTS = new ErrorCode(500, "集团车辆不存在");
    // ========== 会员配置==========
    ErrorCode MEMBER_CONFIG_NOT_EXISTS = new ErrorCode(500, "会员配置不存在");
    // ========== 会员用户==========
    ErrorCode MEMBER_USER_NOT_EXISTS = new ErrorCode(500, "会员用户不存在");
    // ========== 会员标签==========
    ErrorCode MEMBER_TAG_NOT_EXISTS = new ErrorCode(500, "会员标签不存在");
    // ========== 会员签到==========
    ErrorCode MEMBER_SIGN_NOT_EXISTS = new ErrorCode(500, "会员签到不存在");
    // ========== 会员等级==========
    ErrorCode MEMBER_LEVEL_NOT_EXISTS = new ErrorCode(500, "会员等级不存在");
    // ========== 会员分组==========
    ErrorCode MEMBER_GROUP_NOT_EXISTS = new ErrorCode(500, "会员分组不存在");
    // ========== 会员积分==========
    ErrorCode MEMBER_POINT_NOT_EXISTS = new ErrorCode(500, "会员积分不存在");
    ErrorCode MEMBER_POINT_ALREADY_NORMAL = new ErrorCode(500, "会员积分已为正常");
    // ========== 用户信用==========
    ErrorCode USER_CREDIT_NOT_EXISTS = new ErrorCode(500, "用户信用不存在");
    // ========== 信用配置==========
    ErrorCode CREDIT_CONFIG_NOT_EXISTS = new ErrorCode(500, "信用配置不存在");
    // ========== 周期报表存储==========
    ErrorCode CYCLE_REPORT_NOT_EXISTS = new ErrorCode(500, "周期报表存储不存在");

}
