package cn.iocoder.yudao.module.vehiclepass.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

public interface ErrorCodeConstants {

    ErrorCode IDENTIFY_NOT_EXISTS = new ErrorCode(500, "片区信息不存在");

    ErrorCode RECORD_NOT_EXISTS = new ErrorCode(500, "入场记录不存在");
}
