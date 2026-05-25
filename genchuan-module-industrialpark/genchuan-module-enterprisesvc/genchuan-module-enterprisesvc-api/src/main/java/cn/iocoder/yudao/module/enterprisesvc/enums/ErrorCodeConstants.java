package cn.iocoder.yudao.module.enterprisesvc.enums;


import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * enterprisesvc 错误码枚举类
 * enterprisesvc 系统，使用500错误码
 */
public interface ErrorCodeConstants {

    // ========== 企业档案 ==========
    ErrorCode ENTERPRISE_FILE_NOT_EXISTS = new ErrorCode(500, "企业档案不存在");
    ErrorCode ENTERPRISE_FILE_CREDIT_CODE_EXISTS = new ErrorCode(501, "统一社会信用代码已存在");
    ErrorCode ENTERPRISE_FILE_UPLOAD_FILE_EMPTY = new ErrorCode(502, "资质文件不能为空");
    ErrorCode ENTERPRISE_FILE_UPLOAD_FAIL = new ErrorCode(503, "资质文件上传失败");

    // ========== 企业员工==========
    ErrorCode STAFF_MGMT_NOT_EXISTS = new ErrorCode(500, "企业员工不存在");







}
