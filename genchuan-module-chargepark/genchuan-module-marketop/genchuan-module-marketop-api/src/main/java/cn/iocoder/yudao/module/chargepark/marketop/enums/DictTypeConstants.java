package cn.iocoder.yudao.module.chargepark.marketop.enums;

/**
 * marketop 字典类型的枚举类
 */
public interface DictTypeConstants {

    // ========== 积分活动模块 ==========
    String POINT_ACTIVITY_TYPE = "point_activity_type"; // 积分活动类型
    String POINT_ACTIVITY_STATUS = "point_activity_status"; // 积分活动状态
    String POINT_LOTTERY_STATUS = "point_lottery_status"; // 积分抽奖记录状态
    String POINT_LOTTERY_SYNC_STATUS = "point_lottery_sync_status"; // 积分抽奖同步状态
    String RULE_CONFIG_TYPE = "rule_config_type"; // 规则配置类型
    String RULE_CONFIG_STATUS = "rule_config_status"; // 规则配置状态
    String RULE_CONFIG_SCENE = "rule_config_scene"; // 规则适用场景
    String PRIZE_MGMT_TYPE = "prize_mgmt_type"; // 奖品类型
    String PRIZE_MGMT_STATUS = "prize_mgmt_status"; // 奖品状态

    // ========== 优惠活动模块 ==========
    String COUPON_MGMT_TYPE = "coupon_mgmt_type"; // 优惠券类型
    String COUPON_MGMT_STATUS = "coupon_mgmt_status"; // 优惠券状态
    String ACTIVITY_CONFIG_TYPE = "activity_config_type"; // 活动配置类型
    String ACTIVITY_CONFIG_STATUS = "activity_config_status"; // 活动配置状态
    String ACTIVITY_CONFIG_USER_GROUP = "activity_config_user_group"; // 活动配置适用人群
    String PACKAGE_CONFIG_TYPE = "package_config_type"; // 券包配置类型
    String PACKAGE_CONFIG_STATUS = "package_config_status"; // 券包配置状态
    String PACKAGE_CONFIG_SCOPE = "package_config_scope"; // 券包适用范围
    String RECEIVE_RECORD_STATUS = "receive_record_status"; // 领用记录状态
    String RECEIVE_RECORD_SYNC_STATUS = "receive_record_sync_status"; // 领用记录同步状态

    // ========== 卡种管理模块 ==========
    String CARD_ORDER_PAY_STATUS = "card_order_pay_status"; // 卡种订单支付状态
    String CARD_ORDER_INVOICE_STATUS = "card_order_invoice_status"; // 卡种订单开票状态
    String CARD_CONFIG_TYPE = "card_config_type"; // 卡种类型
    String CARD_CONFIG_STATUS = "card_config_status"; // 卡种配置状态
    String CARD_CONFIG_SCOPE = "card_config_scope"; // 卡种适用范围
    String STOCK_CONTROL_STATUS = "stock_control_status"; // 库存状态
    String STOCK_CONTROL_WARN_STATUS = "stock_control_warn_status"; // 库存告警状态

    // ========== 兑换管理模块 ==========
    String EXCHANGE_CATEGORY_STATUS = "exchange_category_status"; // 兑换类目状态
    String EXCHANGE_CATEGORY_SCOPE = "exchange_category_scope"; // 兑换类目适用范围
    String EXCHANGE_ORDER_PAY_STATUS = "exchange_order_pay_status"; // 兑换订单支付状态

}
