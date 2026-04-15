package cn.iocoder.yudao.module.ordertrade.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * 订单交易模块 错误码枚举
 * 错误码区间：1-030-000-000 ~ 1-030-999-999
 *
 * @author genchuan
 */
public interface ErrorCodeConstants {

    // ========== 全部订单 ==========
    ErrorCode ALL_ORDER_NOT_EXISTS               = new ErrorCode(1_030_001_000, "全部订单不存在");
    ErrorCode ALL_ORDER_STATUS_CANNOT_PAY        = new ErrorCode(1_030_001_001, "订单状态不是待支付，无法支付");
    ErrorCode ALL_ORDER_STATUS_CANNOT_REFUND     = new ErrorCode(1_030_001_002, "订单状态不是已支付，无法发起退款");
    ErrorCode ALL_ORDER_STATUS_CANNOT_INVOICE    = new ErrorCode(1_030_001_003, "当前订单状态不支持开票");
    ErrorCode ALL_ORDER_STATUS_CANNOT_CANCEL     = new ErrorCode(1_030_001_004, "订单状态不是待支付，无法取消");

    // ========== 临时停车订单 ==========
    ErrorCode TEMP_PARK_ORDER_NOT_EXISTS         = new ErrorCode(1_030_002_000, "临时停车订单不存在");

    // ========== 错时停车订单 ==========
    ErrorCode OFFTIME_PARK_ORDER_NOT_EXISTS      = new ErrorCode(1_030_003_000, "错时停车订单不存在");

    // ========== 汽车充电订单 ==========
    ErrorCode CAR_CHARGE_ORDER_NOT_EXISTS        = new ErrorCode(1_030_004_000, "汽车充电订单不存在");
    ErrorCode CAR_CHARGE_ORDER_NOT_CHARGING      = new ErrorCode(1_030_004_001, "汽车充电订单不在充电中，无法停止");

    // ========== 两轮充电订单 ==========
    ErrorCode BIKE_CHARGE_ORDER_NOT_EXISTS       = new ErrorCode(1_030_005_000, "两轮充电订单不存在");
    ErrorCode BIKE_CHARGE_ORDER_NOT_CHARGING     = new ErrorCode(1_030_005_001, "两轮充电订单不在充电中，无法停止");

    // ========== 共享充电订单 ==========
    ErrorCode SHARE_CHARGE_ORDER_NOT_EXISTS      = new ErrorCode(1_030_006_000, "共享充电订单不存在");
    ErrorCode SHARE_CHARGE_ORDER_NOT_BORROWED    = new ErrorCode(1_030_006_001, "共享充电订单不在借出中，无法归还");

    // ========== 异常订单 ==========
    ErrorCode ABNORMAL_ORDER_NOT_EXISTS          = new ErrorCode(1_030_007_000, "异常订单不存在");

    // ========== 逃费识别 ==========
    ErrorCode DEBT_IDENTIFY_NOT_EXISTS           = new ErrorCode(1_030_008_000, "逃费识别记录不存在");

    // ========== 逃费记录 ==========
    ErrorCode DEBT_RECORD_NOT_EXISTS             = new ErrorCode(1_030_009_000, "逃费记录不存在");

    // ========== 欠费记录 ==========
    ErrorCode ARREAR_RECORD_NOT_EXISTS           = new ErrorCode(1_030_010_000, "欠费记录不存在");

    // ========== 追缴跟踪 ==========
    ErrorCode COLLECT_TRACK_NOT_EXISTS           = new ErrorCode(1_030_011_000, "追缴跟踪记录不存在");

    // ========== 追缴配置 ==========
    ErrorCode COLLECT_CONFIG_NOT_EXISTS          = new ErrorCode(1_030_012_000, "追缴配置不存在");

    // ========== 退款申请 ==========
    ErrorCode REFUND_APPLY_NOT_EXISTS            = new ErrorCode(1_030_013_000, "退款申请不存在");
    ErrorCode REFUND_APPLY_STATUS_CANNOT_APPROVE = new ErrorCode(1_030_013_001, "退款申请不是待审核状态，无法审核");
    ErrorCode REFUND_APPLY_STATUS_CANNOT_EXEC    = new ErrorCode(1_030_013_002, "退款申请不是待执行状态，无法执行退款");

    // ========== 退款记录 ==========
    ErrorCode REFUND_RECORD_NOT_EXISTS           = new ErrorCode(1_030_014_000, "退款记录不存在");

    // ========== 金额核算 ==========
    ErrorCode AMOUNT_CHECK_NOT_EXISTS            = new ErrorCode(1_030_015_000, "金额核算记录不存在");

}
