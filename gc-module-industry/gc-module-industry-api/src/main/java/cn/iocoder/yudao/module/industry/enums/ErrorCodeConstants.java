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
    ErrorCode PARK_WALLET_RECHARGE_NOT_EXISTS = new ErrorCode(500, "钱包充值不存在");
    // ========== 停车系统用户  ==========
    ErrorCode PARK_USER_NOT_EXISTS = new ErrorCode(500, "停车系统用户不存在");
    // ========== 商户==========
    ErrorCode PARK_MERCHANT_NOT_EXISTS = new ErrorCode(500, "商户不存在");
    // ========== 商户权限==========
    ErrorCode PARK_MERCHANT_PERMISSION_NOT_EXISTS = new ErrorCode(500, "商户权限不存在");
    // ========== 访客==========
    ErrorCode PARK_VISITOR_NOT_EXISTS = new ErrorCode(500, "访客不存在");

    // ========== 车位信息 ==========
    ErrorCode PARK_SPACE_NOT_EXISTS = new ErrorCode(601_001, "车位信息不存在");

    // ========== 车库信息 ==========
    ErrorCode PARK_GARAGE_NOT_EXISTS = new ErrorCode(601_002, "车库信息不存在");

    // ========== 车场信息 ==========
    ErrorCode PARK_LOT_NOT_EXISTS = new ErrorCode(601_003, "车场信息不存在");
    // ========== 出入口信息 ==========
    ErrorCode PARK_ENTRY_EXIT_NOT_EXISTS = new ErrorCode(601_004, "出入口信息不存在");
    // ========== 路侧泊位 ==========
    ErrorCode PARK_ROADSIDE_NOT_EXISTS = new ErrorCode(601_005, "路侧泊位不存在");

    // ========== 黑白名单==========
    ErrorCode PARK_BLACK_WHITE_LIST_NOT_EXISTS = new ErrorCode(500, "黑白名单不存在");
    // ========== 运维排班==========
    ErrorCode PARK_MAINTAIN_SCHEDULE_NOT_EXISTS = new ErrorCode(500, "运维排班不存在");
    // ========== 运维人员==========
    ErrorCode PARK_MAINTAIN_USER_NOT_EXISTS = new ErrorCode(500, "运维人员不存在");
    // ========== 代付规则==========
    ErrorCode PARK_PAYMENT_PROXY_NOT_EXISTS = new ErrorCode(500, "代付规则不存在");

    // ========== 在停车辆 ==========
    ErrorCode PARK_CAR_PARKING_NOT_EXISTS = new ErrorCode(500, "在停车辆不存在");
    // ========== 入场记录 ==========
    ErrorCode PARK_CAR_ENTRY_NOT_EXISTS = new ErrorCode(500, "入场记录不存在");
    // ========== 通行规则 ==========
    ErrorCode PARK_PASS_RULE_NOT_EXISTS = new ErrorCode(500, "通行规则不存在");
    // ========== 资源台账 ==========
    ErrorCode PARK_RESOURCE_ACCOUNT_NOT_EXISTS = new ErrorCode(500, "资源台账不存在");
}
