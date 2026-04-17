package cn.iocoder.yudao.module.vehiclecharging.enums;


import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * vehiclecharging 错误码枚举类
 * vehiclecharging 系统，使用500错误码
 */
public interface ErrorCodeConstants {
// ========== 实时监测 TODO 补充编号 ==========
    ErrorCode STATUS_MONITOR_NOT_EXISTS = new ErrorCode(500, "实时监测不存在");

    // ========== 充电站==========
    ErrorCode CHARGING_STATION_NOT_EXISTS = new ErrorCode(500, "充电站不存在");
    ErrorCode CHARGING_STATION_CODE_EXISTS = new ErrorCode(500, "充电站编号已存在");
    // ========== 充电桩告警==========
    ErrorCode PILEALARM_NOT_EXISTS = new ErrorCode(500, "充电桩告警不存在");



    // ========== 充电车位==========
    ErrorCode CHARGING_LOT_NOT_EXISTS = new ErrorCode(500, "充电车位不存在");

    // ========== 互联互通表错误码=========
    ErrorCode INTERCONNECTION_NOT_EXISTS = new ErrorCode(500, "互联互通表不存在");
    ErrorCode INTERCONNECTION_STATUS_ERROR = new ErrorCode(500, "互联互通表状态错误");
    // ========== 费率设置==========
    ErrorCode RATE_SETTING_NOT_EXISTS = new ErrorCode(500, "费率设置不存在");
    // ========== 订单列表表错误码 ==========
    ErrorCode ORDER_LIST_NOT_EXISTS = new ErrorCode(500, "订单列表表不存在");
    // ========== 订单告警 ==========
    ErrorCode ORDER_ALARM_NOT_EXISTS = new ErrorCode(500, "订单告警不存在");
    // ========== 订单告警状态错误==========
    ErrorCode ORDER_ALARM_STATUS_ERROR = new ErrorCode(500, "订单告警状态错误");
    // ========== 订单不存在==========
    ErrorCode ORDER_NOT_EXISTS = new ErrorCode(500, "订单不存在");

    // ========== 订单退款==========
    ErrorCode ORDER_REFUND_NOT_EXISTS = new ErrorCode(500, "订单退款不存在");
    // ========== 分账比例 ==========
    ErrorCode SHARING_RATIO_NOT_EXISTS = new ErrorCode(500, "分账比例不存在");

    ErrorCode PILE_NOT_EXISTS = new ErrorCode(500, "充电桩不存在");
    ErrorCode PILE_CODE_DUPLICATE = new ErrorCode(500, "充电桩编号已存在");
    ErrorCode PILE_STATUS_NOT_DEBUGGING = new ErrorCode(500, "充电桩状态不是未调试，无法调试");
    ErrorCode PILE_STATUS_NOT_ENABLED = new ErrorCode(500, "充电桩状态不是已调试，无法启用");
    ErrorCode PILE_STATUS_NOT_ENABLED_FOR_DISABLE = new ErrorCode(500, "充电桩状态不是已启用，无法进行停用操作");
    ErrorCode PILE_QRCODE_NOT_EXISTS = new ErrorCode(500, "充电桩二维码内容不存在");
    ErrorCode     MODULE_ALARM_NOT_EXISTS = new ErrorCode(500, "模块告警记录不存在");


    ErrorCode MODULE_ALARM_STATUS_NOT_UNCHECKED = new ErrorCode(500, "模块告警状态不是未排查，无法执行排查操作");
    ErrorCode MODULE_ALARM_STATUS_NOT_CHECKED = new ErrorCode(500, "模块告警状态不是已排查，无法执行修复操作");
    ErrorCode MODULE_ALARM_STATUS_NOT_REPAIRING = new ErrorCode(500, "模块告警状态不是修复中，无法执行销账操作");
    ErrorCode MODULE_ALARM_REPAIR_VOUCHER_NOT_EXISTS = new ErrorCode(500, "模块告警记录不存在修复凭证");


    // ========== 结算单==========
    ErrorCode SETTLEMENT_BILL_NOT_EXISTS = new ErrorCode(500, "结算单不存在");
    // ========== 分账报表==========
    ErrorCode SHARING_REPORT_ID_REQUIRED = new ErrorCode(500, "缺失结算单ID");
    ErrorCode SHARING_REPORT_NOT_EXISTS = new ErrorCode(500, "结算单不存在");
    ErrorCode REPORT_NAME_REQUIRED = new ErrorCode(500, "缺失报表名称");
    ErrorCode REPORT_TYPE_REQUIRED = new ErrorCode(500, "缺失报表类型");
    ErrorCode ILLEGAL_TIME_FORMAT = new ErrorCode(500, "非法时间格式");
}
