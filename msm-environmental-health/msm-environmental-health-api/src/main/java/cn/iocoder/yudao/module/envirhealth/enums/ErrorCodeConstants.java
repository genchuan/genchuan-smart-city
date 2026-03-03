package cn.iocoder.yudao.module.envirhealth.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

public interface ErrorCodeConstants {
    ErrorCode GARBAGE_COLLECTION_NOT_EXISTS = new ErrorCode(999_001, "收运计划不存在");
    ErrorCode GARBAGE_TYPE_NOT_EXISTS = new ErrorCode(999_002, "垃圾品类字典不存在");
    ErrorCode AREA_NOT_EXISTS = new ErrorCode(999_003, "区域编码不存在");
    ErrorCode PLAN_STATUS_NOT_EXISTS = new ErrorCode(999_004, "计划状态字典不存在");
    ErrorCode VEHICLE_NOT_EXISTS = new ErrorCode(999_005, "车辆不存在");
    ErrorCode USER_NOT_EXISTS = new ErrorCode(999_006, "系统用户不存在");
    ErrorCode POINT_NOT_EXISTS = new ErrorCode(999_007, "点位不存在");
    ErrorCode GARBAGE_ABNORMAL_NOT_EXISTS = new ErrorCode(999_008, "垃圾异常记录不存在");
    ErrorCode ABNORMAL_TYPE_NOT_EXISTS = new ErrorCode(999_009, "垃圾异常类型字典不存在");


    ErrorCode PUBLIC_TOILET_NOT_EXISTS = new ErrorCode(999_010, "公厕不存在");
    ErrorCode FACILITY_NOT_EXISTS = new ErrorCode(999_011, "设施字典不存在");
    ErrorCode OPERATION_STATUS_NOT_EXISTS = new ErrorCode(999_012, "运营状态字典不存在");
    ErrorCode TOILET_COMPLAINT_NOT_EXISTS = new ErrorCode(999_013, "公厕投诉不存在");
    ErrorCode COMPLAINT_TYPE_NOT_EXISTS = new ErrorCode(999_014, "投诉类型字典不存在");
    ErrorCode TOILET_FACILITY_REPAIR_NOT_EXISTS = new ErrorCode(999_015, "公厕设施维修不存在");
    ErrorCode CONSUMABLE_NOT_EXISTS = new ErrorCode(999_016, "耗材字典不存在");
    ErrorCode TASK_NOT_EXISTS = new ErrorCode(999_017, "任务表不存在");
    ErrorCode TASK_TYPE_NOT_EXISTS = new ErrorCode(999_018, "任务类型字典表不存在");

    ErrorCode ROAD_CLEANING_NOT_EXISTS = new ErrorCode(999_019, "道路清扫计划不存在");
    ErrorCode ROAD_NOT_EXISTS = new ErrorCode(999_020, "道路不存在");
    ErrorCode TOOL_NOT_EXISTS = new ErrorCode(999_021, "工具字典不存在");
    ErrorCode CLEANING_PROBLEM_NOT_EXISTS = new ErrorCode(999_022, "道路清扫问题不存在");
    ErrorCode PROBLEM_TYPE_NOT_EXISTS = new ErrorCode(999_023, "问题类型字典表不存在");
    ErrorCode TEAM_NOT_EXISTS = new ErrorCode(999_024, "班组不存在");

    ErrorCode GARBAGE_TRANSFER_NOT_EXISTS = new ErrorCode(999_025, "垃圾转运站不存在");
    ErrorCode EQUIPMENT_NOT_EXISTS = new ErrorCode(999_026, "设备不存在");
    ErrorCode TRANSFER_RESERVE_NOT_EXISTS = new ErrorCode(999_027, "进站预约不存在");
    ErrorCode TRANSFER_OPERATION_NOT_EXISTS = new ErrorCode(999_028, "转运作业不存在");
    ErrorCode TRANSFER_ALARM_NOT_EXISTS = new ErrorCode(999_029, "转运站预警不存在");
    ErrorCode ALARM_TYPE_NOT_EXISTS = new ErrorCode(999_030, "预警类型字典不存在");
    ErrorCode TRANSFER_MAINTENANCE_NOT_EXISTS = new ErrorCode(999_031, "设备维护不存在");

    ErrorCode PUBLIC_INSTITUTION_NOT_EXISTS = new ErrorCode(999_032, "公共机构不存在");
    ErrorCode INSTITUTION_TYPE_NOT_EXISTS = new ErrorCode(999_033, "机构类型字典不存在");
    ErrorCode INSTITUTION_PROBLEM_NOT_EXISTS = new ErrorCode(999_034, "公共机构问题不存在");
    ErrorCode INSTITUTION_INSPECTION_NOT_EXISTS = new ErrorCode(999_035, "公共机构核查不存在");

    ErrorCode COMMERCIAL_STREET_NOT_EXISTS = new ErrorCode(999_036, "商业街不存在");
    ErrorCode MAINTAIN_STATUS_NOT_EXISTS = new ErrorCode(999_037, "维护状态字典表不存在");
    ErrorCode HANDLE_STATUS_NOT_EXISTS = new ErrorCode(999_038, "处置状态字典表不存在");

    ErrorCode PARK_NOT_EXISTS = new ErrorCode(999_039, "公园不存在");
    ErrorCode GREEN_TYPE_NOT_EXISTS = new ErrorCode(999_040, "绿化品类字典表不存在");

    ErrorCode URBAN_VILLAGE_NOT_EXISTS = new ErrorCode(999_041, "城中村不存在");
    ErrorCode REVIEW_RESULT_NOT_EXISTS = new ErrorCode(999_042, "复核结果字典表不存在");

    ErrorCode MARKET_NOT_EXISTS = new ErrorCode(999_043, "集贸市场不存在");
    ErrorCode CHECK_RESULT_NOT_EXISTS = new ErrorCode(999_044, "核查结果字典表不存在");

    ErrorCode RIVER_NOT_EXISTS = new ErrorCode(999_045, "河道不存在");
    ErrorCode CLEANING_TYPE_NOT_EXISTS = new ErrorCode(999_046, "保洁类型字典表不存在");
    ErrorCode MONITOR_TYPE_NOT_EXISTS = new ErrorCode(999_047, "监测类型字典表不存在");
    ErrorCode MONITOR_STATUS_NOT_EXISTS = new ErrorCode(999_048, "监测状态字典表不存在");

    ErrorCode VEHICLE_TYPE_NOT_EXISTS = new ErrorCode(999_049, "车辆类型字典不存在");
    ErrorCode DEPT_NOT_EXISTS = new ErrorCode(999_050, "部门不存在");
    ErrorCode ROUTE_NOT_EXISTS = new ErrorCode(999_051, "路线不存在");
    ErrorCode VEHICLE_STATUS_NOT_EXISTS = new ErrorCode(999_052, "车辆状态字典不存在");
    ErrorCode VIOLATION_TYPE_NOT_EXISTS = new ErrorCode(999_053, "违规类型字典表不存在");
    ErrorCode VIOLATION_STATUS_NOT_EXISTS = new ErrorCode(999_054, "违规状态字典表不存在");
    ErrorCode MAINTENANCE_TYPE_NOT_EXISTS = new ErrorCode(999_055, "维护类型字典表不存在");
    ErrorCode WORK_STATUS_NOT_EXISTS = new ErrorCode(999_056, "作业状态字典表不存在");

    ErrorCode JOB_TYPE_NOT_EXISTS = new ErrorCode(999_057, "岗位类型字典不存在");
    ErrorCode PERSON_STATUS_NOT_EXISTS = new ErrorCode(999_058, "人员状态字典不存在");
    ErrorCode SCHEDULE_NOT_EXISTS = new ErrorCode(999_059, "排班计划不存在");
    ErrorCode SCHEDULE_STATUS_NOT_EXISTS = new ErrorCode(999_060, "排班状态字典表不存在");
    ErrorCode ATTENDANCE_NOT_EXISTS = new ErrorCode(999_061, "考勤不存在");
    ErrorCode ATTENDANCE_STATUS_NOT_EXISTS = new ErrorCode(999_062, "考勤状态字典表不存在");
    ErrorCode ATTENDANCE_ABNORMAL_TYPE_NOT_EXISTS = new ErrorCode(999_063, "考勤异常类型字典表不存在");
    ErrorCode ASSESSMENT_NOT_EXISTS = new ErrorCode(999_064, "考核不存在");
    ErrorCode ASSESSMENT_GRADE_NOT_EXISTS = new ErrorCode(999_065, "考核等级字典表不存在");
    ErrorCode REVIEW_STATUS_NOT_EXISTS = new ErrorCode(999_066, "审核状态字典表不存在");

    ErrorCode COLLECTION_FREQUENCY_NOT_EXISTS = new ErrorCode(999_067, "收运频次字典不存在");
    ErrorCode COLLECTION_TIME_PERIOD_NOT_EXISTS = new ErrorCode(999_068, "收运时段字典不存在");


    ErrorCode COLLECTION_STATISTICS_TIME_REQUIRED = new ErrorCode(1050080001, "统计时间不能为空");
    ErrorCode COLLECTION_STATISTICS_DIMENSION_INVALID = new ErrorCode(1050080002, "统计维度无效，只能是day/week/month");
}
