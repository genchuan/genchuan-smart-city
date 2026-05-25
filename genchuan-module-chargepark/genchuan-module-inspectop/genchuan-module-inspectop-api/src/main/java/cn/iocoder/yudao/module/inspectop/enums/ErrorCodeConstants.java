package cn.iocoder.yudao.module.inspectop.enums;


import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * inspectop 错误码枚举类
 * inspectop 系统，使用 500 错误码
 */
public interface ErrorCodeConstants {

    // ========== 车位状态监测==========
    ErrorCode SPACE_MONITOR_NOT_EXISTS = new ErrorCode(500, "车位状态监测不存在");

    // ========== 油车占位监测 ==========
    ErrorCode OIL_MONITOR_NOT_EXISTS = new ErrorCode(500, "油车占位监测不存在");

    // ========== 汽车充电监测 ==========
    ErrorCode CAR_CHARGE_MONITOR_NOT_EXISTS = new ErrorCode(500, "汽车充电监测不存在");

    // ========== 两轮充电监测 ==========
    ErrorCode BIKE_CHARGE_MONITOR_NOT_EXISTS = new ErrorCode(500, "两轮充电监测不存在");

    // ========== 共享充电监测 ==========
    ErrorCode SHARE_CHARGE_MONITOR_NOT_EXISTS = new ErrorCode(500, "共享充电监测不存在");

    // ========== 巡检人员 ==========
    ErrorCode INSPECT_USER_NOT_EXISTS = new ErrorCode(500, "巡检人员不存在");
    ErrorCode INSPECT_USER_EXISTS = new ErrorCode(1001, "巡检人员已存在");
    ErrorCode INSPECT_USER_IMPORT_DATA_EMPTY = new ErrorCode(1002, "导入数据为空");
    ErrorCode INSPECT_USER_NAME_NOT_NULL = new ErrorCode(1003, "姓名为空");
    ErrorCode INSPECT_USER_PHONE_NOT_NULL = new ErrorCode(1004, "手机号为空");
    ErrorCode INSPECT_USER_STATUS_NOT_NULL = new ErrorCode(1005, "状态为空");

    // ========== 巡检计划 ==========
    ErrorCode INSPECT_PLAN_NOT_EXISTS = new ErrorCode(500, "巡检计划不存在");
    ErrorCode INSPECT_PLAN_IMPORT_DATA_EMPTY = new ErrorCode(500, "导入数据不能为空");
    ErrorCode INSPECT_PLAN_NAME_NOT_NULL = new ErrorCode(500, "计划名称不能为空");
    ErrorCode INSPECT_PLAN_TYPE_NOT_NULL = new ErrorCode(500, "巡检类型不能为空");
    ErrorCode INSPECT_PLAN_SCOPE_NOT_NULL = new ErrorCode(500, "巡检范围不能为空");
    ErrorCode INSPECT_PLAN_STATUS_NOT_NULL = new ErrorCode(500, "计划状态不能为空");
    ErrorCode INSPECT_PLAN_EXISTS = new ErrorCode(500, "巡检计划【{}】已存在");

    // ========== 巡检任务 ==========
    ErrorCode INSPECT_TASK_NOT_EXISTS = new ErrorCode(500, "巡检任务不存在");

    // ========== 巡检上报 ==========
    ErrorCode INSPECT_REPORT_NOT_EXISTS = new ErrorCode(500, "巡检上报不存在");

    // ========== 巡检轨迹 ==========
    ErrorCode INSPECT_TRACK_NOT_EXISTS= new ErrorCode(500, "巡检轨迹不存在");

    // ========== 电子围栏 ==========
    ErrorCode FENCE_MGMT_NOT_EXISTS = new ErrorCode(500, "电子围栏不存在");

    // ========== 资产信息 ==========
    ErrorCode ASSET_INFO_NOT_EXISTS = new ErrorCode(500, "资产信息不存在");
    ErrorCode ASSET_INFO_IMPORT_DATA_EMPTY = new ErrorCode(501, "导入数据不能为空");
    ErrorCode ASSET_INFO_NAME_NOT_NULL = new ErrorCode(502, "资产名称不能为空");
    ErrorCode ASSET_INFO_TYPE_NOT_NULL = new ErrorCode(503, "资产类型不能为空");
    ErrorCode ASSET_INFO_STATUS_NOT_NULL = new ErrorCode(504, "资产状态不能为空");
    ErrorCode ASSET_INFO_STATION_ID_NOT_NULL = new ErrorCode(505, "所属场站ID不能为空");
    ErrorCode ASSET_INFO_EXISTS = new ErrorCode(506, "资产信息已存在");

    // ========== 库存管理 ==========
    ErrorCode ASSET_STOCK_NOT_EXISTS = new ErrorCode(500, "库存管理不存在");
    // ========== 资产盘点 ==========
    ErrorCode ASSET_CHECK_NOT_EXISTS = new ErrorCode(500, "资产盘点不存在");

    // ========== 备件仓储 ==========
    ErrorCode SPARE_STOCK_NOT_EXISTS = new ErrorCode(500, "备件仓储不存在");

    // ========== 排班查看 ==========
    ErrorCode SCHEDULE_VIEW_NOT_EXISTS = new ErrorCode(500, "排班查看不存在");

    // ========== 换班申请 ==========
    ErrorCode SHIFT_APPLY_NOT_EXISTS = new ErrorCode(500, "换班申请不存在");

    // ========== 交接日志 ==========
    ErrorCode HANDOVER_LOG_NOT_EXISTS = new ErrorCode(500, "交接日志不存在");


}
