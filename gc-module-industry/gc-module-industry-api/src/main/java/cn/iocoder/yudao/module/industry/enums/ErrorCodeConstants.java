package cn.iocoder.yudao.module.industry.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * System 错误码枚举类
 * system 系统，使用 1-002-000-000 段
 */
public interface ErrorCodeConstants {
    //管理部件找不到
    ErrorCode MNG_COMP_NOT_EXISTS = new ErrorCode(100_001, "找不到管理部件");

    //=============一、通用大屏模版=================================================

    // ========== 通用场景表，一级和二级场景  ==========
    ErrorCode UNIVERSAL_SCENE_NOT_EXISTS = new ErrorCode(500_001, "通用场景表，一级和二级场景不存在");

    // ========== 场景字段   ==========
    ErrorCode SCENE_FIELD_NOT_EXISTS = new ErrorCode(500_002, "场景字段不存在");

    // ========== 场景字段状态映射  ==========
    ErrorCode SELECTED_FIELD_STATUS_MAP_NOT_EXISTS = new ErrorCode(500_003, "场景字段状态映射不存在");
    // ========== 场景状态字段图标配置  ==========
    ErrorCode SCENE_STATUS_ICON_NOT_EXISTS = new ErrorCode(500_004, "场景状态字段图标配置不存在");
    // ========== 场景地图整体配置  ==========
    ErrorCode SCENE_MAP_CONFIG_NOT_EXISTS = new ErrorCode(500_005, "场景地图整体配置不存在");


// ========== 停车订单
    ErrorCode PARK_WO_NOT_EXISTS = new ErrorCode(600_001, "停车订单不存在");
    // ========== 停车缴费服务  ==========
    ErrorCode PARK_PAY_NOT_EXISTS = new ErrorCode(600_002, "停车缴费服务不存在");
    // ========== 欠费追缴  ==========
    ErrorCode PARK_ARREARS_RECOVERY_NOT_EXISTS = new ErrorCode(600_003, "欠费追缴不存在");

    // ========== 停车预约服务  ==========
    ErrorCode PARK_RESERVATION_NOT_EXISTS = new ErrorCode(600_004, "停车预约服务不存在");
    // ========== 停车泊位实时状态  ==========
    ErrorCode PARK_REAL_TIME_NOT_EXISTS = new ErrorCode(600_005, "停车泊位实时状态不存在");
    // ========== 泊位锁定记录  ==========
    ErrorCode PARK_BERTH_LOCK_NOT_EXISTS = new ErrorCode(600_006, "泊位锁定记录不存在");
    // ========== 缴费统计  ==========
    ErrorCode STAT_PARK_PAY_NOT_EXISTS = new ErrorCode(600_007, "缴费统计不存在");
    // ========== 停车诱导服务  ==========
    ErrorCode PARK_GUIDANCE_NOT_EXISTS = new ErrorCode(600_008, "停车诱导服务不存在");
    // ========== 放行记录   ==========
    ErrorCode PARK_RELEASE_RECORD_NOT_EXISTS = new ErrorCode(600_009 , "放行记录不存在");
    // ========== 优惠活动  ==========
    ErrorCode PARK_DISCOUNT_ACTIVITY_NOT_EXISTS = new ErrorCode(600_010, "优惠活动不存在");

    // ========== 临停订单  ==========
    ErrorCode ORDER_TEMP_NOT_EXISTS = new ErrorCode(600_011, "临停订单不存在");
    // ========== 临停订单  ==========
    ErrorCode PARK_ORDER_TEMP_NOT_EXISTS = new ErrorCode(600_012, "临停订单不存在");
    // ========== 期卡订单  ==========
    ErrorCode PARK_ORDER_PERIOD_NOT_EXISTS = new ErrorCode(600_013, "期卡订单不存在");
    // ========== 退款订单  ==========
    ErrorCode ORDER_REFUND_NOT_EXISTS = new ErrorCode(600_014, "退款订单不存在");
    // ========== 逃费订单  ==========
    ErrorCode ORDER_ESCAPE_NOT_EXISTS = new ErrorCode(600_015, "逃费订单不存在");
    // ========== 分账结算表 ==========
    ErrorCode PARK_SETTLEMENT_NOT_EXISTS = new ErrorCode(600_016, "分账结算表不存在");
    // ========== 欠费记录 ==========
    ErrorCode PARK_ARREARS_NOT_EXISTS = new ErrorCode(600_017, "欠费记录不存在");
    // ========== 钱包充值  ==========
    ErrorCode PARK_WALLET_RECHARGE_NOT_EXISTS = new ErrorCode(600_018, "钱包充值不存在");
}
