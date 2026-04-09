package cn.iocoder.yudao.module.studentmgmt.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * studentmgmt 错误码枚举类
 *
 * studentmgmt 系统
 */
public interface ErrorCodeConstants {


    // =============================================================================================================
    // =============================================== 学 生 管 理 模 块 ==================================================
    // =============================================================================================================



    // ========== 学生信息管理 ==========
    ErrorCode STUDENT_INFO_NOT_EXISTS = new ErrorCode(500, "学生信息管理不存在");

    // ========== 荣誉管理==========
    ErrorCode HONOR_MGMT_NOT_EXISTS = new ErrorCode(500, "荣誉管理不存在");

    // ========== 考评管理 ==========
    ErrorCode ASSESS_MGMT_NOT_EXISTS = new ErrorCode(500, "考评管理不存在");


}
