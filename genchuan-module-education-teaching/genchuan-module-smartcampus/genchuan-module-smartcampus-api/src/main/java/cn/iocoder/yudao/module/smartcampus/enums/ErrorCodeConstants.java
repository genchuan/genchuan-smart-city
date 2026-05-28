package cn.iocoder.yudao.module.smartcampus.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

public interface ErrorCodeConstants {
    ErrorCode ARCHIVE_NOT_EXISTS = new ErrorCode(500, "学籍档案不存在");

    ErrorCode ARCHIVE_PROCESS_STATUS_NOT_NULL= new ErrorCode(500, "审核状态不能为空");

    ErrorCode ARCHIVE_STATUS_NOT_NULL= new ErrorCode(500, "学籍状态不能为空");
}
