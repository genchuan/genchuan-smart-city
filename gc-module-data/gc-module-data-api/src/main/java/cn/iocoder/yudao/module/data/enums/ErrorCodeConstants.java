package cn.iocoder.yudao.module.data.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * System 错误码枚举类
 * system 系统，使用 1-002-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== 地理编码 ==========
    ErrorCode GEO_CODE_NOT_EXISTS = new ErrorCode(500, "地理编码不存在");

    // ========== 管理部件分类 ==========
    ErrorCode CATEGORY_NOT_EXISTS = new ErrorCode(500, "管理部件分类不存在");

    // ========== 管理部件实例 ==========
    ErrorCode INSTANCE_NOT_EXISTS = new ErrorCode(500, "管理部件实例不存在");
    ErrorCode INSTANCE_CATEGORY_ID_EMPTY = new ErrorCode(100501, "分类ID不能为空");
    ErrorCode INSTANCE_CATEGORY_ID_INVALID = new ErrorCode(100502, "分类ID格式无效");
}
