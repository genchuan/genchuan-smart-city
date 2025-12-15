package cn.iocoder.yudao.module.industry.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * System 错误码枚举类
 * system 系统，使用 1-002-000-000 段
 */
public interface ErrorCodeConstants {
    //管理部件找不到
    ErrorCode MNG_COMP_NOT_EXISTS = new ErrorCode(100_001, "找不到管理部件");

    //=============一、通用大屏模版=================================================

    // ========== 通用场景表，一级和二级场景  ==========
    ErrorCode UNIVERSAL_SCENE_NOT_EXISTS = new ErrorCode(500_001, "通用场景表，一级和二级场景不存在");

    // ========== 场景字段   ==========
    ErrorCode SCENE_FIELD_NOT_EXISTS = new ErrorCode(500_002, "场景字段不存在");

    // ========== 场景字段状态映射  ==========
    ErrorCode SELECTED_FIELD_STATUS_MAP_NOT_EXISTS = new ErrorCode(500_003, "场景字段状态映射不存在");
    // ========== 场景状态字段图标配置  ==========
    ErrorCode SCENE_STATUS_ICON_NOT_EXISTS = new ErrorCode(500_004, "场景状态字段图标配置不存在");
    // ========== 场景地图整体配置  ==========
    ErrorCode SCENE_MAP_CONFIG_NOT_EXISTS = new ErrorCode(500_005, "场景地图整体配置不存在");
}
