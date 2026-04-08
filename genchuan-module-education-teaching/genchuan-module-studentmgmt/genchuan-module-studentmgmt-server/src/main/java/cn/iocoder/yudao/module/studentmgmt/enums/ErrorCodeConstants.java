package cn.iocoder.yudao.module.studentmgmt.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * System 错误码枚举类
 * system 系统，使用 1-002-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== 学生信息管理 ==========
    ErrorCode STUDENT_INFO_NOT_EXISTS = new ErrorCode(500, "学生信息管理不存在");

    // ========== 荣誉管理==========
    ErrorCode HONOR_MGMT_NOT_EXISTS = new ErrorCode(500, "荣誉管理不存在");

}
