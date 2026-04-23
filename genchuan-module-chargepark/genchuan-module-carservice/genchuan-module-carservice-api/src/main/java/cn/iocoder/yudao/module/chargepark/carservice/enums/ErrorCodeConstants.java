package cn.iocoder.yudao.module.chargepark.carservice.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * carservice 错误码枚举类
 *
 * carservice 系统使用 1-100-xxx-xxx 段
 */
public interface ErrorCodeConstants {

    // ========== 救援服务 1-100-001-xxx ==========
    ErrorCode RESCUE_INFO_NOT_EXISTS = new ErrorCode(1_100_001_001, "救援信息不存在");
    ErrorCode RESCUE_INFO_STATUS_INVALID = new ErrorCode(1_100_001_002, "救援状态不允许此操作");
    ErrorCode RESCUE_INFO_ALREADY_ARCHIVED_EVALUATE = new ErrorCode(1_100_001_003, "该救援信息已归档,不能再评价");
    ErrorCode RESCUE_INFO_ALREADY_ARCHIVED = new ErrorCode(1_100_001_004, "该救援信息已归档,请勿重复归档");

    // ========== 车辆引导 1-100-002-xxx ==========
    ErrorCode CHARGE_PARK_MAP_NOT_EXISTS = new ErrorCode(1_100_002_001, "充停地图查询记录不存在");
    ErrorCode NEAR_STATION_NOT_EXISTS = new ErrorCode(1_100_002_101, "周边场站查询记录不存在");
    ErrorCode SPACE_PUSH_NOT_EXISTS = new ErrorCode(1_100_002_201, "空位推送记录不存在");
    ErrorCode SPACE_PUSH_STATUS_INVALID = new ErrorCode(1_100_002_202, "空位推送状态不允许此操作");

    // ========== 预约服务 1-100-003-xxx ==========
    ErrorCode RESERVE_LIST_NOT_EXISTS = new ErrorCode(1_100_003_001, "预约记录不存在");
    ErrorCode RESERVE_LIST_STATUS_INVALID = new ErrorCode(1_100_003_002, "预约状态不允许此操作");

    // ========== 反向寻车 1-100-004-xxx ==========
    ErrorCode SPACE_LOCATION_NOT_EXISTS = new ErrorCode(1_100_004_001, "车位定位记录不存在");
    ErrorCode PATH_PLAN_NOT_EXISTS = new ErrorCode(1_100_004_101, "路径规划记录不存在");

    // ========== 投诉调解 1-100-005-xxx ==========
    ErrorCode SUGGESTION_NOT_EXISTS = new ErrorCode(1_100_005_001, "意见建议不存在");
    ErrorCode SUGGESTION_STATUS_INVALID = new ErrorCode(1_100_005_002, "意见处理状态不允许此操作");
    ErrorCode USER_APPEAL_NOT_EXISTS = new ErrorCode(1_100_005_101, "用户申诉不存在");
    ErrorCode USER_APPEAL_STATUS_INVALID = new ErrorCode(1_100_005_102, "用户申诉状态不允许此操作");
    ErrorCode DISPUTE_MEDIATE_NOT_EXISTS = new ErrorCode(1_100_005_201, "纠纷调解记录不存在");
    ErrorCode DISPUTE_MEDIATE_STATUS_INVALID = new ErrorCode(1_100_005_202, "纠纷调解状态不允许此操作");

    // ========== 客服配置 1-100-006-xxx ==========
    ErrorCode WORDING_MGMT_NOT_EXISTS = new ErrorCode(1_100_006_001, "话术不存在");
    ErrorCode WORDING_MGMT_NAME_DUPLICATE = new ErrorCode(1_100_006_002, "话术名称已存在");

    // ========== 决策分析 1-100-007-xxx ==========
    ErrorCode SERVICE_OP_REPORT_PARAM_INVALID = new ErrorCode(1_100_007_001, "服务运营报表参数无效");

    // ========== 跨模块外键校验 1-100-008-xxx ==========
    ErrorCode STATION_NOT_EXISTS = new ErrorCode(1_100_008_001, "场站不存在");
    ErrorCode SPACE_NOT_EXISTS = new ErrorCode(1_100_008_002, "车位不存在");
    ErrorCode RESCUE_USER_NOT_EXISTS = new ErrorCode(1_100_008_003, "救援人员不存在");
    ErrorCode RESCUE_USER_DISABLED = new ErrorCode(1_100_008_004, "救援人员已停用");
    ErrorCode CROSS_MODULE_RPC_UNAVAILABLE = new ErrorCode(1_100_008_005, "下游服务暂不可用,请稍后重试");

}
