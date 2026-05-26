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
    ErrorCode COLLECT_CONFIG_NO_DUPLICATE        = new ErrorCode(1_030_012_001, "配置编号已存在，请使用其他编号");

    // ========== 退款申请 ==========
    ErrorCode REFUND_APPLY_NOT_EXISTS            = new ErrorCode(1_030_013_000, "退款申请不存在");
    ErrorCode REFUND_APPLY_STATUS_CANNOT_APPROVE = new ErrorCode(1_030_013_001, "退款申请不是待审核状态，无法审核");
    ErrorCode REFUND_APPLY_STATUS_CANNOT_EXEC    = new ErrorCode(1_030_013_002, "退款申请不是待执行状态，无法执行退款");

    // ========== 退款记录 ==========
    ErrorCode REFUND_RECORD_NOT_EXISTS           = new ErrorCode(1_030_014_000, "退款记录不存在");

    // ========== 金额核算 ==========
    ErrorCode AMOUNT_CHECK_NOT_EXISTS            = new ErrorCode(1_030_015_000, "金额核算记录不存在");

    // ========== 开票审核 ==========
    ErrorCode INVOICE_AUDIT_NOT_EXISTS           = new ErrorCode(1_030_016_000, "开票审核记录不存在");
    ErrorCode INVOICE_AUDIT_STATUS_CANNOT_APPROVE = new ErrorCode(1_030_016_001, "开票审核不是待审核状态，无法审核通过");
    ErrorCode INVOICE_AUDIT_STATUS_CANNOT_REJECT  = new ErrorCode(1_030_016_002, "开票审核不是待审核状态，无法驳回");

    // ========== 发票列表 ==========
    ErrorCode INVOICE_LIST_NOT_EXISTS            = new ErrorCode(1_030_017_000, "发票记录不存在");
    ErrorCode INVOICE_LIST_ALREADY_APPLIED       = new ErrorCode(1_030_017_004, "该订单已申请开票，请勿重复提交");
    ErrorCode INVOICE_LIST_STATUS_CANNOT_APPROVE = new ErrorCode(1_030_017_001, "发票不是待审核状态，无法审核通过");
    ErrorCode INVOICE_LIST_STATUS_CANNOT_REJECT  = new ErrorCode(1_030_017_002, "发票不是待审核状态，无法驳回");
    ErrorCode INVOICE_LIST_STATUS_CANNOT_INVOICE = new ErrorCode(1_030_017_003, "发票不是待开票状态，无法开票");
    ErrorCode INVOICE_LIST_STATUS_NOT_INVOICED   = new ErrorCode(1_030_017_005, "发票不是已开票状态，无法下载");
    ErrorCode INVOICE_LIST_DOWNLOAD_URL_NOT_EXISTS = new ErrorCode(1_030_017_006, "发票下载链接不存在");

    // ========== 发票配置 ==========
    ErrorCode INVOICE_CONFIG_NOT_EXISTS          = new ErrorCode(1_030_018_000, "发票配置不存在");
    ErrorCode INVOICE_CONFIG_STATUS_CANNOT_ENABLE  = new ErrorCode(1_030_018_001, "发票配置已生效，无法重复生效");
    ErrorCode INVOICE_CONFIG_STATUS_CANNOT_DISABLE = new ErrorCode(1_030_018_002, "发票配置已禁用，无法重复禁用");

    // ========== 支付应用 ==========
    ErrorCode PAY_APP_NOT_EXISTS                 = new ErrorCode(1_030_019_000, "支付应用不存在");
    ErrorCode PAY_APP_STATUS_CANNOT_ENABLE       = new ErrorCode(1_030_019_001, "支付应用已生效，无法重复生效");
    ErrorCode PAY_APP_STATUS_CANNOT_DISABLE      = new ErrorCode(1_030_019_002, "支付应用已禁用，无法重复禁用");

    // ========== 支付订单 ==========
    ErrorCode PAY_ORDER_NOT_EXISTS               = new ErrorCode(1_030_020_000, "支付订单不存在");
    ErrorCode PAY_ORDER_STATUS_CANNOT_PAY        = new ErrorCode(1_030_020_001, "支付订单不是待支付状态，无法支付");
    ErrorCode PAY_ORDER_STATUS_CANNOT_REFUND     = new ErrorCode(1_030_020_002, "支付订单不是已支付状态，无法退款");
    ErrorCode PAY_ORDER_STATUS_CANNOT_CANCEL     = new ErrorCode(1_030_020_003, "支付订单不是待支付状态，无法取消");

    // ========== 退款单 ==========
    ErrorCode PAY_REFUND_NOT_EXISTS              = new ErrorCode(1_030_021_000, "退款单不存在");
    ErrorCode PAY_REFUND_STATUS_CANNOT_EXECUTE   = new ErrorCode(1_030_021_001, "退款单不是待执行状态，无法执行");
    ErrorCode PAY_REFUND_STATUS_CANNOT_CANCEL    = new ErrorCode(1_030_021_002, "退款单不是待执行状态，无法取消");

    // ========== 转账单 ==========
    ErrorCode PAY_TRANSFER_NOT_EXISTS            = new ErrorCode(1_030_022_000, "转账单不存在");
    ErrorCode PAY_TRANSFER_STATUS_CANNOT_EXECUTE = new ErrorCode(1_030_022_001, "转账单不是待执行状态，无法执行");
    ErrorCode PAY_TRANSFER_STATUS_CANNOT_CANCEL  = new ErrorCode(1_030_022_002, "转账单不是待执行状态，无法取消");

    // ========== 电子钱包 ==========
    ErrorCode PAY_WALLET_NOT_EXISTS              = new ErrorCode(1_030_023_000, "电子钱包不存在");
    ErrorCode PAY_WALLET_STATUS_CANNOT_RECHARGE  = new ErrorCode(1_030_023_001, "电子钱包已冻结，无法充值");
    ErrorCode PAY_WALLET_STATUS_CANNOT_WITHDRAW  = new ErrorCode(1_030_023_002, "电子钱包已冻结，无法提现");

    // ========== 支付回调 ==========
    ErrorCode PAY_CALLBACK_NOT_EXISTS            = new ErrorCode(1_030_024_000, "支付回调记录不存在");

    // ========== 分账账单 ==========
    ErrorCode SETTLE_BILL_NOT_EXISTS             = new ErrorCode(1_030_025_000, "分账账单不存在");
    ErrorCode SETTLE_BILL_STATUS_CANNOT_APPROVE  = new ErrorCode(1_030_025_001, "分账账单不是待审核状态，无法审核通过");
    ErrorCode SETTLE_BILL_STATUS_CANNOT_REJECT   = new ErrorCode(1_030_025_002, "分账账单不是待审核状态，无法驳回");
    ErrorCode SETTLE_BILL_STATUS_CANNOT_SETTLE   = new ErrorCode(1_030_025_003, "分账账单不是待结算状态，无法结算");

    // ========== 分账比例 ==========
    ErrorCode SPLIT_RATE_NOT_EXISTS              = new ErrorCode(1_030_026_000, "分账比例配置不存在");
    ErrorCode SPLIT_RATE_STATUS_CANNOT_ENABLE    = new ErrorCode(1_030_026_001, "分账比例配置已生效，无法重复生效");
    ErrorCode SPLIT_RATE_STATUS_CANNOT_DISABLE   = new ErrorCode(1_030_026_002, "分账比例配置已生效状态才能禁用");
    ErrorCode SPLIT_RATE_PARTNER_ID_DUPLICATE    = new ErrorCode(1_030_026_003, "该合作方已存在分账比例配置");

    // ========== 结算状态 ==========
    ErrorCode SETTLE_STATUS_NOT_EXISTS           = new ErrorCode(1_030_027_000, "结算状态记录不存在");

    // ========== 商户对账单 ==========
    ErrorCode RECONCILE_BILL_NOT_EXISTS          = new ErrorCode(1_030_028_000, "商户对账单不存在");
    ErrorCode RECONCILE_BILL_STATUS_CANNOT_CONFIRM = new ErrorCode(1_030_028_001, "对账单不是待确认状态，无法确认");
    ErrorCode RECONCILE_BILL_STATUS_CANNOT_DISPUTE = new ErrorCode(1_030_028_002, "对账单不是待确认状态，无法提出异议");

    // ========== 对账记录 ==========
    ErrorCode RECONCILE_RECORD_NOT_EXISTS        = new ErrorCode(1_030_029_000, "对账记录不存在");

    // ========== 代付规则 ==========
    ErrorCode AGENT_RULE_NOT_EXISTS              = new ErrorCode(1_030_030_000, "代付规则不存在");
    ErrorCode AGENT_RULE_STATUS_CANNOT_ENABLE    = new ErrorCode(1_030_030_001, "代付规则当前状态不可生效");
    ErrorCode AGENT_RULE_STATUS_CANNOT_DISABLE   = new ErrorCode(1_030_030_002, "代付规则未处于已生效状态，无法禁用");
    ErrorCode AGENT_RULE_MERCHANT_TYPE_DUPLICATE = new ErrorCode(1_030_030_003, "该商户同类型的代付规则已存在");

    // ========== 代付码 ==========
    ErrorCode AGENT_CODE_NOT_EXISTS              = new ErrorCode(1_030_031_000, "代付码不存在");
    ErrorCode AGENT_CODE_STATUS_CANNOT_REFRESH   = new ErrorCode(1_030_031_001, "代付码不是未使用状态，无法刷新");
    ErrorCode AGENT_CODE_STATUS_CANNOT_REGEN     = new ErrorCode(1_030_031_002, "代付码不是已过期状态，无法重新生成");

    // ========== 代付订单 ==========
    ErrorCode AGENT_ORDER_NOT_EXISTS             = new ErrorCode(1_030_032_000, "代付订单不存在");
    ErrorCode AGENT_ORDER_STATUS_CANNOT_PAY      = new ErrorCode(1_030_032_001, "代付订单不是待支付状态，无法支付");
    ErrorCode AGENT_ORDER_STATUS_CANNOT_INVOICE  = new ErrorCode(1_030_032_002, "代付订单不是已支付或已完成状态，无法开票");
    ErrorCode AGENT_ORDER_STATUS_CANNOT_CANCEL   = new ErrorCode(1_030_032_003, "代付订单不是待支付状态，无法取消");

    // ========== 代付记录 ==========
    ErrorCode AGENT_RECORD_NOT_EXISTS            = new ErrorCode(1_030_033_000, "代付记录不存在");
    ErrorCode AGENT_RECORD_STATUS_CANNOT_CHECK   = new ErrorCode(1_030_033_001, "代付记录不是异常状态，无需核查");

    // ========== 周期报表 ==========
    ErrorCode CYCLE_REPORT_NOT_EXISTS            = new ErrorCode(1_030_034_000, "周期报表记录不存在");

}
