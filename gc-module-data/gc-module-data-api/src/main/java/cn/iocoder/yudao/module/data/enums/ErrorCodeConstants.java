package cn.iocoder.yudao.module.data.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * System 错误码枚举类
 * system 系统，使用 1-002-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== 地理编码 ==========
    ErrorCode GEO_CODE_NOT_EXISTS = new ErrorCode(500, "地理编码不存在");
    ErrorCode CATEGORY_HAS_CHILDREN = new ErrorCode(100401, "分类存在子分类，无法删除");
    ErrorCode CATEGORY_DELETE_FAILED = new ErrorCode(100402, "分类删除失败");
    ErrorCode CATEGORY_BATCH_DELETE_FAILED = new ErrorCode(100403, "批量删除分类失败");

    // ========== 管理部件分类 ==========
    ErrorCode CATEGORY_NOT_EXISTS = new ErrorCode(500, "管理部件分类不存在");

    // ========== 管理部件实例 ==========
    ErrorCode INSTANCE_NOT_EXISTS = new ErrorCode(500, "管理部件实例不存在");
    ErrorCode INSTANCE_CATEGORY_ID_EMPTY = new ErrorCode(100501, "分类ID不能为空");
    ErrorCode INSTANCE_CATEGORY_ID_INVALID = new ErrorCode(100502, "分类ID格式无效");
    // 错误码编号需确保唯一，例如接着已有的顺序
    ErrorCode INSTANCE_IMPORT_FILE_EMPTY = new ErrorCode(1_004_005, "导入Excel文件不能为空");
    ErrorCode INSTANCE_IMPORT_DATA_EMPTY = new ErrorCode(1_004_006, "导入Excel数据为空");
    // ========== 管理事项分类 ==========
    ErrorCode MATTER_CATEGORY_NOT_EXISTS = new ErrorCode(500, "管理事项分类不存在");
    // ========== 管理事项实例==========
    ErrorCode MATTER_INSTANCE_NOT_EXISTS = new ErrorCode(500, "管理事项实例不存在");
    ErrorCode MATTER_CATEGORY_HAS_CHILDREN = new ErrorCode(500, "管理事项分类存在子分类，无法删除");
}
