package cn.iocoder.yudao.module.facility.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * System 错误码枚举类
 * system 系统，使用 1-002-000-000 段
 */
public interface ErrorCodeConstants {
    // ========== 道路监测==========
    ErrorCode MONITOR_NOT_EXISTS = new ErrorCode(500, "道路监测不存在");
    // ========== 道路监测配置==========
    ErrorCode ROAD_CONFIG_NOT_EXISTS = new ErrorCode(500, "道路监测配置不存在");
    // ========== 窨井盖==========
    ErrorCode COVER_NOT_EXISTS = new ErrorCode(500, "窨井盖不存在");
    // ========== 窨井盖配置==========
    ErrorCode MANHOLE_CONFIG_NOT_EXISTS = new ErrorCode(500, "窨井盖配置不存在");

    // ================= 操作类型不合法，仅支持 0-停止、1-启动
    ErrorCode OPERATION_TYPE_NOT_SUPPORT = new ErrorCode(500, "操作类型不支持");
    // ========== 窨井盖配置已存在==========
     ErrorCode MANHOLE_CONFIG_EXISTS = new ErrorCode(500, "窨井盖配置已存在");
    // ========== 配置不存在或无权限 ========
    ErrorCode CONFIG_NOT_EXISTS_OR_NO_PERMISSION = new ErrorCode(500, "配置不存在或无权限");
    // ========== 井盖ID:{} 不存在或不属于租户:{}
    ErrorCode COVER_NOT_EXISTS_OR_NOT_BELONG_TO_TENANT = new ErrorCode(500, "窨井盖ID:{} 不存在或不属于租户:{}");
    // ========== 处置工单==========
    ErrorCode ORDER_NOT_EXISTS = new ErrorCode(500, "处置工单不存在");
    // ========== 预警==========
    ErrorCode WARN_NOT_EXISTS = new ErrorCode(500, "预警不存在");

    // ========== 通用预警==========
    ErrorCode SYS_WARN_NOT_EXISTS = new ErrorCode(500, "通用预警不存在");

    // ========== 道路设施==========
    ErrorCode ROAD_FACILITY_NOT_EXISTS = new ErrorCode(500, "道路设施不存在");

    // ========== 设备==========
    ErrorCode SYS_DEVICE_NOT_EXISTS = new ErrorCode(500, "设备不存在");

    // ========== 工单==========
    ErrorCode WORK_ORDER_NOT_EXISTS = new ErrorCode(500, "工单不存在");

    // ========== 系统用户==========
    ErrorCode SYS_USER_NOT_EXISTS = new ErrorCode(500, "系统用户不存在");
    // ========== 归档==========
    ErrorCode SYS_ARCHIVE_NOT_EXISTS = new ErrorCode(500, "归档不存在");

}
