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
}
