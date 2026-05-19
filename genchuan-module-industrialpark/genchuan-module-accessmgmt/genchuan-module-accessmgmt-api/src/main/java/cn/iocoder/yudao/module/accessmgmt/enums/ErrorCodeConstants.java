package cn.iocoder.yudao.module.accessmgmt.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * accessmgmt 错误码枚举类
 * accessmgmt 系统，使用500错误码
 */
public interface ErrorCodeConstants {

    // ========== 人脸管理 ==========
    ErrorCode FACE_MGMT_NOT_EXISTS = new ErrorCode(500, "人脸信息不存在");
    ErrorCode FACE_MGMT_USER_NAME_EXISTS = new ErrorCode(500, "人员姓名已存在");
    ErrorCode FACE_MGMT_PHONE_EXISTS = new ErrorCode(500, "手机号已存在");

    // ========== 通行记录 ==========
    ErrorCode ACCESS_RECORD_NOT_EXISTS = new ErrorCode(500, "通行记录不存在");

    // ========== 访客预约 ==========
    ErrorCode VISITOR_APPOINT_NOT_EXISTS = new ErrorCode(500, "访客预约不存在");

    // ========== 访客通行 ==========
    ErrorCode VISITOR_ACCESS_NOT_EXISTS = new ErrorCode(500, "访客通行记录不存在");

    // ========== 车位管理 ==========
    ErrorCode PARKING_SPACE_NOT_EXISTS = new ErrorCode(500, "车位信息不存在");
    ErrorCode PARKING_SPACE_CODE_EXISTS = new ErrorCode(500, "车位编号已存在");

    // ========== 车辆通行 ==========
    ErrorCode VEHICLE_ACCESS_NOT_EXISTS = new ErrorCode(500, "车辆通行记录不存在");

    // ========== 停车缴费 ==========
    ErrorCode PARKING_PAYMENT_NOT_EXISTS = new ErrorCode(500, "停车缴费记录不存在");

    // ========== 通行周期报表 ==========
    ErrorCode ACCESS_CYCLE_REPORT_NOT_EXISTS = new ErrorCode(500, "通行周期报表不存在");

}
