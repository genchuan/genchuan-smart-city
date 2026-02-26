package cn.iocoder.yudao.module.envir.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

public interface ErrorCodeConstants {
    ErrorCode GARBAGE_COLLECTION_NOT_EXISTS = new ErrorCode(100_001, "收运计划不存在");
    ErrorCode GARBAGE_TYPE_NOT_EXISTS = new ErrorCode(100_002, "垃圾品类字典不存在");
    ErrorCode PLAN_STATUS_NOT_EXISTS = new ErrorCode(100_003, "计划状态字典不存在");
    ErrorCode VEHICLE_NOT_EXISTS = new ErrorCode(100_4, "车辆不存在");
    ErrorCode PUBLIC_TOILET_NOT_EXISTS = new ErrorCode(100_005, "公厕不存在");
    ErrorCode FACILITY_NOT_EXISTS = new ErrorCode(100_006, "设施字典不存在");
    ErrorCode OPERATION_STATUS_NOT_EXISTS = new ErrorCode(100_007, "运营状态字典不存在");
    ErrorCode ROAD_CLEANING_NOT_EXISTS = new ErrorCode(100_008, "道路清扫计划不存在");
    ErrorCode ROAD_NOT_EXISTS = new ErrorCode(100_009, "道路不存在");
    ErrorCode TOOL_NOT_EXISTS = new ErrorCode(100_010, "工具字典不存在");
    ErrorCode GARBAGE_TRANSFER_NOT_EXISTS = new ErrorCode(100_011, "垃圾转运站不存在");
    ErrorCode EQUIPMENT_NOT_EXISTS = new ErrorCode(100_012, "设备不存在");
    ErrorCode PUBLIC_INSTITUTION_NOT_EXISTS = new ErrorCode(100_013, "公共机构不存在");
    ErrorCode INSTITUTION_TYPE_NOT_EXISTS = new ErrorCode(100_014, "机构类型字典不存在");
    ErrorCode COMMERCIAL_STREET_NOT_EXISTS = new ErrorCode(100_015, "商业街不存在");
    ErrorCode PARK_NOT_EXISTS = new ErrorCode(100_016, "公园不存在");
    ErrorCode URBAN_VILLAGE_NOT_EXISTS = new ErrorCode(100_017, "城中村不存在");
    ErrorCode MARKET_NOT_EXISTS = new ErrorCode(100_018, "集贸市场不存在");
    ErrorCode RIVER_NOT_EXISTS = new ErrorCode(100_019, "河道不存在");
    ErrorCode VEHICLE_TYPE_NOT_EXISTS = new ErrorCode(100_020, "车辆类型字典不存在");
    ErrorCode DEPT_NOT_EXISTS = new ErrorCode(100_021, "部门不存在");
    ErrorCode ROUTE_NOT_EXISTS = new ErrorCode(100_022, "路线不存在");
    ErrorCode VEHICLE_STATUS_NOT_EXISTS = new ErrorCode(100_023, "车辆状态字典不存在");
    ErrorCode JOB_TYPE_NOT_EXISTS = new ErrorCode(100_024, "岗位类型字典不存在");
    ErrorCode TEAM_NOT_EXISTS = new ErrorCode(100_025, "班组不存在");
    ErrorCode PERSON_STATUS_NOT_EXISTS = new ErrorCode(100_026, "人员状态字典不存在");
    ErrorCode AREA_NOT_EXISTS = new ErrorCode(100_027, "区域编码不存在");
    ErrorCode USER_NOT_EXISTS = new ErrorCode(100_028, "系统用户不存在");
}