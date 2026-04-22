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





}
