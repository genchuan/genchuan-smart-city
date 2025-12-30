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
    ErrorCode UNIT_GRID_DIV_NOT_EXISTS = new ErrorCode(100_104, "单元网格划分不存在");
    ErrorCode EVAL_GRID_DIV_NOT_EXISTS = new ErrorCode(100_105, "评价网格划分不存在");
    ErrorCode MNG_GRID_DIV_NOT_EXISTS = new ErrorCode(100_106, "管理网格划分不存在");
    ErrorCode EXT_GRID_DIV_NOT_EXISTS = new ErrorCode(100_107, "扩展网格划分不存在");
    ErrorCode AREA_NOT_EXISTS = new ErrorCode(100_108, "统一行政区划配置表（树形结构）不存在");
    ErrorCode AREA_EXISTS_CHILDREN = new ErrorCode(100_109, "存在子节点，无法删除");
    ErrorCode AREA_PARENT_IS_SELF = new ErrorCode(100_110, "父节点不能是自己");
    ErrorCode AREA_PARENT_IS_CHILD = new ErrorCode(100_111, "父节点不能是自己的子节点");
    ErrorCode UNIT_GRID_DIV_BOUNDARY_INVALID = new ErrorCode(100_112, "单元网格边界校验不通过");
    ErrorCode MNG_GRID_DIV_VALIDATION_FAILED = new ErrorCode(100400100, "单元网格不相邻");
    ErrorCode UNIT_GRID_CODE_NOT_EXISTS = new ErrorCode(100_113, "单元网格编码不存在");
    ErrorCode EVAL_GRID_CODE_NOT_EXISTS = new ErrorCode(100_115, "评价网格编码不存在");
    // 单元网格编码相关错误码
    ErrorCode UNIT_GRID_NOT_EXISTS = new ErrorCode(100501, "单元网格不存在");
    ErrorCode UNIT_GRID_CODE_SEQ_OVERFLOW = new ErrorCode(100503, "顺序码已满，无法生成新的编码");
    ErrorCode MNG_GRID_CODE_NOT_EXISTS = new ErrorCode(100_116, "管理网格编码不存在");
    // 管理网格编码错误码
    ErrorCode MNG_GRID_CODE_SEQ_OVERFLOW = new ErrorCode(1003002003, "顺序码溢出");
    ErrorCode EXT_GRID_CODE_NOT_EXISTS = new ErrorCode(100_117, "扩展网格编码不存在");
    ErrorCode GRID_CODE_ANNOTATE_NOT_EXISTS = new ErrorCode(100_118, "网格标识码注记配置不存在");
    ErrorCode GRID_DATA_QUAL_CK_NOT_EXISTS = new ErrorCode(100_119, "网格数据质量检查不存在");
    ErrorCode GRID_BOUNDARY_STYLE_NOT_EXISTS = new ErrorCode(100_120, "网格边界线样式配置不存在");
    ErrorCode EVAL_GRID_ATTR_NOT_EXISTS = new ErrorCode(100_121, "评价网格属性不存在");
    ErrorCode UNIT_GRID_ATTR_NOT_EXISTS = new ErrorCode(100_122, "单元网格属性不存在");
    ErrorCode MNG_GRID_ATTR_NOT_EXISTS = new ErrorCode(100_123, "管理网格属性不存在");
    ErrorCode GRID_SPATIAL_DATA_NOT_EXISTS = new ErrorCode(100_124, "网格空间数据不存在");
    ErrorCode EVAL_GRID_RPT_NOT_EXISTS = new ErrorCode(100_125, "评价网格统计不存在");
    ErrorCode UNIT_GRID_RPT_NOT_EXISTS = new ErrorCode(100_126, "单元网格统计不存在");
    ErrorCode MNG_GRID_RPT_NOT_EXISTS = new ErrorCode(100_127, "管理网格统计不存在");
    ErrorCode GRID_TOPIC_LAYER_NOT_EXISTS = new ErrorCode(100_128, "网格专题图层管理不存在");

    // ========== 事件类型管理 1002000000 ==========
    ErrorCode EVENT_TYPE_NOT_EXISTS = new ErrorCode(1002000000, "事件类型不存在");
    ErrorCode EVENT_TYPE_CODE_DUPLICATE = new ErrorCode(1002000001, "事件类型代码已存在");

    // ========== 国家政策信息 1003000000 ==========
    ErrorCode NATIONAL_POLICY_INFO_NOT_EXISTS = new ErrorCode(1003000000, "国家政策信息不存在");
    ErrorCode NATIONAL_POLICY_INFO_NO_DUPLICATE = new ErrorCode(1003000001, "政策编号已存在");

    // ========== 地方法规信息 1004000000 ==========
    ErrorCode LOCAL_REGULATION_INFO_NOT_EXISTS = new ErrorCode(1004000000, "地方法规信息不存在");
    ErrorCode LOCAL_REGULATION_INFO_NO_DUPLICATE = new ErrorCode(1004000001, "法规编号已存在");
    ErrorCode LOCAL_REGULATION_INFO_TIME_INVALID = new ErrorCode(1004000002, "失效时间不能早于生效时间");
    ErrorCode LOCAL_REGULATION_INFO_CANNOT_UPDATE = new ErrorCode(1004000003, "仅未生效状态的法规可以修改");
    ErrorCode LOCAL_REGULATION_INFO_CANNOT_DELETE = new ErrorCode(1004000004, "仅未生效状态的法规可以删除");

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

    // ========== 监测部件 1007000000 ==========
    ErrorCode COMPONENT_CATEGORY_NOT_EXISTS = new ErrorCode(1007000000, "监测部件分类不存在");
    ErrorCode MON_COMP_DATA_CFG_NOT_EXISTS = new ErrorCode(1007000001, "监测部件数据配置不存在");
    ErrorCode MON_COMP_INFO_NOT_EXISTS = new ErrorCode(1007000002, "监测部件信息不存在");
    ErrorCode MON_COMP_EXT_NOT_EXISTS = new ErrorCode(1007000003, "监测部件扩展配置不存在");
    ErrorCode MON_COMP_CAT_NOT_EXISTS = new ErrorCode(1007000004, "监测部件分类配置不存在");
    ErrorCode MON_COMP_CODE_RULE_NOT_EXISTS = new ErrorCode(1007000005, "监测部件标识码规则不存在");
    ErrorCode MON_COMP_RPT_NOT_EXISTS = new ErrorCode(1007000011, "监测部件统计报不存在");
    // ========== 监测事项 1007000006 ==========
    ErrorCode MON_EVT_CAT_NOT_EXISTS = new ErrorCode(1007000006, "监测事件分类配置不存在");
    ErrorCode MON_EVT_INFO_NOT_EXISTS = new ErrorCode(1007000007, "监测事件信息不存在");
    ErrorCode MON_EVT_EXT_NOT_EXISTS = new ErrorCode(1007000008, "扩展监测事件配置不存在");
    ErrorCode MON_EVT_DATA_CFG_NOT_EXISTS = new ErrorCode(1007000009, "监测事件数据配置不存在");
    ErrorCode MON_EVT_CODE_RULE_NOT_EXISTS = new ErrorCode(1007000010, "监测事件标识码规则不存在");
    ErrorCode MON_EVT_RPT_NOT_EXISTS = new ErrorCode(1007000012, "监测事件统计报不存在");
    ErrorCode EVENT_DISPOSITION_NOT_EXISTS = new ErrorCode(1007000013, "监测事件关联处置不存在");

    // ========== 管理部件 1008000000 ==========
    ErrorCode MANAGED_COMPONENT_MAJOR_CONFIG_NOT_EXISTS = new ErrorCode(1008000000, "管理部件大类配置表不存在");
    ErrorCode MANAGED_COMPONENT_MINOR_CONFIG_NOT_EXISTS = new ErrorCode(1008000001, "管理部件小类配置不存在");
    ErrorCode MANAGED_MAJOR_MINOR_REL_NOT_EXISTS = new ErrorCode(1008000002, "管理部件大类小类关联不存在");
    ErrorCode BIZ_MNG_COMP_NOT_EXISTS = new ErrorCode(1008000004, "管理部件信息管理不存在");
    ErrorCode BIZ_MNG_COMP_SPATIAL_NOT_EXISTS = new ErrorCode(1008000005, "管理部件空间数据不存在");
    ErrorCode REL_MNG_COMP_SYMBOL_NOT_EXISTS = new ErrorCode(1008000006, "管理部件图示关联不存在");
    ErrorCode BIZ_MNG_COMP_SYMBOL_LIB_NOT_EXISTS = new ErrorCode(1008000007, "管理部件图示符号库不存在");
    ErrorCode BIZ_MNG_COMP_EXT_NOT_EXISTS = new ErrorCode(1008000008, "管理部件扩展管理部件配置不存在");
    ErrorCode MNG_COMP_NOT_EXISTS = new ErrorCode(1008000009, "管理部件统计不存在");
    // ========== 管理事项 1009000000 ==========
    ErrorCode MANAGED_MATTER_MAJOR_NOT_EXISTS = new ErrorCode(100_105, "管理事项分类表（含大类和小类）不存在");
    ErrorCode BIZ_MNG_MATTER_MAJOR_NOT_EXISTS = new ErrorCode(100900000, "管理事项大类不存在");
    ErrorCode BIZ_MNG_MATTER_MINOR_NOT_EXISTS = new ErrorCode(100900002, "管理事项小类不存在");
    ErrorCode BIZ_MNG_MATTER_NOT_EXISTS = new ErrorCode(100900003, "管理事项信息不存在");
    ErrorCode REL_MNG_MATTER_MAJ_MIN_NOT_EXISTS = new ErrorCode(100900004, "管理事项大小类关联不存在");
    ErrorCode BIZ_MNG_MATTER_EXT_NOT_EXISTS = new ErrorCode(100900005, "管理事项扩展管理事项配置不存在");
    ErrorCode MNG_MATTER_NOT_EXISTS = new ErrorCode(100900006, "管理事项统计不存在");

    // ========== 预案库 1010000000 ==========
    ErrorCode EMER_PLAN_LIB_NOT_EXISTS = new ErrorCode(1010000001, "预案库不存在");
    ErrorCode EMER_PLAN_CAT_NOT_EXISTS = new ErrorCode(1010000002, "预案分类配置不存在");
    // ========== 风险预警信息列表 ==========
    ErrorCode WARNING_ALERT_LIST_TABLE_NOT_EXISTS = new ErrorCode(100_103, "预警告警列表不存在");
    ErrorCode ALARM_RULE_NOT_EXISTS = new ErrorCode(100_105, "预警告警规则配置不存在");
    ErrorCode ALARM_RULE_ALREADY_ENABLED = new ErrorCode(1003001001, "预警告警规则配置已启用");
    ErrorCode ALARM_RULE_ALREADY_DISABLED = new ErrorCode(1003001002, "预警告警规则配置已禁用");
    ErrorCode ALARM_RULE_DUPLICATE = new ErrorCode(1003001003, "同一对象范围和触发条件的规则已存在");
    ErrorCode ALARM_RULE_THRESHOLD_INVALID = new ErrorCode(1003001004, "预警阈值上限必须大于下限");
    ErrorCode ALARM_RULE_SCOPE_IMMUTABLE = new ErrorCode(1003001005, "关联对象范围不可修改");
    ErrorCode EARLY_WARN_REGION_NOT_EXISTS = new ErrorCode(1003001006, "按行政区划预警告警统计不存在");
    ErrorCode EARLY_WARN_DEVICE_NOT_EXISTS = new ErrorCode(100_106, "按设备分域预警告警统计不存在");
    ErrorCode EARLY_WARN_MON_EVT_NOT_EXISTS = new ErrorCode(100_107, "按监测事件预警告警统计不存在");
    ErrorCode EARLY_WARN_GRID_NOT_EXISTS = new ErrorCode(100_108, "按网格分域预警告警统计不存在");
    ErrorCode EARLY_WARN_ASSET_NOT_EXISTS = new ErrorCode(100_109, "按资产分域预警告警统计不存在");
    ErrorCode EARLY_WARN_APP_SCENE_NOT_EXISTS = new ErrorCode(100_110, "按应用场景预警告警统计不存在");
    ErrorCode EARLY_WARN_MON_COMP_NOT_EXISTS = new ErrorCode(100_111, "按监测部件预警告警统计不存在");
    ErrorCode EARLY_WARN_MATTER_NOT_EXISTS = new ErrorCode(100_112, "按管理事项预警告警统计不存在");
    ErrorCode EARLY_WARN_MNG_COMP_NOT_EXISTS = new ErrorCode(100_113, "按管理部件预警告警统计不存在");
    ErrorCode EARLY_WARN_DISPATCH_NOT_EXISTS = new ErrorCode(100_114, "预警告警派单不存在");
    ErrorCode EARLY_WARN_HANDLE_EVAL_NOT_EXISTS = new ErrorCode(100_115, "预警告警处置评估不存在");
    ErrorCode EARLY_WARN_HANDLE_TRACE_NOT_EXISTS = new ErrorCode(100_116, "预警告警处置跟踪不存在");
    ErrorCode EARLY_WARN_RELEASE_NOT_EXISTS = new ErrorCode(100_117, "预警告警解除不存在");
    ErrorCode EARLY_WARN_ARCH_NOT_EXISTS = new ErrorCode(100_118, "预警告警处置归档不存在");
    ErrorCode RELATED_RISK_IDENT_NOT_EXISTS = new ErrorCode(100_119, "关联风险识别不存在");
    ErrorCode RISK_PROPAGATE_CHAIN_NOT_EXISTS = new ErrorCode(100_120, "风险传播链不存在");
    ErrorCode RISK_SOURCE_ARCH_NOT_EXISTS = new ErrorCode(100_121, "风险源档案不存在");
    ErrorCode RISK_TRACE_PATH_NOT_EXISTS = new ErrorCode(100_121, "风险溯源路径不存在");
    ErrorCode EARLY_WARN_CUSTOM_CFG_NOT_EXISTS = new ErrorCode(100_122, "预警告警自定义统计配置不存在");
    ErrorCode EARLY_WARN_RISK_PRED_NOT_EXISTS = new ErrorCode(100_123, "预警告警风险趋势预测不存在");
    ErrorCode EARLY_WARN_TIME_NOT_EXISTS = new ErrorCode(100_124, "预警告警时间维度统计不存在");
    ErrorCode EARLY_WARN_AREA_NOT_EXISTS = new ErrorCode(100_125, "预警告警区域维度统计不存在");
    ErrorCode EARLY_WARN_TYPE_NOT_EXISTS = new ErrorCode(100_126, "预警告警类型维度统计不存在");
    ErrorCode EARLY_WARN_HANDLE_EFF_NOT_EXISTS = new ErrorCode(100_127, "预警告警处置效率统计不存在");
    ErrorCode EARLY_WARN_LEVEL_NOT_EXISTS = new ErrorCode(100_128, "预警告警等级维度统计不存在");




    //====================================资产管理模块=========================================================================
    // Asset 相关错误码
    ErrorCode ASSET_NOT_EXISTS = new ErrorCode(2001001, "资产不存在");

    // ========= 资产关联行政区划 200_221 ==========
    ErrorCode ASSET_AREA_NOT_EXISTS = new ErrorCode(200_221, "资产关联行政区划不存在");
    // ========== 资产关联网格 200_231 ==========
    ErrorCode ASSET_GRID_NOT_EXISTS = new ErrorCode(200_231, "资产关联网格不存在");
    // ========== 资产关联管理部件 200_241 ==========
    ErrorCode ASSET_MNG_COMP_NOT_EXISTS = new ErrorCode(200_241, "资产关联管理部件不存在");
    // ========== 资产关联应用场景 200_251 ==========
    ErrorCode ASSET_APP_SCENE_NOT_EXISTS = new ErrorCode(200_251, "资产关联应用场景不存在");
    // ========== 资产关联资产 200_261 ==========
    ErrorCode ASSET_ASSET_NOT_EXISTS = new ErrorCode(200_261, "资产关联资产不存在");
    //====================================资产数据管理模块=====================================================
    ErrorCode ASSET_SPATIAL_DATA_NOT_EXISTS = new ErrorCode(200_301, "资产空间数据不存在");
    ErrorCode ASSET_SERVER_ATTR_CFG_NOT_EXISTS = new ErrorCode(200_311, "资产服务端属性配置不存在");
    ErrorCode ASSET_CLIENT_ATTR_CFG_NOT_EXISTS = new ErrorCode(200_321, "资产客户端属性配置不存在");
    ErrorCode ASSET_SHARE_ATTR_CFG_NOT_EXISTS = new ErrorCode(200_331, "资产共享属性配置不存在");
    ErrorCode ASSET_DATA_QUAL_CK_NOT_EXISTS = new ErrorCode(200_341, "资产数据质量检查不存在");

    //====================================资产管理模块=========================================================================

    // ========== 资产分类规则配置 200_101 ==========
    ErrorCode ASSET_CAT_RULE_CFG_NOT_EXISTS = new ErrorCode(200_101, "资产分类规则配置不存在");


    // ========== 资产属性规则配置 200_111 ==========
    ErrorCode ASSET_ATTR_RULE_CFG_NOT_EXISTS = new ErrorCode(200_111, "资产属性规则配置不存在");


    // ========== 资产关联规则配置 200_121 ==========
    ErrorCode ASSET_REL_RULE_CFG_NOT_EXISTS = new ErrorCode(200_121, "资产关联规则配置不存在");


    //====================================资产统计报表模块=====================================================
    ErrorCode ASSET_STATUS_RPT_NOT_EXISTS = new ErrorCode(200_401, "资产状态统计报表不存在");
    ErrorCode ASSET_CAT_RPT_NOT_EXISTS = new ErrorCode(200_411, "资产分类统计报表不存在");

    //=====================================资产管理导入模块=====================================================

    ErrorCode ASSET_SPATIAL_DATA_IMPORT_LIST_IS_EMPTY = new ErrorCode(200_351, "导入资产空间数据列表不能为空");
    ErrorCode ASSET_CAT_MNG_IMPORT_LIST_IS_EMPTY = new ErrorCode(200_217, "导入资产分类管理列表不能为空");
    ErrorCode ASSET_CAT_MNG_NOT_EXISTS = new ErrorCode(200_601,"资产分类管理不存在");
    ErrorCode ASSET_SERVER_ATTR_CFG_IMPORT_LIST_IS_EMPTY = new ErrorCode(200_361, "导入资产服务端属性配置列表不能为空");
    ErrorCode ASSET_CLIENT_ATTR_CFG_IMPORT_LIST_IS_EMPTY = new ErrorCode(200_371, "导入资产客户端属性配置列表不能为空");
    ErrorCode ASSET_SHARE_ATTR_CFG_IMPORT_LIST_IS_EMPTY = new ErrorCode(200_381, "导入资产共享属性配置列表不能为空");
    ErrorCode ASSET_DATA_MNG_NOT_EXISTS = new ErrorCode(200_501, "资产数据管理不存在");
    ErrorCode EVT_WO_NOT_EXISTS = new ErrorCode(1002000002, "事件工单不存在");
    ErrorCode EVT_TYPE_CFG_NOT_EXISTS = new ErrorCode(1002000003, "事件类型配置不存在");
    ErrorCode EVT_DISPOSAL_TRACK_NOT_EXISTS = new ErrorCode(1002000004, "事件处置跟踪不存在");
    ErrorCode EVT_RPT_REG_NOT_EXISTS = new ErrorCode(1002000005, "事件接报登记不存在");
    ErrorCode EVT_CLASSIFY_ALLOCATE_NOT_EXISTS = new ErrorCode(1002000006, "事件分级分拨不存在");
    ErrorCode EVT_ARCH_NOT_EXISTS = new ErrorCode(1002000007, "事件办结归档不存在");
    ErrorCode EVT_ANALYSIS_NOT_EXISTS = new ErrorCode(1002000008, "事件研判分析不存在");

    // ========== 资源调度管理 TODO 补充编号 ==========
    ErrorCode EMER_VEHICLE_INFO_NOT_EXISTS = new ErrorCode(1011000000, "救援车辆信息不存在");
    ErrorCode EMER_MAT_INFO_NOT_EXISTS = new ErrorCode(1011000001, "应急物资信息不存在");
    ErrorCode EMER_DEVICE_INFO_NOT_EXISTS = new ErrorCode(1011000002, "救援设备信息不存在");
    ErrorCode EMER_TEAM_INFO_NOT_EXISTS = new ErrorCode(1011000003, "救援队伍信息不存在");
    ErrorCode EMER_MAT_CAT_NOT_EXISTS = new ErrorCode(1011000004, "应急物资分类配置不存在");
    ErrorCode EMER_TEAM_CAT_NOT_EXISTS  = new ErrorCode(1011000005, "救援队伍分类配置不存在");
    ErrorCode EMER_VEHICLE_CAT_NOT_EXISTS = new ErrorCode(1011000006, "救援车辆分类配置不存在");
    ErrorCode EMER_DEVICE_CAT_NOT_EXISTS = new ErrorCode(1011000007, "救援设备分类配置不存在");

    //=====================================设备管理模块---100_30-----100_40============================================
    ErrorCode DEVICE_INFO_NOT_EXISTS = new ErrorCode(100_300, "设备信息不存在");
    ErrorCode DEVICE_CAT_RULE_NOT_EXISTS = new ErrorCode(100_301, "设备分类规则配置不存在");
    ErrorCode DEVICE_ATTR_RULE_NOT_EXISTS = new ErrorCode(100_302, "设备属性规则配置不存在");
    ErrorCode DEVICE_REL_RULE_NOT_EXISTS = new ErrorCode(100_303, "设备关联规则配置不存在");
    ErrorCode DEVICE_CAT_MNG_NOT_EXISTS = new ErrorCode(100_304, "设备分类管理不存在");
    ErrorCode DEVICE_ASSET_NOT_EXISTS = new ErrorCode(100_305, "设备关联资产不存在");
    ErrorCode DEVICE_AREA_NOT_EXISTS = new ErrorCode(100_306, "设备关联行政区划不存在");
    ErrorCode DEVICE_DEVICE_NOT_EXISTS = new ErrorCode(100_307, "设备关联设备不存在");
    ErrorCode DEVICE_APP_SCENE_NOT_EXISTS = new ErrorCode(100_308, "设备关联应用场景不存在");
    ErrorCode DEVICE_GRID_NOT_EXISTS = new ErrorCode(100_309, "设备关联网格不存在");
    ErrorCode DEVICE_MON_COMP_NOT_EXISTS = new ErrorCode(100_310, "设备关联监测部件不存在");
    ErrorCode DEVICE_OPER_RPT_NOT_EXISTS = new ErrorCode(100_311, "设备运行状态统计不存在");
    ErrorCode DEVICE_CAT_RPT_NOT_EXISTS = new ErrorCode(100_312, "设备分类统计不存在");
    ErrorCode DEVICE_OFFLINE_ALERT_NOT_EXISTS = new ErrorCode(100_313, "设备离线预警不存在");
    ErrorCode DEVICE_REAL_TIME_STATUS_NOT_EXISTS = new ErrorCode(100_314, "设备实时状态不存在");
    ErrorCode DEVICE_SPATIAL_DATA_NOT_EXISTS = new ErrorCode(100_315, "设备空间数据不存在");
    ErrorCode DEVICE_SHARE_ATTR_NOT_EXISTS = new ErrorCode(100_316, "设备共享属性配置不存在");
    ErrorCode DEVICE_SERVER_ATTR_NOT_EXISTS = new ErrorCode(100_317, "设备服务器属性配置不存在");
    ErrorCode DEVICE_DATA_QUAL_CK_NOT_EXISTS = new ErrorCode(100_318, "设备数据质量检查不存在");
    ErrorCode DEVICE_CLIENT_ATTR_NOT_EXISTS = new ErrorCode(100_319, "设备客户端属性配置不存在");
    ErrorCode DEVICE_TELEMETRY_DATA_NOT_EXISTS = new ErrorCode(100_320, "设备遥测数据不存在");

    // ========== 协同联动管理 1012000000 ==========
    ErrorCode COOP_FLOW_CFG_NOT_EXISTS = new ErrorCode(1012000000, "联动流程配置不存在");
    ErrorCode COOP_COMMAND_NOT_EXISTS = new ErrorCode(1012000001, "联动指令不存在");
    ErrorCode COOP_UNIT_INFO_NOT_EXISTS = new ErrorCode(1012000002, "联动单位信息不存在");
    // ========== 指挥调度中心 1013000000 ==========
    ErrorCode CROSS_REGION_DISPATCH_NOT_EXISTS = new ErrorCode(1013000000, "跨域调度协调不存在");
    ErrorCode DISPATCH_COMMAND_NOT_EXISTS = new ErrorCode(1013000001, "调度指令不存在");
    ErrorCode DISPATCH_PLAN_NOT_EXISTS = new ErrorCode(1013000002, "调度方案不存在");
    ErrorCode DISPATCH_EFFECT_FEEDBACK_NOT_EXISTS = new ErrorCode(1013000003, "调度效果反馈不存在");
    ErrorCode TASK_OVERALL_MNG_NOT_EXISTS = new ErrorCode(1013000004, "任务统筹管理不存在");
    ErrorCode PLAN_LAUNCH_RESP_NOT_EXISTS = new ErrorCode(1013000005, "预案启动响应不存在");
    // ========== 复盘追溯管理 1014000000 ==========
    ErrorCode EVT_PROCESS_TRACE_NOT_EXISTS = new ErrorCode(1014000000, "事件过程回溯不存在");
    ErrorCode EXP_LIB_NOT_EXISTS = new ErrorCode(1014000001, "经验库不存在");
    ErrorCode DISPOSAL_RESULT_EVAL_NOT_EXISTS = new ErrorCode(1014000002, "处置结果评估不存在");
    // ========== 分域分析研判 1015000000 ==========
    ErrorCode ANALYSIS_GRID_NOT_EXISTS = new ErrorCode(1015000001, "按网格分域分析研判统计不存在");
    ErrorCode ANALYSIS_MNG_COMP_NOT_EXISTS = new ErrorCode(1015000002, "按管理部件分析研判统计不存在");
    ErrorCode ANALYSIS_ASSET_NOT_EXISTS = new ErrorCode(1015000003, "按资产分域分析研判统计不存在");
    ErrorCode ANALYSIS_APP_SCENE_NOT_EXISTS = new ErrorCode(1015000004, "按应用场景分析研判统计不存在");
    ErrorCode ANALYSIS_MON_EVT_NOT_EXISTS = new ErrorCode(1015000005, "按监测事件分析研判统计不存在");
    ErrorCode ANALYSIS_MON_COMP_NOT_EXISTS = new ErrorCode(1015000006, "按监测部件分析研判统计不存在");
    ErrorCode ANALYSIS_MNG_MATTER_NOT_EXISTS = new ErrorCode(1015000007, "按管理事项分析研判统计不存在");
    ErrorCode ANALYSIS_REGION_NOT_EXISTS = new ErrorCode(1015000008, "按行政区划分析研判统计不存在");
    ErrorCode ANALYSIS_DEVICE_NOT_EXISTS = new ErrorCode(1015000009, "按设备分域分析研判统计不存在");
    ErrorCode EMER_RES_STATUS_NOT_EXISTS = new ErrorCode(1015000009, "资源状态统计不存在");
    ErrorCode DECISION_SCHEME_NOT_EXISTS = new ErrorCode(1015000011, "决策方案生成不存在");
    ErrorCode SCHEME_SIM_COMPARE_NOT_EXISTS = new ErrorCode(1015000012, "方案模拟对比统计不存在");
    ErrorCode POL_COMPLIANCE_CK_NOT_EXISTS = new ErrorCode(1015000013, "政策合规校验不存在");
    ErrorCode RES_MATCH_SCHEDULE_NOT_EXISTS = new ErrorCode(1015000014, "资源匹配调度不存在");
    ErrorCode DECISION_EFFECT_EVAL_NOT_EXISTS = new ErrorCode(1015000015, "决策效果评估不存在");
    ErrorCode DECISION_OPT_SUGG_NOT_EXISTS = new ErrorCode(1015000016, "决策优化建议不存在");
    ErrorCode DECISION_EXEC_TRACK_NOT_EXISTS = new ErrorCode(1015000017, "决策执行跟踪不存在");
    ErrorCode TYPICAL_SCENARIO_CASE_NOT_EXISTS = new ErrorCode(1015000018, "典型场景案例库不存在");
    ErrorCode CUSTOM_RPT_CFG_NOT_EXISTS = new ErrorCode(1015000019, "自定义报表配置不存在");
    ErrorCode SCREEN_TEMPLATE_MNG_NOT_EXISTS = new ErrorCode(1015000020, "大屏模板管理不存在");

    //=====================================设备管理模块---100_40-----100_50============================================
    ErrorCode BASIC_MON_PT_NOT_EXISTS = new ErrorCode(100_401, "基础监测点位不存在");
    ErrorCode BASIC_MON_IDX_NOT_EXISTS = new ErrorCode(100_402, "基础监测指标不存在");
    ErrorCode BASIC_MON_EVT_NOT_EXISTS = new ErrorCode(100_403, "基础监测事件不存在");
    ErrorCode DOM_DIV_ADMIN_NOT_EXISTS = new ErrorCode(100_404, "行政区划分域不存在");
    ErrorCode DOM_DIV_GRID_NOT_EXISTS = new ErrorCode(100_405, "网格分域不存在");
    ErrorCode DOM_DIV_MNG_COMP_NOT_EXISTS = new ErrorCode(100_406, "管理部件分域不存在");
    ErrorCode DOM_DIV_MNG_MATTER_NOT_EXISTS = new ErrorCode(100_407, "管理事项分域不存在");
    ErrorCode DOM_DIV_MON_COMP_NOT_EXISTS = new ErrorCode(100_408, "监测部件分域不存在");
    ErrorCode DOM_DIV_MON_EVT_NOT_EXISTS = new ErrorCode(100_409, "监测事件分域不存在");
    ErrorCode DOM_DIV_APP_SCENE_NOT_EXISTS = new ErrorCode(100_410, "应用场景分域不存在");
    ErrorCode DOM_DIV_ASSET_NOT_EXISTS = new ErrorCode(100_411, "资产分域不存在");
    ErrorCode DOM_DIV_DEVICE_NOT_EXISTS = new ErrorCode(100_412, "设备分域不存在");
    ErrorCode BASIC_MON_PT_RPT_NOT_EXISTS = new ErrorCode(100_413, "基础监测点位报表不存在");
    ErrorCode BASIC_MON_EVT_RPT_NOT_EXISTS = new ErrorCode(100_414, "基础监测事件报表不存在");
    ErrorCode BASIC_MON_IDX_RPT_NOT_EXISTS = new ErrorCode(100_415, "基础监测指标报表不存在");
    ErrorCode DOM_ADMIN_MON_RPT_NOT_EXISTS = new ErrorCode(100_416, "行政区划分域监测报表不存在");
    ErrorCode DOM_GRID_MON_RPT_NOT_EXISTS = new ErrorCode(100_417, "网格分域监测报表不存在");
    ErrorCode DOM_MNG_MATTER_RPT_NOT_EXISTS = new ErrorCode(100_418, "管理事项分域监测报表不存在");
    ErrorCode DOM_MON_COMP_MON_RPT_NOT_EXISTS = new ErrorCode(100_419, "监测部件分域监测报表不存在");
    ErrorCode DOM_MON_EVT_MON_RPT_NOT_EXISTS = new ErrorCode(100_420, "监测事件分域监测报表不存在");
    ErrorCode DOM_MNG_COMP_MON_RPT_NOT_EXISTS = new ErrorCode(100_421, "管理部件分域监测报表不存在");
    ErrorCode DOM_ASSET_MON_RPT_NOT_EXISTS = new ErrorCode(100_422, "资产分域监测报表不存在");
    ErrorCode DOM_APP_SCENE_MON_RPT_NOT_EXISTS = new ErrorCode(100_423, "应用场景分域监测报表不存在");
    ErrorCode DOM_DEVICE_MON_RPT_NOT_EXISTS = new ErrorCode(100_424, "设备分域监测报表不存在");
    ErrorCode MON_PT_SUMMARY_RPT_NOT_EXISTS = new ErrorCode(100_425, "监测点位汇总报表不存在");
    ErrorCode TREND_ANALYSIS_RPT_NOT_EXISTS = new ErrorCode(100_426, "综合趋势分析报表不存在");
    ErrorCode KEY_IDX_SUMMARY_RPT_NOT_EXISTS = new ErrorCode(100_427, "关键指标汇总报表不存在");
    ErrorCode COMPARE_ANALYSIS_RPT_NOT_EXISTS = new ErrorCode(100_428, "综合对比分析报表不存在");

    // ========== 业务指导 10017000000 ==========
    ErrorCode NATIONAL_POL_NOT_EXISTS = new ErrorCode(1001700001, "国家政策信息不存在");
    ErrorCode LOCAL_REGULATION_NOT_EXISTS = new ErrorCode(1001700002, "地方法规信息不存在");
    ErrorCode INDUSTRY_STD_NOT_EXISTS = new ErrorCode(1001700003, "行业规范信息不存在");
    ErrorCode POL_INTERPRETATION_NOT_EXISTS = new ErrorCode(1001700004, "政策解读信息不存在");
    ErrorCode DOMESTIC_DYNAMIC_NOT_EXISTS = new ErrorCode(1001700005, "国内行业动态不存在");
    ErrorCode INTERNATIONAL_DYNAMIC_NOT_EXISTS = new ErrorCode(1001700006, "国际行业动态不存在");
    ErrorCode LOCAL_DYNAMIC_NOT_EXISTS = new ErrorCode(1001700007, "本地行业动态不存在");
    ErrorCode PROCESS_EXEC_STD_NOT_EXISTS = new ErrorCode(1001700008, "流程执行规范不存在");
    ErrorCode SPEC_BUSINESS_STD_NOT_EXISTS = new ErrorCode(1001700009, "专项业务标准不存在");
    ErrorCode FAQ_NOT_EXISTS = new ErrorCode(1001700010, "常见问题解答不存在");
    ErrorCode TRAIN_MAT_NOT_EXISTS = new ErrorCode(1001700011, "培训资料不存在");
    ErrorCode SPEC_BUSINESS_GUIDE_NOT_EXISTS = new ErrorCode(1001700012, "专项业务指南不存在");
    ErrorCode SYSTEM_FUNCTION_GUIDE_NOT_EXISTS = new ErrorCode(1001700013, "系统功能指南不存在");
    ErrorCode GENERAL_OPER_GUIDE_NOT_EXISTS = new ErrorCode(1001700014, "通用操作指南不存在");
    ErrorCode INDUSTRY_TYPICAL_EXP_NOT_EXISTS = new ErrorCode(1001700015, "行业典型经验不存在");
    ErrorCode CROSS_DOM_EXP_NOT_EXISTS = new ErrorCode(1001700016, "跨域经验交流不存在");
    ErrorCode LOCAL_LEVEL_EXP_NOT_EXISTS = new ErrorCode(1001700017, "本级经验分享不存在");
    // ========== 公众服务 10018000000 ==========
    ErrorCode PUBLIC_SVC_INFO_NOT_EXISTS = new ErrorCode(1001800001, "服务信息发布不存在");
    ErrorCode PUBLIC_POL_INTERPRET_NOT_EXISTS = new ErrorCode(1001800002, "政策解读发布不存在");
    ErrorCode PUBLIC_NOTICE_NOT_EXISTS = new ErrorCode(1001800003, "通知公告发布不存在");
    ErrorCode PUBLIC_SNAP_SHOT_NOT_EXISTS = new ErrorCode(1001800004, "随手拍数据不存在");
    ErrorCode PUBLIC_CONSULTATION_NOT_EXISTS = new ErrorCode(1001800005, "咨询建议不存在");
    ErrorCode PUBLIC_COMPLAIN_NOT_EXISTS = new ErrorCode(1001800006, "投诉举报不存在");
    ErrorCode PUBLIC_HOTLINE_LINK_NOT_EXISTS = new ErrorCode(1001800007, "热线对接不存在");
    ErrorCode PUBLIC_ONLINE_INTERVIEW_NOT_EXISTS = new ErrorCode(1001800008, "在线访谈不存在");
    ErrorCode PUBLIC_OPINION_COLLECT_NOT_EXISTS = new ErrorCode(1001800009, "民意征集不存在");
    ErrorCode PUBLIC_FEEDBACK_NOT_EXISTS = new ErrorCode(1001800010, "意见反馈不存在");
    ErrorCode PUBLIC_AI_SVC_NOT_EXISTS = new ErrorCode(1001800011, "智能客服知识库不存在");

    //=====================================监督检查模块---100_50-----100_60============================================
    ErrorCode INSPECT_PERSON_INFO_NOT_EXISTS = new ErrorCode(100_501, "巡查巡检人员信息不存在");
    ErrorCode INSPECT_ROUTE_INFO_NOT_EXISTS = new ErrorCode(100_502, "巡查巡检路线信息不存在");
    ErrorCode INSPECT_DEVICE_INFO_NOT_EXISTS = new ErrorCode(100_503, "巡查巡检设备信息不存在");
    ErrorCode INSPECT_STD_INFO_NOT_EXISTS = new ErrorCode(100_504, "巡查巡检标准信息不存在");
    ErrorCode INSPECT_DAILY_PLAN_NOT_EXISTS = new ErrorCode(100_505, "日常巡查计划信息不存在");
    ErrorCode INSPECT_EMER_PLAN_NOT_EXISTS = new ErrorCode(100_506, "应急巡查计划信息不存在");
    ErrorCode INSPECT_SPEC_PLAN_NOT_EXISTS = new ErrorCode(100_507, "专项巡查计划信息不存在");
    ErrorCode INSPECT_TASK_ALLOCATE_REC_NOT_EXISTS = new ErrorCode(100_508, "巡查巡检任务分配记录不存在");
    ErrorCode INSPECT_TASK_TRACK_NOT_EXISTS = new ErrorCode(100_509, "巡查巡检任务跟踪不存在");
    ErrorCode INSPECT_DAILY_EXEC_REC_NOT_EXISTS = new ErrorCode(100_510, "日常巡查执行记录不存在");
    ErrorCode INSPECT_SPEC_EXEC_REC_NOT_EXISTS = new ErrorCode(100_511, "专项巡查执行记录不存在");
    ErrorCode INSPECT_EMER_EXEC_REC_NOT_EXISTS = new ErrorCode(100_512, "应急巡查执行记录不存在");
    ErrorCode INSPECT_PROBLEM_RPT_NOT_EXISTS = new ErrorCode(100_513, "巡查巡检问题上报记录不存在");
    ErrorCode INSPECT_PROBLEM_LEVEL_REC_NOT_EXISTS = new ErrorCode(100_514, "巡查巡检问题分级记录不存在");
    ErrorCode INSPECT_RECTIFY_TASK_NOT_EXISTS = new ErrorCode(100_515, "巡查巡检整改任务派发不存在");
    ErrorCode INSPECT_RECTIFY_TRACK_NOT_EXISTS = new ErrorCode(100_516, "巡查巡检整改进度跟踪视图不存在");
    ErrorCode INSPECT_RECTIFY_ACCEPT_NOT_EXISTS = new ErrorCode(100_517, "巡查巡检整改结果验收不存在");
    ErrorCode INSPECT_SUPV_TASK_NOT_EXISTS = new ErrorCode(100_518, "巡查巡检重点督办任务不存在");
    ErrorCode INSPECT_SUPV_FEEDBACK_NOT_EXISTS = new ErrorCode(100_519, "巡查巡检督办结果反馈不存在");
    ErrorCode INSPECT_SUPV_TRACK_VIEW_NOT_EXISTS = new ErrorCode(100_520, "巡查巡检督办进度跟踪视图不存在");
    ErrorCode INSPECT_DAILY_REVIEW_NOT_EXISTS = new ErrorCode(100_521, "巡查巡检日常结果复核不存在");
    ErrorCode INSPECT_SPEC_REVIEW_NOT_EXISTS = new ErrorCode(100_522, "巡查巡检专项结果复核不存在");
    ErrorCode INSPECT_RECTIFY_REVIEW_NOT_EXISTS = new ErrorCode(100_523, "巡查巡检整改结果复核不存在");
    ErrorCode INSPECT_RESULT_PUBLIC_NOT_EXISTS = new ErrorCode(100_524, "巡查巡检结果公示不存在");
    ErrorCode INSPECT_RESULT_EVAL_NOT_EXISTS = new ErrorCode(100_525, "巡查巡检结果评估不存在");
    ErrorCode INSPECT_RESULT_FEEDBACK_NOT_EXISTS = new ErrorCode(100_526, "巡查巡检结果反馈不存在");
    ErrorCode INSPECT_REC_ARCH_NOT_EXISTS = new ErrorCode(100_527, "巡查巡检记录档案不存在");
    ErrorCode INSPECT_ACCEPT_ARCH_NOT_EXISTS = new ErrorCode(100_528, "巡查巡检验收档案不存在");
    ErrorCode INSPECT_RECTIFY_ARCH_NOT_EXISTS = new ErrorCode(100_529, "巡查巡检整改档案不存在");
    ErrorCode INSPECT_LEDGER_ARCH_NOT_EXISTS = new ErrorCode(100_530, "巡查巡检台账档案不存在");
    ErrorCode INSPECT_AREA_RPT_NOT_EXISTS = new ErrorCode(100_531, "巡查巡检区域统计不存在");
    ErrorCode INSPECT_EFFICACY_RPT_NOT_EXISTS = new ErrorCode(100_532, "巡查巡检效率统计不存在");
    ErrorCode INSPECT_TYPE_RPT_NOT_EXISTS = new ErrorCode(100_533, "巡查巡检类型统计不存在");
    ErrorCode INSPECT_CYCLE_RPT_NOT_EXISTS = new ErrorCode(100_534, "巡查巡检周期统计不存在");

    //=====================================综合评价模块---100_60-----100_70============================================
    ErrorCode EVAL_OBJECT_NOT_EXISTS = new ErrorCode(100_601, "评价对象管理不存在");
    ErrorCode EVAL_SUBJECT_NOT_EXISTS = new ErrorCode(100_602, "评价主体管理不存在");
    ErrorCode EVAL_IDX_CAT_NOT_EXISTS = new ErrorCode(100_603, "指标分类管理不存在");
    ErrorCode EVAL_IDX_ITEM_NOT_EXISTS = new ErrorCode(100_604, "指标项管理不存在");
    ErrorCode EVAL_IDX_WEIGHT_NOT_EXISTS = new ErrorCode(100_605, "指标权重管理不存在");
    ErrorCode EVAL_IDX_SYSTEM_NOT_EXISTS = new ErrorCode(100_606, "指标体系管理不存在");
    ErrorCode EVAL_RULE_CAT_NOT_EXISTS = new ErrorCode(100_607, "规则分类管理不存在");
    ErrorCode EVAL_RULE_ITEM_NOT_EXISTS = new ErrorCode(100_608, "规则项管理不存在");
    ErrorCode EVAL_VETO_RULE_NOT_EXISTS = new ErrorCode(100_609, "否决项规则管理不存在");
    ErrorCode EVAL_STD_CAT_NOT_EXISTS = new ErrorCode(100_610, "标准分类管理不存在");
    ErrorCode EVAL_STD_ITEM_NOT_EXISTS = new ErrorCode(100_611, "标准项管理不存在");
    ErrorCode EVAL_TASK_TEMPLATE_NOT_EXISTS = new ErrorCode(100_612, "任务模板管理不存在");
    ErrorCode EVAL_TASK_NOT_EXISTS = new ErrorCode(100_613, "任务管理不存在");
    ErrorCode EVAL_REAL_TIME_DATA_NOT_EXISTS = new ErrorCode(100_614, "实时监测数据接入不存在");
    ErrorCode EVAL_PLATFORM_RPT_DATA_NOT_EXISTS = new ErrorCode(100_615, "平台上报数据不存在");
    ErrorCode EVAL_QUESTIONNAIRE_NOT_EXISTS = new ErrorCode(100_616, "问卷调查管理不存在");
    ErrorCode EVAL_INSPECT_PLAN_NOT_EXISTS = new ErrorCode(100_617, "考察计划管理不存在");
    ErrorCode EVAL_INSPECT_REC_NOT_EXISTS = new ErrorCode(100_618, "考察记录数据不存在");
    ErrorCode EVAL_RESULT_AUDIT_NOT_EXISTS = new ErrorCode(100_619, "结果审核不存在");
    ErrorCode EVAL_RESULT_PUBLIC_NOT_EXISTS = new ErrorCode(100_620, "结果公示不存在");
    ErrorCode EVAL_RESULT_ARCH_NOT_EXISTS = new ErrorCode(100_621, "结果存档管理不存在");
    ErrorCode EVAL_APPEAL_NOT_EXISTS = new ErrorCode(100_622, "申诉管理不存在");
    ErrorCode EVAL_REVIEW_NOT_EXISTS = new ErrorCode(100_623, "复核办理不存在");
    ErrorCode EVAL_REVIEW_FEEDBACK_NOT_EXISTS = new ErrorCode(100_624, "复核结果反馈不存在");
    ErrorCode EVAL_RESULT_RPT_NOT_EXISTS = new ErrorCode(100_625, "结果报表管理不存在");
    ErrorCode EVAL_RESULT_ANALYSIS_NOT_EXISTS = new ErrorCode(100_626, "评价结果分析不存在");
    ErrorCode EVAL_RESULT_PUSH_NOT_EXISTS = new ErrorCode(100_627, "结果关联推送不存在");
    ErrorCode EVAL_RESULT_SYSTEM_DOCK_NOT_EXISTS = new ErrorCode(100_628, "结果系统对接不存在");
    ErrorCode EVAL_RPT_NOT_EXISTS = new ErrorCode(100_629, "评价报告不存在");
    ErrorCode EVAL_RPT_TEMPLATE_NOT_EXISTS = new ErrorCode(100_630, "报告模板配置不存在");
    ErrorCode EVAL_RPT_ARCH_NOT_EXISTS = new ErrorCode(100_631, "报告归档导出不存在");
    ErrorCode INSPECTION_STATISTICS_NOT_EXISTS = new ErrorCode(100_632, "环卫考核统计结果不存在");

//    应用场景分类
    ErrorCode APP_SCENE_CATEGORY_NOT_EXISTS = new ErrorCode(199_001, "应用场景分类配置不存在");

    // ========== 系统首页快捷菜单配置==========
    ErrorCode HOME_QUICK_MENU_NOT_EXISTS = new ErrorCode(199_002, "系统首页快捷菜单配置不存在");

    // ========== 资产配置 ==========
    ErrorCode ASSET_PROFILE_NOT_EXISTS = new ErrorCode(199_003, "资产配置信息不存在");
}
