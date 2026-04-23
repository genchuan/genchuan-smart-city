package cn.iocoder.yudao.module.vehiclepass.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

public interface ErrorCodeConstants {

    ErrorCode ENTER_NOT_EXISTS = new ErrorCode(500, "无牌入场不存在");

    ErrorCode IDENTIFY_NOT_EXISTS = new ErrorCode(500, "片区信息不存在");

    ErrorCode RECORD_NOT_EXISTS = new ErrorCode(500, "入场记录不存在");

    //类型需要为人工补录
    ErrorCode RECORD_TYPE_NOT_MANUAL = new ErrorCode(500, "入场记录类型非人工补录");

    ErrorCode PARK_STATUS_NOT_EXISTS = new ErrorCode(500, "在停状态不存在");

    ErrorCode PLATE_CONTROL_NOT_EXISTS = new ErrorCode(500, "套牌管控不存在");

    ErrorCode CAR_HANDLE_NOT_EXISTS = new ErrorCode(500, "油车占位处置不存在");

    ErrorCode OPEN_NOT_EXISTS = new ErrorCode(500, "开闸不存在");

    ErrorCode CHECK_NOT_EXISTS = new ErrorCode(500, "缴费核验不存在");

    ErrorCode LEAVE_NOT_EXISTS = new ErrorCode(500, "无牌出场不存在");
}
