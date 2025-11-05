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
    ErrorCode MNG_GRID_DIV_CROSS_COMMUNITY = new ErrorCode(1003001002, "单元网格跨社区，无法集成");
    ErrorCode MNG_GRID_DIV_NOT_ADJACENT = new ErrorCode(1003001003, "单元网格不相邻，无法集成");
    ErrorCode MNG_GRID_DIV_UNIT_COUNT_INVALID = new ErrorCode(1003001004, "单元网格数量必须在1-5个之间");
    ErrorCode UNIT_GRID_CODE_NOT_EXISTS = new ErrorCode(100_113, "单元网格编码不存在");
    ErrorCode EVAL_GRID_CODE_NOT_EXISTS = new ErrorCode(100_115, "评价网格编码不存在");
    // 单元网格编码相关错误码
    ErrorCode UNIT_GRID_CODE_ALREADY_EXISTS = new ErrorCode(100500, "单元网格编码已存在");
    ErrorCode UNIT_GRID_NOT_EXISTS = new ErrorCode(100501, "单元网格不存在");
    ErrorCode AREA_FULL_CODE_INVALID = new ErrorCode(100502, "行政区划代码无效");
    ErrorCode UNIT_GRID_CODE_SEQ_OVERFLOW = new ErrorCode(100503, "顺序码已满，无法生成新的编码");
    ErrorCode MNG_GRID_CODE_NOT_EXISTS = new ErrorCode(100_116, "管理网格编码不存在");
    // 管理网格编码错误码
    ErrorCode MNG_GRID_NOT_EXISTS = new ErrorCode(1003002000, "管理网格不存在");
    ErrorCode MNG_GRID_CODE_ALREADY_EXISTS = new ErrorCode(1003002001, "管理网格编码已存在");
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
    ErrorCode IMPORT_DATA_ERROR = new ErrorCode(100_104, "导入数据错误：{}");
    ErrorCode ALARM_RULE_NOT_EXISTS = new ErrorCode(100_105, "预警告警规则配置不存在");
    ErrorCode ALARM_RULE_ALREADY_ENABLED = new ErrorCode(1003001001, "预警告警规则配置已启用");
    ErrorCode ALARM_RULE_ALREADY_DISABLED = new ErrorCode(1003001002, "预警告警规则配置已禁用");
    ErrorCode ALARM_RULE_DUPLICATE = new ErrorCode(1003001003, "同一对象范围和触发条件的规则已存在");
    ErrorCode ALARM_RULE_THRESHOLD_INVALID = new ErrorCode(1003001004, "预警阈值上限必须大于下限");
    ErrorCode ALARM_RULE_SCOPE_IMMUTABLE = new ErrorCode(1003001005, "关联对象范围不可修改");
    ErrorCode EARLY_WARN_REGION_NOT_EXISTS = new ErrorCode(1003001006, "按行政区划预警告警统计不存在");

    //====================================资产管理模块=========================================================================

    // ========== 资产分类管理 200_211 ==========
    ErrorCode ASSET_CAT_MNG_NOT_EXISTS = new ErrorCode(200_211, "资产分类管理不存在");
    ErrorCode ASSET_CAT_MNG_EXITS_CHILDREN = new ErrorCode(200_212, "存在存在子资产分类管理，无法删除");
    ErrorCode ASSET_CAT_MNG_PARENT_NOT_EXITS = new ErrorCode(200_213,"父级资产分类管理不存在");
    ErrorCode ASSET_CAT_MNG_PARENT_ERROR = new ErrorCode(200_214, "不能设置自己为父资产分类管理");
    ErrorCode ASSET_CAT_MNG_NAME_DUPLICATE = new ErrorCode(200_215, "已经存在该名字的资产分类管理");
    ErrorCode ASSET_CAT_MNG_PARENT_IS_CHILD = new ErrorCode(200_216, "不能设置自己的子AssetCatMng为父AssetCatMng");
    // ========= 资产关联行政区划 200_221 ==========
    ErrorCode ASSET_AREA_NOT_EXISTS = new ErrorCode(200_221, "资产关联行政区划不存在");
    ErrorCode ASSET_AREA_EXITS_CHILDREN = new ErrorCode(200_222, "存在存在子资产关联行政区划，无法删除");
    ErrorCode ASSET_AREA_PARENT_NOT_EXITS = new ErrorCode(200_223,"父级资产关联行政区划不存在");
    ErrorCode ASSET_AREA_PARENT_ERROR = new ErrorCode(200_224, "不能设置自己为父资产关联行政区划");
    ErrorCode ASSET_AREA_NAME_DUPLICATE = new ErrorCode(200_225, "已经存在该名字的资产关联行政区划");
    ErrorCode ASSET_AREA_PARENT_IS_CHILD = new ErrorCode(200_226, "不能设置自己的子AssetArea为父AssetArea");
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

    ErrorCode ASSET_SERVER_ATTR_CFG_IMPORT_LIST_IS_EMPTY = new ErrorCode(200_361, "导入资产服务端属性配置列表不能为空");
    ErrorCode ASSET_CLIENT_ATTR_CFG_IMPORT_LIST_IS_EMPTY = new ErrorCode(200_371, "导入资产客户端属性配置列表不能为空");
    ErrorCode ASSET_SHARE_ATTR_CFG_IMPORT_LIST_IS_EMPTY = new ErrorCode(200_381, "导入资产共享属性配置列表不能为空");
    ErrorCode ASSET_DATA_MNG_NOT_EXISTS = new ErrorCode(200_501, "资产数据管理不存在");
}
