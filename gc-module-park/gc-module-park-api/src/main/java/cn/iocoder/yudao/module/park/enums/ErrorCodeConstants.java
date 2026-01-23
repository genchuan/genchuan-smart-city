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


}
