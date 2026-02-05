package cn.iocoder.yudao.module.park.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * System 错误码枚举类
 * system 系统，使用 1-002-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== 收费异常==========
    ErrorCode CHARGE_ABNORMAL_NOT_EXISTS = new ErrorCode(500, "收费异常不存在");
    // ========== 逃费订单==========
    ErrorCode ORDER_ESCAPE_NOT_EXISTS = new ErrorCode(500, "逃费订单不存在");
    // ========== 期卡订单==========
    ErrorCode ORDER_PERIOD_NOT_EXISTS = new ErrorCode(500, "期卡订单不存在");
    // ========== 退款订单==========
    ErrorCode ORDER_REFUND_NOT_EXISTS = new ErrorCode(500, "退款订单不存在");
    // ========== 临停订单==========
    ErrorCode ORDER_TEMP_NOT_EXISTS = new ErrorCode(500, "临停订单不存在");
    // ========== 分账结算==========
    ErrorCode SETTLEMENT_NOT_EXISTS = new ErrorCode(500, "分账结算不存在");

    // ========== 黑白名单==========
    ErrorCode BLACK_WHITE_LIST_NOT_EXISTS = new ErrorCode(500, "黑白名单不存在");
    // ========== 商户==========
    ErrorCode MERCHANT_NOT_EXISTS = new ErrorCode(500, "商户不存在");
    // ========== 商户权限==========
    ErrorCode MERCHANT_PERMISSION_NOT_EXISTS = new ErrorCode(500, "商户权限不存在");
    // ========== 代付规则==========
    ErrorCode PAYMENT_PROXY_NOT_EXISTS = new ErrorCode(500, "代付规则不存在");
    // ========== 系统用户==========
    ErrorCode USER_NOT_EXISTS = new ErrorCode(500, "系统用户不存在");
    // ========== 访客==========
    ErrorCode VISITOR_NOT_EXISTS = new ErrorCode(500, "访客不存在");

    // ========== 认证记录==========
    ErrorCode CERTIFICATION_NOT_EXISTS = new ErrorCode(500, "认证记录不存在");
    // ========== 企业信息==========
    ErrorCode ENTERPRISE_INFORMATION_NOT_EXISTS = new ErrorCode(500, "企业信息不存在");
    // ========== 政府部门==========
    ErrorCode GOVERNMENT_DEPARTMENT_NOT_EXISTS = new ErrorCode(500, "政府部门不存在");
    // ========== 运维排班==========
    ErrorCode MAINTAIN_SCHEDULE_NOT_EXISTS = new ErrorCode(500, "运维排班不存在");
    // ========== 运维人员==========
    ErrorCode MAINTAIN_USER_NOT_EXISTS = new ErrorCode(500, "运维人员不存在");
    // ========== 真实设备监控------车牌识别事件 ==========
    ErrorCode RECOGNITION_EVENTS_NOT_EXISTS = new ErrorCode(500, "车牌识别事件不存在");

    // ========== 地址==========
    ErrorCode ADDRESS_NOT_EXISTS = new ErrorCode(500, "地址不存在");
    // ========== 参与单位==========
    ErrorCode PARTICIPATING_UNIT_NOT_EXISTS = new ErrorCode(500, "参与单位不存在");
    // ========== 接收方==========
    ErrorCode RECEIVER_TABLE_NOT_EXISTS = new ErrorCode(500, "接收方不存在");
    // ========== 供应商==========
    ErrorCode SUPPLIER_NOT_EXISTS = new ErrorCode(500, "供应商不存在");
    // ========== 优惠券==========
    ErrorCode COUPON_NOT_EXISTS = new ErrorCode(500, "优惠券不存在");
    // ========== 费率策略==========
    ErrorCode FEE_STRATEGY_NOT_EXISTS = new ErrorCode(500, "费率策略不存在");
    // ========== 临停收费规则==========
    ErrorCode FEE_TEMP_NOT_EXISTS = new ErrorCode(500, "临停收费规则不存在");
    // ========== 期卡套餐==========
    ErrorCode PERIOD_PACKAGE_NOT_EXISTS = new ErrorCode(500, "期卡套餐不存在");
    // ========== 优惠活动==========
    ErrorCode PROMOTION_NOT_EXISTS = new ErrorCode(500, "优惠活动不存在");
    // ========== 充值套餐==========
    ErrorCode RECHARGE_PACKAGE_NOT_EXISTS = new ErrorCode(500, "充值套餐不存在");

    // ========== 行业应用类别 ==========
    ErrorCode APP_TYPE_NOT_EXISTS = new ErrorCode(500, "行业应用类别不存在");

    // ========== 行政区划配置表 ==========
    ErrorCode AREA_NOT_EXISTS = new ErrorCode(500, "行政区划配置表不存在");

    // ========== 资产-thingsboard ==========
    ErrorCode ASSET_NOT_EXISTS = new ErrorCode(500, "资产-thingsboard不存在");

    // ========== 资产扩展 ==========
    ErrorCode ASSET_EXTEND_NOT_EXISTS = new ErrorCode(500, "资产扩展不存在");

    // ========== 设备扩展 ==========
    ErrorCode DEVICE_EXTEND_NOT_EXISTS = new ErrorCode(500, "设备扩展不存在");

    // ========== 监测事件类别 ==========
    ErrorCode EVENT_TYPE_NOT_EXISTS = new ErrorCode(500, "监测事件类别不存在");

    // ========== 网格管理 ==========
    ErrorCode GRID_MANAGE_NOT_EXISTS = new ErrorCode(500, "网格管理不存在");

    // ========== 监测部件类别 ==========
    ErrorCode MONITOR_PART_TYPE_NOT_EXISTS = new ErrorCode(500, "监测部件类别不存在");

    // ========== 管理部件类别 ==========
    ErrorCode PART_TYPE_NOT_EXISTS = new ErrorCode(500, "管理部件类别不存在");

    // ========== 管理事项类别 ==========
    ErrorCode ITEM_TYPE_NOT_EXISTS = new ErrorCode(500, "管理事项类别不存在");

    // ========== 泊位录入车辆 ==========
    ErrorCode INPUT_CAR_NOT_EXISTS = new ErrorCode(500, "泊位录入车辆不存在");

    // ========== 路测泊位管理 ==========
    ErrorCode ROADSIDE_BERTH_MANAGE_NOT_EXISTS = new ErrorCode(500, "路测泊位管理不存在");

    // ========== 优惠券==========
//    ErrorCode COUPON_NOT_EXISTS = new ErrorCode(500, "优惠券不存在");
    // ========== 畅停卡==========
    ErrorCode SMOOTH_STOP_CARD_NOT_EXISTS = new ErrorCode(500, "畅停卡不存在");
}
