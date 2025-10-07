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

    // ========== 事件类型管理 1002000000 ==========
    ErrorCode EVENT_TYPE_NOT_EXISTS = new ErrorCode(1002000000, "事件类型不存在");
    ErrorCode EVENT_TYPE_CODE_DUPLICATE = new ErrorCode(1002000001, "事件类型代码已存在");
    ErrorCode EVENT_TYPE_EXISTS_CHILDREN = new ErrorCode(1002000002, "存在子事件类型，无法删除");
    ErrorCode EVENT_TYPE_HAS_RELATED_EVENTS = new ErrorCode(1002000003, "存在关联的事件记录，无法删除");
}
