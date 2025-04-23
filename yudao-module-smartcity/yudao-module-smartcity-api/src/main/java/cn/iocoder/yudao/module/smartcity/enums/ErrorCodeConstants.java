package cn.iocoder.yudao.module.smartcity.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * System 错误码枚举类
 *
 * system 系统，使用 1-002-000-000 段
 */
public interface ErrorCodeConstants {


    // =============================================================================================================
    // =============================================== 行 业 应 用 模 块 ==================================================
    // =============================================================================================================

    // ========== 警报处理类别  1_000_001 ==========
    ErrorCode ALARM_HANDLING_CATEGORY_NOT_EXISTS = new ErrorCode(1_000_001, "警报处理类别不存在");

    // ========== 风险管控 1_000_002==========
    ErrorCode RISK_CONTROL_NOT_EXISTS = new ErrorCode(1_000_002, "风险管控不存在");

    // ========== 养护考核评价 1_000_003==========
    ErrorCode ASSESSMENT_AND_EVALUATION_NOT_EXISTS = new ErrorCode(1_000_003, "养护考核评价不存在");

    // ========== 养护任务 1_000_004 ==========
    ErrorCode MAINTENANCE_TASKS_NOT_EXISTS = new ErrorCode(1_000_004, "养护任务不存在");

    // ========== 养护地块 1_000_005 ==========
    ErrorCode MAINTENANCE_PLOT_NOT_EXISTS = new ErrorCode(1_000_005, "养护地块不存在");

    // ========== 养护计划 1_000_006 ==========
    ErrorCode MAINTENANCE_PLAN_NOT_EXISTS = new ErrorCode(1_000_006, "养护计划不存在");

    // ========== 养护人员 1_000_007 ==========
    ErrorCode MAINTENANCE_PERSONNEL_NOT_EXISTS = new ErrorCode(1_000_007, "养护人员不存在");

    // ========== 巡査人员管理 1_000_008 ==========
    ErrorCode MANAGEMENT_OF_PATROL_PERSONNEL_NOT_EXISTS = new ErrorCode(1_000_008, "巡査人员管理不存在");

    // ========== 巡查计划管理 1_000_009 ==========
    ErrorCode INSPECTION_PLAN_MANAGEMENT_NOT_EXISTS = new ErrorCode(1_000_009, "巡查计划管理不存在");

    // ========== 巡査任务管理 1_000_010 ==========
    ErrorCode PATROL_TASK_MANAGEMENT_NOT_EXISTS = new ErrorCode(1_000_010, "巡査任务管理不存在");

    // ========== 人员密集场所安全 1_000_011 ==========
    ErrorCode DENSELY_POPULATED_AREAS_NOT_EXISTS = new ErrorCode(1_000_011, "人员密集场所安全不存在");

    // ========== 环卫设施安全 1_000_012 ==========
    ErrorCode ENVIRONMENTAL_SAFETY_NOT_EXISTS = new ErrorCode(1_000_012, "环卫设施安全不存在");

    // ========== 内涝安全 1_000_013 ==========
    ErrorCode FLOOD_SAFETY_NOT_EXISTS = new ErrorCode(1_000_013, "内涝安全不存在");

    // ========== 管廊安全 1_000_014 ==========
    ErrorCode PIPE_GALLERY_SAFETY_NOT_EXISTS = new ErrorCode(1_000_014, "管廊安全不存在");

    // ========== 路面塌陷安全 1_000_015 ==========
    ErrorCode ROAD_COLLAPSE_SAFETY_NOT_EXISTS = new ErrorCode(1_000_015, "路面塌陷安全不存在");

    // ========== 桥梁安全 1_000_016 ==========
    ErrorCode BRIDGE_SAFETY_NOT_EXISTS = new ErrorCode(1_000_016, "桥梁安全不存在");

    // ========== 巡查巡检类 1_000_017 ==========
    ErrorCode INSPECTION_AND_PATROL_CATEGORY_NOT_EXISTS = new ErrorCode(1_000_017, "巡查巡检类不存在");

    // ========== 机构信息录入 1_101_001 ==========
    ErrorCode INSTITUTIONAL_INFORMATION_INPUT_NOT_EXISTS = new ErrorCode(1_101_001, "机构信息录入不存在");

    // ========== 机构信息录入 1_101_002 ==========
    ErrorCode QUESTION_CLASSIFICATION_NOT_EXISTS = new ErrorCode(1_101_002, "问题录入不存在");

    // ========== 问题录入 1_101_003 ==========
    ErrorCode PROBLEM_INPUT_NOT_EXISTS = new ErrorCode(1_101_003, "问题录入不存在");

    // ========== 指南信息分类 1_101_004 ==========
    ErrorCode CLASSIFICATION_OF_GUIDE_INFORMATION_NOT_EXISTS = new ErrorCode(1_101_004, "指南信息分类不存在");

    // ========== 指南信息录入 1_101_005 ==========
    ErrorCode GUIDE_INFORMATION_INPUT_NOT_EXISTS = new ErrorCode(1_101_005, "指南信息录入不存在");

    // ========== 政策法规录入 1_101_006 ==========
    ErrorCode POLICY_AND_REGULATION_INPUT_NOT_EXISTS = new ErrorCode(1_101_006, "政策法规录入不存在");

    // ========== 政策法规分类 1_101_007 ==========
    ErrorCode CLASSIFICATION_OF_POLICIES_AND_REGULATIONS_NOT_EXISTS = new ErrorCode(1_101_007, "政策法规分类不存在");

    // ========== 动态信息录入 1_101_008 ==========
    ErrorCode DYNAMIC_INFORMATION_INPUT_NOT_EXISTS = new ErrorCode(1_101_008, "动态信息录入不存在");

    // ========== 动态信息分类 1_101_009 ==========
    ErrorCode DYNAMIC_INFORMATION_CLASSIFICATION_NOT_EXISTS = new ErrorCode(1_101_009, "动态信息分类不存在");

    // ========== 经验信息录入 1_101_010 ==========
    ErrorCode EXPERIENCE_INFORMATION_INPUT_NOT_EXISTS = new ErrorCode(1_101_010, "经验信息录入不存在");

    // ========== 经验信息分类 1_101_011 ==========
    ErrorCode CLASSIFICATION_OF_EXPERIENCE_INFORMATION_NOT_EXISTS = new ErrorCode(1_101_011, "经验信息分类不存在");

    // ========== 车辆信息 1_101_012 ==========
    ErrorCode VEHICLE_INFORMATION_NOT_EXISTS = new ErrorCode(1_101_012, "车辆信息不存在");

    // ========== 车辆异常 1_101_013 ==========
    ErrorCode VEHICLE_ABNORMALITY_NOT_EXISTS = new ErrorCode(1_101_013, "车辆异常不存在");

    // ========== 人员信息 1_101_014 ==========
    ErrorCode PERSONNEL_INFORMATION_NOT_EXISTS = new ErrorCode(1_101_014, "人员信息不存在");

    // ========== 作业区域 1_101_015 ==========
    ErrorCode WORK_AREA_NOT_EXISTS = new ErrorCode(1_101_015, "作业区域不存在");

    // ========== 人员作业 1_101_016 ==========
    ErrorCode PERSONNEL_HOMEWORK_NOT_EXISTS = new ErrorCode(1_101_016, "人员作业不存在");

    // ========== 巡查资源 1_101_017 ==========
    ErrorCode PATROL_RESOURCES_NOT_EXISTS = new ErrorCode(1_101_017, "巡查资源不存在");

    // ========== 巡查人员 1_101_018 ==========
    ErrorCode PATROL_PERSONNEL_NOT_EXISTS = new ErrorCode(1_101_018, "巡查人员不存在");

    // ========== 巡查计划 1_101_019 ==========
    ErrorCode INSPECTION_INSPECTION_PLAN_NOT_EXISTS = new ErrorCode(1_101_019, "巡查计划不存在");

    // ========== 巡查任务 1_101_020 ==========
    ErrorCode INSPECTION_TASK_A_NOT_EXISTS = new ErrorCode(1_101_020, "巡查任务不存在");

    // ========== 巡查结果 1_101_021 ==========
    ErrorCode INSPECTION_RESULTS_A_NOT_EXISTS = new ErrorCode(1_001_021, "巡查结果不存在");

    // ========== 巡查分析统计 1_001_018 ==========
    ErrorCode INSPECTION_STATISTICS_NOT_EXISTS = new ErrorCode(1_001_018, "巡查分析统计不存在");

    // ========== 部件信息 1_001_019 ==========
    ErrorCode COMPONENT_INFORMATION_NOT_EXISTS = new ErrorCode(1_001_019, "部件信息不存在");

    // ========== 事件上报 1_001_020 ==========
    ErrorCode EVENT_REPORTING_NOT_EXISTS = new ErrorCode(1_001_020, "事件上报不存在");

    // ========== 事件处理 1_001_021 ==========
    ErrorCode EVENT_PROCESSING_NOT_EXISTS = new ErrorCode(1_001_021, "事件处理不存在");

    // ========== 事件统计分析 1_001_022 ==========
    ErrorCode EVENT_STATISTICAL_ANALYSIS_NOT_EXISTS = new ErrorCode(1_001_022, "事件统计分析不存在");

    // ========== 事件关联分析 1_001_023 ==========
    ErrorCode EVENT_ANALYSIS_NOT_EXISTS = new ErrorCode(1_001_023, "事件关联分析不存在");

    // ========== 市政公用 1_001_024 ==========
    ErrorCode MUNICIPAL_PUBLIC_UTILITIES_NOT_EXISTS = new ErrorCode(1_001_024, "市政公用不存在");

    // ========== 市容环卫 1_001_025 ==========
    ErrorCode ENVIRONMENTAL_SANITATION_NOT_EXISTS = new ErrorCode(1_001_025, "市容环卫不存在");

    // ========== 园林绿化 1_001_026 ==========
    ErrorCode LANDSCAPING_NOT_EXISTS = new ErrorCode(1_001_026, "园林绿化不存在");

    // ========== 城市管理执法 1_001_027 ==========
    ErrorCode LAW_ENFORCEMENT_NOT_EXISTS = new ErrorCode(1_001_027, "城市管理执法不存在");

    // ========== 故障检测与定位 1_101_023 ==========
    ErrorCode DETECTION_LOCALIZATION_NOT_EXISTS = new ErrorCode(1_101_023, "故障检测与定位不存在");

    // ========== 故障诊断和预警 1_101_024 ==========
    ErrorCode DIAGNOSIS_WARNING_NOT_EXISTS = new ErrorCode(1_101_024, "故障诊断和预警不存在");

    // ========== 执法人员管理 1_101_025 ==========
    ErrorCode OF_LAW_ENFORCEMENT_PERSONNEL_NOT_EXISTS = new ErrorCode(1_101_025, "执法人员管理不存在");

    // ========== 执法车辆管理 1_101_026 ==========
    ErrorCode LAW_ENFORCEMENT_VEHICLE_NOT_EXISTS = new ErrorCode(1_101_026, "执法车辆管理不存在");

    // ========== 执法公示 1_101_027 ==========
    ErrorCode ENFORCEMENT_PUBLICITY_NOT_EXISTS = new ErrorCode(1_101_027, "执法公示不存在");

    // ========== 执法监督 1_101_028 ==========
    ErrorCode LAW_ENFORCEMENT_SUPERVISION_NOT_EXISTS = new ErrorCode(1_101_028, "执法监督不存在");

    // ========== 事件列表 1_101_029 ==========
    ErrorCode EVENT_LIST_NOT_EXISTS = new ErrorCode(1_101_029, "事件列表不存在");

    // ========== 重点工作任务 1_101_030 ==========
    ErrorCode KEY_WORK_TASKS_NOT_EXISTS = new ErrorCode(1_101_030, "重点工作任务不存在");

    // ========== 停车场信息管理 1_101_031 ==========
    ErrorCode PARKING_LOT_INFORMATION_NOT_EXISTS = new ErrorCode(1_101_031, "停车场信息管理不存在");

    // ========== 停车收费管理 1_101_032 ==========
    ErrorCode PARKING_FEE_NOT_EXISTS = new ErrorCode(1_101_032, "停车收费管理不存在");

    // ========== 停车管理用户管理与服务 1_101_033 ==========
    ErrorCode PARKING_NOT_EXISTS = new ErrorCode(1_101_033, "停车管理用户管理与服务不存在");

    // ========== 违建基本信息 1_101_034 ==========
    ErrorCode BASIC_ILLEGAL_NOT_EXISTS = new ErrorCode(1_101_034, "违建基本信息不存在");

    // ========== 巡查计划管理 1_101_035 ==========
    ErrorCode DEVELOPMENT_INSPECTION_PLAN_NOT_EXISTS = new ErrorCode(1_101_035, "巡查计划管理不存在");
    
    // ========== 违建上报 1_101_036 ==========
    ErrorCode REPORT_OF_ILLEGAL_CONSTRUCTIONS_NOT_EXISTS = new ErrorCode(1_101_036, "违建上报不存在");

    // ========== 排水安全 1_101_037 ==========
    ErrorCode DRAINAGE_SAFETY_NOT_EXISTS = new ErrorCode(1_101_037, "排水安全不存在");

    // ========== 监测点位 1_101_038 ==========
    ErrorCode POINT_INFO_NOT_EXISTS = new ErrorCode(1_101_038, "监测点位不存在");

    // ========== 监测设备 1_101_039 ==========
    ErrorCode MONITORING_DEVICE_NOT_EXISTS = new ErrorCode(1_101_039, "监测设备不存在");

    // ========== 监测项规则设置 1_101_040 ==========
    ErrorCode MONITORING_RULE_NOT_EXISTS = new ErrorCode(1_101_040, "监测项规则设置不存在");
}
