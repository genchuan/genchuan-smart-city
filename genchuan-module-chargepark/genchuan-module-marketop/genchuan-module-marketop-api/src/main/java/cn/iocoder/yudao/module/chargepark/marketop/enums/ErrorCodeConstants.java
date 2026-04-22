package cn.iocoder.yudao.module.chargepark.marketop.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * 营销运营模块 错误码常量
 *
 * 营销运营模块，使用 1-100-000 段
 */
public interface ErrorCodeConstants {

    // ========== 积分活动 1-100-001 ~ 1-100-050 ==========
    ErrorCode POINT_ACTIVITY_NOT_EXISTS = new ErrorCode(1_100_001, "积分活动不存在");
    ErrorCode POINT_ACTIVITY_NAME_EXISTS = new ErrorCode(1_100_002, "积分活动名称已存在");
    ErrorCode POINT_ACTIVITY_STATUS_ERROR = new ErrorCode(1_100_003, "积分活动状态流转异常");

    ErrorCode POINT_LOTTERY_NOT_EXISTS = new ErrorCode(1_100_011, "积分抽奖记录不存在");
    ErrorCode POINT_LOTTERY_STATUS_ERROR = new ErrorCode(1_100_012, "积分抽奖记录状态异常");

    ErrorCode RULE_CONFIG_NOT_EXISTS = new ErrorCode(1_100_021, "规则配置不存在");
    ErrorCode RULE_CONFIG_NAME_EXISTS = new ErrorCode(1_100_022, "规则名称已存在");

    ErrorCode PRIZE_MGMT_NOT_EXISTS = new ErrorCode(1_100_031, "奖品不存在");
    ErrorCode PRIZE_MGMT_NAME_EXISTS = new ErrorCode(1_100_032, "奖品名称已存在");

    // ========== 优惠活动 1-100-051 ~ 1-100-100 ==========
    ErrorCode COUPON_MGMT_NOT_EXISTS = new ErrorCode(1_100_051, "优惠券不存在");
    ErrorCode COUPON_MGMT_NAME_EXISTS = new ErrorCode(1_100_052, "优惠券名称已存在");
    ErrorCode COUPON_MGMT_STATUS_ERROR = new ErrorCode(1_100_053, "优惠券状态异常");

    ErrorCode ACTIVITY_CONFIG_NOT_EXISTS = new ErrorCode(1_100_061, "活动配置不存在");
    ErrorCode ACTIVITY_CONFIG_NAME_EXISTS = new ErrorCode(1_100_062, "活动名称已存在");

    ErrorCode PACKAGE_CONFIG_NOT_EXISTS = new ErrorCode(1_100_071, "券包配置不存在");
    ErrorCode PACKAGE_CONFIG_NAME_EXISTS = new ErrorCode(1_100_072, "券包名称已存在");

    ErrorCode RECEIVE_RECORD_NOT_EXISTS = new ErrorCode(1_100_081, "领用记录不存在");
    ErrorCode RECEIVE_RECORD_STATUS_ERROR = new ErrorCode(1_100_082, "领用记录状态异常");

    // ========== 卡种管理 1-100-101 ~ 1-100-150 ==========
    ErrorCode CARD_ORDER_NOT_EXISTS = new ErrorCode(1_100_101, "卡种订单不存在");
    ErrorCode CARD_ORDER_STATUS_ERROR = new ErrorCode(1_100_102, "卡种订单状态异常");

    ErrorCode CARD_CONFIG_NOT_EXISTS = new ErrorCode(1_100_111, "卡种配置不存在");
    ErrorCode CARD_CONFIG_NAME_EXISTS = new ErrorCode(1_100_112, "卡种名称已存在");

    ErrorCode STOCK_CONTROL_NOT_EXISTS = new ErrorCode(1_100_121, "库存记录不存在");
    ErrorCode STOCK_INSUFFICIENT = new ErrorCode(1_100_122, "库存不足");

    // ========== 兑换管理 1-100-151 ~ 1-100-200 ==========
    ErrorCode EXCHANGE_CATEGORY_NOT_EXISTS = new ErrorCode(1_100_151, "兑换类目不存在");
    ErrorCode EXCHANGE_CATEGORY_NAME_EXISTS = new ErrorCode(1_100_152, "兑换类目名称已存在");

    ErrorCode EXCHANGE_ORDER_NOT_EXISTS = new ErrorCode(1_100_161, "兑换订单不存在");
    ErrorCode EXCHANGE_ORDER_STATUS_ERROR = new ErrorCode(1_100_162, "兑换订单状态异常");

    // ========== 决策分析 1-100-201 ~ 1-100-250 ==========
    ErrorCode MARKET_OP_REPORT_NOT_EXISTS = new ErrorCode(1_100_201, "营销运营报表不存在");
    ErrorCode CYCLE_REPORT_NOT_EXISTS = new ErrorCode(1_100_202, "周期报表不存在");

}
