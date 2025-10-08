package cn.iocoder.yudao.module.datacenter.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * System 错误码枚举类
 *
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

}
