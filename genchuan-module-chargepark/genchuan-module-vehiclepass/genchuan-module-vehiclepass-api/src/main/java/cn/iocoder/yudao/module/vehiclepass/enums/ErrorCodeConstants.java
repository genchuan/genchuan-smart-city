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

    ErrorCode TASK_NOT_EXISTS = new ErrorCode(500, "稽查任务不存在");

    ErrorCode HANDLE_NOT_EXISTS = new ErrorCode(500, "结果处置不存在");

    ErrorCode INPUT_NOT_EXISTS = new ErrorCode(500, "车辆录入不存在");

    ErrorCode QUERY_NOT_EXISTS = new ErrorCode(500, "泊位查询不存在");

    ErrorCode PARK_NOT_EXISTS = new ErrorCode(500, "车位不存在");

    ErrorCode USER_CAR_NOT_EXISTS = new ErrorCode(500, "未录入用户车辆");

    // 无牌入场业务错误码
    ErrorCode ENTER_STATUS_INVALID = new ErrorCode(500, "无牌入场状态不正确，无法执行该操作");
    ErrorCode ENTER_AUDIT_FAILED = new ErrorCode(500, "无牌入场审核失败，状态必须为待审核");
    ErrorCode ENTER_CONFIRM_FAILED = new ErrorCode(500, "无牌入场确认失败，状态必须为已通过");
    ErrorCode ENTER_CORRECT_FAILED = new ErrorCode(500, "无牌入场修正失败，状态必须为待审核或已通过");

    // 通用业务错误码
    ErrorCode TIMESTAMP_PARSE_ERROR = new ErrorCode(500, "时间戳格式错误");
    ErrorCode USER_NOT_LOGIN = new ErrorCode(401, "用户未登录");

    // 开闸管理业务错误码
    ErrorCode GATE_OPEN_STATUS_INVALID = new ErrorCode(500, "开闸记录当前状态不允许执行该操作");

    // 稽查任务业务错误码
    ErrorCode TASK_STATUS_INVALID = new ErrorCode(500, "稽查任务当前状态不允许执行该操作");
}
