package cn.iocoder.yudao.module.datacenter.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * System 错误码枚举类
 *
 * system 系统，使用 1-002-000-000 段
 */
public interface ErrorCodeConstants {
    // ========== 场景分类 100_00* ==========
    ErrorCode SCENE_CONFIG_NOT_EXISTS = new ErrorCode(100_001, "场景分类不存在");
    ErrorCode SCENE_CONFIG_EXITS_CHILDREN = new ErrorCode(100_002, "存在存在子场景分类，无法删除");
    ErrorCode SCENE_CONFIG_PARENT_NOT_EXITS = new ErrorCode(100_003,"父级场景分类不存在");
    ErrorCode SCENE_CONFIG_PARENT_ERROR = new ErrorCode(100_004, "不能设置自己为父场景分类");
    ErrorCode SCENE_CONFIG_NAME_DUPLICATE = new ErrorCode(100_005, "已经存在该场景名称的场景分类");
    ErrorCode SCENE_CONFIG_PARENT_IS_CHILD = new ErrorCode(100_006, "不能设置自己的子SceneConfig为父SceneConfig");
    ErrorCode ALARM_LIST_NOT_EXISTS = new ErrorCode(100_106, "预警告警列不存在");

    // ========== 巡查人员信息 108_001 ==========
    ErrorCode INSPECTION_STAFF_NOT_EXISTS = new ErrorCode(108_001, "巡查人员信息不存在");
    // ========== 人员区域分配 108_002 ==========
    ErrorCode STAFF_AREA_ASSIGNMENT_NOT_EXISTS = new ErrorCode(108_002, "人员区域分配不存在");
    // ========== 人员作业状态 108_003 ==========
    ErrorCode STAFF_WORK_STATUS_NOT_EXISTS = new ErrorCode(108_003, "人员作业状态不存在");
    // ========== 人员异常报警 108_004 ==========
    ErrorCode STAFF_ALERT_NOT_EXISTS = new ErrorCode(108_004, "人员异常报警不存在");
    // ========== 巡查路线 108_005 ==========
    ErrorCode PATROL_ROUTE_NOT_EXISTS = new ErrorCode(108_005, "巡查路线不存在");
    // ========== 路线版本 108_006 ==========
    ErrorCode ROUTE_VERSION_NOT_EXISTS = new ErrorCode(108_006, "路线版本不存在");

    ErrorCode DEVICE_NOT_EXISTS = new ErrorCode(108_007, "Thingsboard设备不存在");
}
