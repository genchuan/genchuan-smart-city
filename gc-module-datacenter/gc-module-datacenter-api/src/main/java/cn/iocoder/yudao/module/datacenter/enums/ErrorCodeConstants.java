package cn.iocoder.yudao.module.datacenter.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * System 错误码枚举类
 * system 系统，使用 1-002-000-000 段
 */
public interface ErrorCodeConstants {
    //datacenter
    //100_00
    // =============================================================================================================
    // ============================================== 地 理 编 码 管 理 =============================================
    // =============================================================================================================
    ErrorCode DEVICE_NOT_EXISTS = new ErrorCode(999_999, "找不到设备");
    ErrorCode GEOCODING_POI_NOT_EXISTS = new ErrorCode(100_001, "兴趣点数据管理不存在");
    ErrorCode GEOCODING_HOUSE_NOT_EXISTS = new ErrorCode(100_002, "门(楼)牌数据管理不存在");
    ErrorCode GEOCODING_LAYER_NOT_EXISTS = new ErrorCode(100_003, "图层代码配置不存在");
    ErrorCode GEOCODING_COORDINATE_NOT_EXISTS = new ErrorCode(100_004, "坐标系配置不存在");
    ErrorCode GEOCODING_BDGRID_NOT_EXISTS = new ErrorCode(100_005, "北斗网格位置码配置不存在");
    ErrorCode GEOCODING_ZONE_NOT_EXISTS = new ErrorCode(100_006, "地片与区片数据管理不存在");
    ErrorCode GEOCODING_STREET_NOT_EXISTS = new ErrorCode(100_007, "街巷数据管理不存在");
    ErrorCode GEOCODING_REGION_NOT_EXISTS = new ErrorCode(100_008, "区域数据管理不存在");
    ErrorCode GEOCODING_IDRULE_NOT_EXISTS = new ErrorCode(100_009, "标识码编码规则配置不存在");
    ErrorCode GEOCODING_SEGMENTRULE_NOT_EXISTS = new ErrorCode(100_010, "地理编码分段组合规则配置不存在");
    ErrorCode GEOCODING_QUALITY_NOT_EXISTS = new ErrorCode(100_011, "地理编码数据质量统计报表不存在");
    ErrorCode GEOCODING_STAT_NOT_EXISTS = new ErrorCode(100_012, "基本地点数据统计报表不存在");
    ErrorCode GEOCODING_UPDATECYCLE_NOT_EXISTS = new ErrorCode(100_013, "数据更新周期配置不存在");
    ErrorCode GEOCODING_DENSITY_NOT_EXISTS = new ErrorCode(100_014, "数据采集密度配置不存在");
    ErrorCode GEOCODING_ACCURACY_NOT_EXISTS = new ErrorCode(100_015, "位置精度标准配置不存在");

    // =============================================================================================================
    // ============================================== 网 格 管 理 ===================================================
    // =============================================================================================================
    ErrorCode GRID_COUNTY_NOT_EXISTS = new ErrorCode(100_101, "县级及以上行政区划配置不存在");
    ErrorCode GRID_COMMUNITY_NOT_EXISTS = new ErrorCode(100_102, "社区（村）行政区划配置不存在");
    ErrorCode GRID_STREET_NOT_EXISTS = new ErrorCode(100_103, "街道（镇、乡）行政区划配置不存在");



    // ========== 事件类型管理 1002000000 ==========
    ErrorCode EVENT_TYPE_NOT_EXISTS = new ErrorCode(1002000000, "事件类型不存在");
    ErrorCode EVENT_TYPE_CODE_DUPLICATE = new ErrorCode(1002000001, "事件类型代码已存在");
    ErrorCode EVENT_TYPE_EXISTS_CHILDREN = new ErrorCode(1002000002, "存在子事件类型，无法删除");
    ErrorCode EVENT_TYPE_HAS_RELATED_EVENTS = new ErrorCode(1002000003, "存在关联的事件记录，无法删除");

    // ========== 国家政策信息 1003000000 ==========
    ErrorCode NATIONAL_POLICY_INFO_NOT_EXISTS = new ErrorCode(1003000000, "国家政策信息不存在");
    ErrorCode NATIONAL_POLICY_INFO_NO_DUPLICATE = new ErrorCode(1003000001, "政策编号已存在");
    ErrorCode NATIONAL_POLICY_INFO_TIME_INVALID = new ErrorCode(1003000002, "失效时间不能早于生效时间");
    ErrorCode NATIONAL_POLICY_INFO_CANNOT_UPDATE = new ErrorCode(1003000003, "仅未生效状态的政策可以修改");
    ErrorCode NATIONAL_POLICY_INFO_CANNOT_DELETE = new ErrorCode(1003000004, "仅未生效状态的政策可以删除");
    ErrorCode NATIONAL_POLICY_INFO_FILE_NOT_EXISTS = new ErrorCode(1003000005, "政策文件不存在");
    ErrorCode NATIONAL_POLICY_INFO_STATUS_INVALID = new ErrorCode(1003000006, "政策状态无效");

    // ========== 地方法规信息 1004000000 ==========
    ErrorCode LOCAL_REGULATION_INFO_NOT_EXISTS = new ErrorCode(1004000000, "地方法规信息不存在");
    ErrorCode LOCAL_REGULATION_INFO_NO_DUPLICATE = new ErrorCode(1004000001, "法规编号已存在");
    ErrorCode LOCAL_REGULATION_INFO_TIME_INVALID = new ErrorCode(1004000002, "失效时间不能早于生效时间");
    ErrorCode LOCAL_REGULATION_INFO_CANNOT_UPDATE = new ErrorCode(1004000003, "仅未生效状态的法规可以修改");
    ErrorCode LOCAL_REGULATION_INFO_CANNOT_DELETE = new ErrorCode(1004000004, "仅未生效状态的法规可以删除");
    ErrorCode LOCAL_REGULATION_INFO_FILE_NOT_EXISTS = new ErrorCode(1004000005, "法规文件不存在");
    ErrorCode LOCAL_REGULATION_INFO_STATUS_INVALID = new ErrorCode(1004000006, "法规状态无效");

    // ========== 行业规范信息 1005000000 ==========
    ErrorCode INDUSTRY_STANDARD_INFO_NOT_EXISTS = new ErrorCode(1004000000, "行业规范信息不存在");
    ErrorCode INDUSTRY_STANDARD_INFO_NO_DUPLICATE = new ErrorCode(1004000001, "规范编号已存在");
    ErrorCode INDUSTRY_STANDARD_INFO_FORMAT_INVALID = new ErrorCode(1004000002, "规范编号格式不正确");
    ErrorCode INDUSTRY_STANDARD_INFO_TIME_INVALID = new ErrorCode(1004000003, "实施时间不能早于当前时间");
    ErrorCode INDUSTRY_STANDARD_INFO_CANNOT_UPDATE = new ErrorCode(1004000004, "仅未实施状态的规范可以修改");
    ErrorCode INDUSTRY_STANDARD_INFO_CANNOT_DELETE = new ErrorCode(1004000005, "仅未实施状态的规范可以删除");

    // ========== 政策解读信息 1006000000 ==========
    ErrorCode POLICY_INTERPRETATION_INFO_NOT_EXISTS = new ErrorCode(1006000000, "政策解读信息不存在");
    ErrorCode POLICY_INTERPRETATION_INFO_TITLE_DUPLICATE = new ErrorCode(1006000001, "解读标题已存在");
    ErrorCode POLICY_INTERPRETATION_INFO_CANNOT_UPDATE = new ErrorCode(1006000002, "仅已发布状态的解读可以修改");
    ErrorCode POLICY_INTERPRETATION_INFO_CANNOT_DELETE = new ErrorCode(1006000003, "仅已下架状态的解读可以删除");
    ErrorCode POLICY_INTERPRETATION_INFO_POLICY_INVALID = new ErrorCode(1006000004, "关联政策无效");

    // ========== 监测部 1007000000 ==========
    ErrorCode COMPONENT_CATEGORY_NOT_EXISTS = new ErrorCode(1007000000, "监测部件分类不存在");

    // ========== 管理部件 1008000000 ==========
    ErrorCode MANAGED_COMPONENT_MAJOR_CONFIG_NOT_EXISTS = new ErrorCode(1008000000, "管理部件大类配置表不存在");
    ErrorCode MANAGED_COMPONENT_MINOR_CONFIG_NOT_EXISTS = new ErrorCode(1008000001, "管理部件小类配置不存在");
    ErrorCode MANAGED_MAJOR_MINOR_REL_NOT_EXISTS = new ErrorCode(1008000002, "管理部件大类小类关联不存在");
    ErrorCode MANAGED_COMPONENT_INFO_NOT_EXISTS = new ErrorCode(1008000003, "管理部件信息不存在");

    // ========== 管理事项分类表（含大类和小类） 100_105 ==========
    ErrorCode MANAGED_MATTER_MAJOR_NOT_EXISTS = new ErrorCode(100_105, "管理事项分类表（含大类和小类）不存在");

    // ========== 风险预警信息列表 ==========
    ErrorCode WARNING_ALERT_LIST_TABLE_NOT_EXISTS = new ErrorCode(100_103, "预警告警列表不存在");
    ErrorCode IMPORT_DATA_ERROR = new ErrorCode(100_104, "导入数据错误：{}");
    ErrorCode ALARM_RULE_NOT_EXISTS = new ErrorCode(100_105, "预警告警规则配置不存在");
    ErrorCode ALARM_RULE_ALREADY_ENABLED = new ErrorCode(1003001001, "预警告警规则配置已启用");
    ErrorCode ALARM_RULE_ALREADY_DISABLED = new ErrorCode(1003001002, "预警告警规则配置已禁用");
    ErrorCode ALARM_RULE_DUPLICATE = new ErrorCode(1003001003, "同一对象范围和触发条件的规则已存在");
    ErrorCode ALARM_RULE_THRESHOLD_INVALID = new ErrorCode(1003001004, "预警阈值上限必须大于下限");
    ErrorCode ALARM_RULE_SCOPE_IMMUTABLE = new ErrorCode(1003001005, "关联对象范围不可修改");

    //====================================资产管理模块=========================================================================

    // ========== 资产分类规则配置 200_101 ==========
    ErrorCode ASSET_CATEGORY_RULE_NOT_EXISTS = new ErrorCode(200_101, "资产分类规则配置不存在");
    ErrorCode ASSET_CATEGORY_RULE_EXITS_CHILDREN = new ErrorCode(200_102, "存在存在子资产分类规则配置，无法删除");
    ErrorCode ASSET_CATEGORY_RULE_PARENT_NOT_EXITS = new ErrorCode(200_103,"父级资产分类规则配置不存在");
    ErrorCode ASSET_CATEGORY_RULE_PARENT_ERROR = new ErrorCode(200_104, "不能设置自己为父资产分类规则配置");
    ErrorCode ASSET_CATEGORY_RULE_NAME_DUPLICATE = new ErrorCode(200_105, "已经存在该名字的资产分类规则配置");
    ErrorCode ASSET_CATEGORY_RULE_PARENT_IS_CHILD = new ErrorCode(200_106, "不能设置自己的子AssetCategoryRule为父AssetCategoryRule");

    // ========== 资产属性规则配置 200_111 ==========
    ErrorCode ASSET_ATTR_RULE_NOT_EXISTS = new ErrorCode(200_111, "资产属性规则配置不存在");
    ErrorCode ASSET_ATTR_RULE_EXITS_CHILDREN = new ErrorCode(200_112, "存在存在子资产属性规则配置，无法删除");
    ErrorCode ASSET_ATTR_RULE_PARENT_NOT_EXITS = new ErrorCode(200_113,"父级资产属性规则配置不存在");
    ErrorCode ASSET_ATTR_RULE_PARENT_ERROR = new ErrorCode(200_114, "不能设置自己为父资产属性规则配置");
    ErrorCode ASSET_ATTR_RULE_NAME_DUPLICATE = new ErrorCode(200_115, "已经存在该名字的资产属性规则配置");
    ErrorCode ASSET_ATTR_RULE_PARENT_IS_CHILD = new ErrorCode(200_116, "不能设置自己的子AssetAttrRule为父AssetAttrRule");

    // ========== 资产关联规则配置 200_121 ==========
    ErrorCode ASSET_REL_RULE_NOT_EXISTS = new ErrorCode(200_121, "资产关联规则配置不存在");
    ErrorCode ASSET_REL_RULE_EXITS_CHILDREN = new ErrorCode(200_122, "存在存在子资产关联规则配置，无法删除");
    ErrorCode ASSET_REL_RULE_PARENT_NOT_EXITS = new ErrorCode(200_123,"父级资产关联规则配置不存在");
    ErrorCode ASSET_REL_RULE_PARENT_ERROR = new ErrorCode(200_124, "不能设置自己为父资产关联规则配置");
    ErrorCode ASSET_REL_RULE_NAME_DUPLICATE = new ErrorCode(200_125, "已经存在该名字的资产关联规则配置");
    ErrorCode ASSET_REL_RULE_PARENT_IS_CHILD = new ErrorCode(200_126, "不能设置自己的子AssetRelRule为父AssetRelRule");


}
