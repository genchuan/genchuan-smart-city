package cn.iocoder.yudao.module.waterdetection.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * waterdetection 错误码枚举类
 *
 * waterdetection 系统，使用 105-000-000 段
 */
public interface ErrorCodeConstants {


    // =============================================================================================================
    // =============================================== 水  质  检  测  系  统 ========================================
    // =============================================================================================================

    // ========== 水质检测信息 105_01 ==========
    ErrorCode WATER_SAMPLE_INFO_NOT_EXISTS = new ErrorCode(105_01, "水质检测信息不存在");
    ErrorCode WATER_SAMPLE_RESULT_NOT_EXISTS = new ErrorCode(105_02, "出厂水检测结果不存在");
    ErrorCode WATER_SAMPLE_RESULT_EXISTS = new ErrorCode(105_03, "出厂水检测结果已存在");

    // ========== 《生活饮用水卫生标准》GB 5749-2022标准 105_04 ==========
    ErrorCode GB5749_STANDARD_NOT_EXISTS = new ErrorCode(105_04, "《生活饮用水卫生标准》标准不存在");

    // ========== 外检统计水质检测结果汇总 105_05 ==========
    ErrorCode WATER_SAMPLE_TEST_SUMMARY_NOT_EXISTS = new ErrorCode(105_05, "外检统计水质检测结果汇总不存在");
    /**
     * 设备相关错误码
     */
    ErrorCode DEVICE_AUTH_FAILED = new ErrorCode(1002001000, "设备认证失败");
    ErrorCode DEVICE_DATA_FORMAT_ERROR = new ErrorCode(1002001001, "设备数据格式错误");
    ErrorCode DEVICE_NOT_REGISTERED = new ErrorCode(1002001002, "设备未注册");

    // ========== 水源类型及属性管理 105_06 ==========
    ErrorCode WATER_SOURCE_MANAGEMENT_NOT_EXISTS = new ErrorCode(105_06, "水源类型及属性管理不存在");

    // ========== 水源保护区管理 105_07 ==========
    ErrorCode WATER_PROTECTION_AREA_NOT_EXISTS = new ErrorCode(105_07, "水源保护区管理不存在");

    // ========== 水源水文参数管理 105_08 ==========
    ErrorCode WATER_HYDROLOGY_PARAM_NOT_EXISTS = new ErrorCode(105_08, "水源水文参数管理不存在");

    // ========== 周边污染源档案管理 105_09 ==========
    ErrorCode POLLUTION_SOURCE_ARCHIVE_NOT_EXISTS = new ErrorCode(105_09, "周边污染源档案管理不存在");

    // ========== 工程基本信息管理 105_010 ==========
    ErrorCode PROJECT_BASIC_INFO_NOT_EXISTS = new ErrorCode(105_010, "工程基本信息管理不存在");

    // ========== 构建筑物参数管理 105_011 ==========
    ErrorCode STRUCTURE_PARAM_MANAGE_NOT_EXISTS = new ErrorCode(105_011, "构建筑物参数管理不存在");

    // ========== 设备资产台账管理 105_012 ==========
    ErrorCode EQUIPMENT_ASSET_NOT_EXISTS = new ErrorCode(105_012, "设备资产台账管理不存在");

    // ========== 用户基础信息登记 105_013 ==========
    ErrorCode USER_BASIC_INFO_NOT_EXISTS = new ErrorCode(105_013, "用户基础信息登记不存在");

    // ========== 用水性质分类管理 105_014 ==========
    ErrorCode WATER_USE_CATEGORY_NOT_EXISTS = new ErrorCode(105_014, "用水性质分类管理不存在");

    // ========== 户表关联及变更管理 105_015 ==========
    ErrorCode METER_USER_RELATION_NOT_EXISTS = new ErrorCode(105_015, "户表关联及变更管理不存在");

    // ========== 供水协议管理 105_016 ==========
    ErrorCode WATER_SUPPLY_AGREEMENT_NOT_EXISTS = new ErrorCode(105_016, "供水协议管理不存在");

    // ========== 检测机构资质管理 105_017 ==========
    ErrorCode TESTING_AGENCY_NOT_EXISTS = new ErrorCode(105_017, "检测机构资质管理不存在");

    // ========== 检测能力及设备管理 105_018 ==========
    ErrorCode TESTING_CAPABILITY_NOT_EXISTS = new ErrorCode(105_018, "检测能力及设备管理不存在");

    // ========== 检测人员信息管理 105_019 ==========
    ErrorCode TESTING_PERSONNEL_NOT_EXISTS = new ErrorCode(105_019, "检测人员信息管理不存在");

    // ========== 责任单位及责任人管理 105_020 ==========
    ErrorCode RESPONSIBILITY_MANAGEMENT_NOT_EXISTS = new ErrorCode(105_020, "责任单位及责任人管理不存在");

    // ========== 岗位职责划分管理 105_021 ==========
    ErrorCode POSITION_RESPONSIBILITY_NOT_EXISTS = new ErrorCode(105_021, "岗位职责划分管理不存在");

    // ========== 采样点规划 105_022 ==========
    ErrorCode SAMPLING_POINT_NOT_EXISTS = new ErrorCode(105_022, "采样点规划不存在");

    // ========== 采样频率设置 105_023 ==========
    ErrorCode SAMPLING_FREQUENCY_NOT_EXISTS = new ErrorCode(105_023, "采样频率设置不存在");

    // ========== 预警指标配置 105_024 ==========
    ErrorCode WARNING_INDICATOR_NOT_EXISTS = new ErrorCode(105_024, "预警指标配置不存在");

    // ========== 预警阈值管理 105_025 ==========
    ErrorCode WARNING_THRESHOLD_NOT_EXISTS = new ErrorCode(105_025, "预警阈值管理不存在");

    // ========== 预警模型校验 105_026 ==========
    ErrorCode WARNING_MODEL_VALIDATION_NOT_EXISTS = new ErrorCode(105_026, "预警模型校验不存在");

    // ========== 采样人员分配 105_027 ==========
    ErrorCode SAMPLING_ASSIGNMENT_NOT_EXISTS = new ErrorCode(105_027, "采样人员分配不存在");

    // ========== 任务派发 105_028 ==========
    ErrorCode TASK_DISPATCH_NOT_EXISTS = new ErrorCode(105_028, "任务派发不存在");

    // ========== 检测进度跟踪 105_029 ==========
    ErrorCode TEST_PROGRESS_NOT_EXISTS = new ErrorCode(105_029, "检测进度跟踪不存在");

    // ========== 检测结果录入 105_030 ==========
    ErrorCode TEST_RESULT_NOT_EXISTS = new ErrorCode(105_030, "检测结果录入不存在");

    // ========== 监测仪表校准管理 105_031 ==========
    ErrorCode METER_CALIBRATION_NOT_EXISTS = new ErrorCode(105_031, "监测仪表校准管理不存在");

    // ========== 设备保养计划管理 105_032 ==========
    ErrorCode EQUIPMENT_MAINTENANCE_NOT_EXISTS = new ErrorCode(105_032, "设备保养计划管理不存在");

    // ========== 耗材库存与更换管理 105_033 ==========
    ErrorCode CONSUMABLE_MANAGEMENT_NOT_EXISTS = new ErrorCode(105_033, "耗材库存与更换管理不存在");

    // ========== 巡检路线规划与优化 105_034 ==========
    ErrorCode INSPECTION_ROUTE_NOT_EXISTS = new ErrorCode(105_034, "巡检路线规划与优化不存在");

    // ========== 巡检任务派发与执行 105_035 ==========
    ErrorCode INSPECTION_TASK_NOT_EXISTS = new ErrorCode(105_035, "巡检任务派发与执行不存在");

    // ========== 问题上报与闭环跟踪 105_036 ==========
    ErrorCode ISSUE_TRACKING_NOT_EXISTS = new ErrorCode(105_036, "问题上报与闭环跟踪不存在");

    // ========== DMA分区划分与调整 105_037 ==========
    ErrorCode DMA_PARTITION_NOT_EXISTS = new ErrorCode(105_037, "DMA分区划分与调整不存在");

    // ========== 水量平衡与漏损分析 105_038 ==========
    ErrorCode WATER_BALANCE_NOT_EXISTS = new ErrorCode(105_038, "水量平衡与漏损分析不存在");

    // ========== 漏损控制方案建议 105_039 ==========
    ErrorCode LEAKAGE_CONTROL_PLAN_NOT_EXISTS = new ErrorCode(105_039, "漏损控制方案建议不存在");

    // ========== 在线数据与实验室比对 105_040 ==========
    ErrorCode ONLINE_LAB_COMPARISON_NOT_EXISTS = new ErrorCode(105_040, "在线数据与实验室比对不存在");

    // ========== 仪器零点/量程漂移校验 105_041 ==========
    ErrorCode INSTRUMENT_CALIBRATION_NOT_EXISTS = new ErrorCode(105_041, "仪器零点/量程漂移校验不存在");

    // ========== 不合格数据处理 105_042 ==========
    ErrorCode INVALID_DATA_NOT_EXISTS = new ErrorCode(105_042, "不合格数据处理不存在");
}
