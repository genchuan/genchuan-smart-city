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

    // ========== 预警信息 TODO 100_007 ==========
    ErrorCode ALARM_INFORMATION_NOT_EXISTS = new ErrorCode(100_007, "预警信息不存在");

}
