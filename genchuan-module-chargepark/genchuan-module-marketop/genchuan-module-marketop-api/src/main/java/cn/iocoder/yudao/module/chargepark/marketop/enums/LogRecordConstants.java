package cn.iocoder.yudao.module.chargepark.marketop.enums;

/**
 * 学生管理 操作日志枚举
 * 目的：统一管理，也减少 Service 里各种“复杂”字符串
 *
 * @author HUIHUI
 */
public interface LogRecordConstants {

    // ======================= CARD_CONFIG 卡种配置 =======================

    String CARD_CONFIG_TYPE = "卡种配置";
    String CARD_CONFIG_CREATE_SUB_TYPE = "创建卡种配置";
    String CARD_CONFIG_CREATE_SUCCESS = "创建了卡种配置【{{#cardConfig.name}}】";
    String CARD_CONFIG_UPDATE_SUB_TYPE = "更新卡种配置";
    String CARD_CONFIG_UPDATE_SUCCESS = "更新了卡种配置【{{#cardConfig.name}}】】: {_DIFF{#updateReqVO}}";
    String CARD_CONFIG_ENABLE_SUB_TYPE = "生效卡种配置";
    String CARD_CONFIG_ENABLE_SUCCESS = "生效了卡种配置【{{#cardConfigName}}】";
    String CARD_CONFIG_DISABLE_SUB_TYPE = "停用卡种配置";
    String CARD_CONFIG_DISABLE_SUCCESS = "停用了卡种配置【{{#cardConfigName}}】";

    // ======================= CARD_ORDER 卡种订单 =======================

    String CARD_ORDER_TYPE = "卡种订单";
    String CARD_ORDER_PAY_SUB_TYPE = "支付卡种订单";
    String CARD_ORDER_PAY_SUCCESS = "支付了卡种订单【{{#cardOrder.no}}】";
    String CARD_ORDER_ACTIVATE_SUB_TYPE = "激活卡种订单";
    String CARD_ORDER_ACTIVATE_SUCCESS = "激活了卡种订单【{{#cardOrder.no}}】";
    String CARD_ORDER_INVOICE_SUB_TYPE = "开票卡种订单";
    String CARD_ORDER_INVOICE_SUCCESS = "开票了卡种订单【{{#cardOrder.no}}】";
    String CARD_ORDER_CANCEL_SUB_TYPE = "取消卡种订单";
    String CARD_ORDER_CANCEL_SUCCESS = "取消了卡种订单【{{#cardOrder.no}}】";

    // ======================= STOCK_CONTROL 库存管理 =======================

    String STOCK_CONTROL_TYPE = "库存管理";
    String STOCK_CONTROL_RESTOCK_SUB_TYPE = "库存补货";
    String STOCK_CONTROL_RESTOCK_SUCCESS = "补货了库存【{{#stockControl.id}}】，补货数量【{{#num}}】";
    String STOCK_CONTROL_WARN_SUB_TYPE = "库存预警";
    String STOCK_CONTROL_WARN_SUCCESS = "设置了库存预警【{{#stockControl.id}}】";
    String STOCK_CONTROL_ALLOCATE_SUB_TYPE = "库存调配";
    String STOCK_CONTROL_ALLOCATE_SUCCESS = "调配了库存【{{#stockControl.id}}】";

    // ======================= ACTIVITY_CONFIG 活动配置 =======================

    String ACTIVITY_CONFIG_TYPE = "活动配置";
    String ACTIVITY_CONFIG_CREATE_SUB_TYPE = "创建活动配置";
    String ACTIVITY_CONFIG_CREATE_SUCCESS = "创建了活动配置【{{#activityConfig.name}}】";
    String ACTIVITY_CONFIG_UPDATE_SUB_TYPE = "更新活动配置";
    String ACTIVITY_CONFIG_UPDATE_SUCCESS = "更新了活动配置【{{#activityConfig.name}}】";
    String ACTIVITY_CONFIG_ENABLE_SUB_TYPE = "生效活动配置";
    String ACTIVITY_CONFIG_ENABLE_SUCCESS = "生效了活动配置【{{#activityConfigName}}】";
    String ACTIVITY_CONFIG_DISABLE_SUB_TYPE = "停用活动配置";
    String ACTIVITY_CONFIG_DISABLE_SUCCESS = "停用了活动配置【{{#activityConfigName}}】";

    // ======================= COUPON_MGMT 优惠券管理 =======================

    String COUPON_MGMT_TYPE = "优惠券管理";
    String COUPON_MGMT_CREATE_SUB_TYPE = "创建优惠券";
    String COUPON_MGMT_CREATE_SUCCESS = "创建了优惠券【{{#couponMgmt.name}}】";
    String COUPON_MGMT_UPDATE_SUB_TYPE = "更新优惠券";
    String COUPON_MGMT_UPDATE_SUCCESS = "更新了优惠券【{{#couponMgmt.name}}】";
    String COUPON_MGMT_SEND_SUB_TYPE = "发放优惠券";
    String COUPON_MGMT_SEND_SUCCESS = "发放了优惠券【{{#couponMgmtName}}】";
    String COUPON_MGMT_VERIFY_SUB_TYPE = "核销优惠券";
    String COUPON_MGMT_VERIFY_SUCCESS = "核销了优惠券【{{#couponMgmtName}}】";
    String COUPON_MGMT_RESEND_SUB_TYPE = "重新发放优惠券";
    String COUPON_MGMT_RESEND_SUCCESS = "重新发放了优惠券【{{#couponMgmtName}}】";

    // ======================= PACKAGE_CONFIG 券包配置 =======================

    String PACKAGE_CONFIG_TYPE = "券包配置";
    String PACKAGE_CONFIG_CREATE_SUB_TYPE = "创建券包配置";
    String PACKAGE_CONFIG_CREATE_SUCCESS = "创建了券包配置【{{#packageConfig.name}}】";
    String PACKAGE_CONFIG_UPDATE_SUB_TYPE = "更新券包配置";
    String PACKAGE_CONFIG_UPDATE_SUCCESS = "更新了券包配置【{{#packageConfig.name}}】";
    String PACKAGE_CONFIG_ENABLE_SUB_TYPE = "生效券包配置";
    String PACKAGE_CONFIG_ENABLE_SUCCESS = "生效了券包配置【{{#packageConfigName}}】";
    String PACKAGE_CONFIG_DISABLE_SUB_TYPE = "停用券包配置";
    String PACKAGE_CONFIG_DISABLE_SUCCESS = "停用了券包配置【{{#packageConfigName}}】";

    // ======================= RECEIVE_RECORD 领用记录 =======================

    String RECEIVE_RECORD_TYPE = "领用记录";
    String RECEIVE_RECORD_CHECK_SUB_TYPE = "核查领用记录";
    String RECEIVE_RECORD_CHECK_SUCCESS = "核查了领用记录【{{#receiveRecord.no}}】";

    // ======================= EXCHANGE_CATEGORY 兑换类目 =======================

    String EXCHANGE_CATEGORY_TYPE = "兑换类目";
    String EXCHANGE_CATEGORY_CREATE_SUB_TYPE = "创建兑换类目";
    String EXCHANGE_CATEGORY_CREATE_SUCCESS = "创建了兑换类目【{{#exchangeCategory.name}}】";
    String EXCHANGE_CATEGORY_UPDATE_SUB_TYPE = "更新兑换类目";
    String EXCHANGE_CATEGORY_UPDATE_SUCCESS = "更新了兑换类目【{{#exchangeCategory.name}}】";
    String EXCHANGE_CATEGORY_ENABLE_SUB_TYPE = "生效兑换类目";
    String EXCHANGE_CATEGORY_ENABLE_SUCCESS = "生效了兑换类目【{{#exchangeCategoryName}}】";
    String EXCHANGE_CATEGORY_DISABLE_SUB_TYPE = "停用兑换类目";
    String EXCHANGE_CATEGORY_DISABLE_SUCCESS = "停用了兑换类目【{{#exchangeCategoryName}}】";

    // ======================= EXCHANGE_ORDER 兑换订单 =======================

    String EXCHANGE_ORDER_TYPE = "兑换订单";
    String EXCHANGE_ORDER_PAY_SUB_TYPE = "支付兑换订单";
    String EXCHANGE_ORDER_PAY_SUCCESS = "支付了兑换订单【{{#exchangeOrder.no}}】";
    String EXCHANGE_ORDER_DELIVER_SUB_TYPE = "发货兑换订单";
    String EXCHANGE_ORDER_DELIVER_SUCCESS = "发货了兑换订单【{{#exchangeOrder.no}}】";
    String EXCHANGE_ORDER_CANCEL_SUB_TYPE = "取消兑换订单";
    String EXCHANGE_ORDER_CANCEL_SUCCESS = "取消了兑换订单【{{#exchangeOrder.no}}】";

    // ======================= POINT_ACTIVITY 积分活动 =======================

    String POINT_ACTIVITY_TYPE = "积分活动";
    String POINT_ACTIVITY_CREATE_SUB_TYPE = "创建积分活动";
    String POINT_ACTIVITY_CREATE_SUCCESS = "创建了积分活动【{{#pointActivity.name}}】";
    String POINT_ACTIVITY_UPDATE_SUB_TYPE = "更新积分活动";
    String POINT_ACTIVITY_UPDATE_SUCCESS = "更新了积分活动【{{#pointActivity.name}}】";
    String POINT_ACTIVITY_ACTIVATE_SUB_TYPE = "生效积分活动";
    String POINT_ACTIVITY_ACTIVATE_SUCCESS = "生效了积分活动【{{#pointActivityName}}】";
    String POINT_ACTIVITY_ENABLE_SUB_TYPE = "启用积分活动";
    String POINT_ACTIVITY_ENABLE_SUCCESS = "启用了积分活动【{{#pointActivityName}}】";
    String POINT_ACTIVITY_PAUSE_SUB_TYPE = "暂停积分活动";
    String POINT_ACTIVITY_PAUSE_SUCCESS = "暂停了积分活动【{{#pointActivityName}}】";

    // ======================= POINT_LOTTERY 积分抽奖 =======================

    String POINT_LOTTERY_TYPE = "积分抽奖";
    String POINT_LOTTERY_CHECK_SUB_TYPE = "核查积分抽奖";
    String POINT_LOTTERY_CHECK_SUCCESS = "核查了积分抽奖记录【{{#lottery.no}}】";

    // ======================= PRIZE_MGMT 奖品管理 =======================

    String PRIZE_MGMT_TYPE = "奖品管理";
    String PRIZE_MGMT_CREATE_SUB_TYPE = "创建奖品";
    String PRIZE_MGMT_CREATE_SUCCESS = "创建了奖品【{{#prizeMgmt.name}}】";
    String PRIZE_MGMT_UPDATE_SUB_TYPE = "更新奖品";
    String PRIZE_MGMT_UPDATE_SUCCESS = "更新了奖品【{{#prizeMgmt.name}}】";
    String PRIZE_MGMT_ENABLE_SUB_TYPE = "启用奖品";
    String PRIZE_MGMT_ENABLE_SUCCESS = "启用了奖品【{{#prizeMgmtName}}】";
    String PRIZE_MGMT_DISABLE_SUB_TYPE = "禁用奖品";
    String PRIZE_MGMT_DISABLE_SUCCESS = "禁用了奖品【{{#prizeMgmtName}}】";

    // ======================= RULE_CONFIG 规则配置 =======================

    String RULE_CONFIG_TYPE = "规则配置";
    String RULE_CONFIG_CREATE_SUB_TYPE = "创建规则配置";
    String RULE_CONFIG_CREATE_SUCCESS = "创建了规则配置【{{#ruleConfig.name}}】";
    String RULE_CONFIG_UPDATE_SUB_TYPE = "更新规则配置";
    String RULE_CONFIG_UPDATE_SUCCESS = "更新了规则配置【{{#ruleConfig.name}}】";
    String RULE_CONFIG_ENABLE_SUB_TYPE = "生效规则配置";
    String RULE_CONFIG_ENABLE_SUCCESS = "生效了规则配置【{{#ruleConfigName}}】";
    String RULE_CONFIG_DISABLE_SUB_TYPE = "停用规则配置";
    String RULE_CONFIG_DISABLE_SUCCESS = "停用了规则配置【{{#ruleConfigName}}】";

    // ======================= CYCLE_REPORT 周期报表 =======================

    String CYCLE_REPORT_TYPE = "周期报表";
    String CYCLE_REPORT_CREATE_SUB_TYPE = "生成周期报表";
    String CYCLE_REPORT_CREATE_SUCCESS = "生成了周期报表【{{#cycleReport.reportCycle}}】";

}
