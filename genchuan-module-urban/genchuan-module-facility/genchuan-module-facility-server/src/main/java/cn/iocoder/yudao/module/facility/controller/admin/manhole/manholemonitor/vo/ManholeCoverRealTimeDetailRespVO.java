package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo;

import cn.iocoder.yudao.module.facility.controller.admin.sysdevice.vo.DeviceInfoVO;
import lombok.Data;

@Data
public class ManholeCoverRealTimeDetailRespVO {
    private String coverId;         // 窨井盖唯一ID
    private String coverCode;       // 窨井盖编码
    private String coverName;       // 井盖名称
    private String areaId;          // 所属区域ID
    private String areaName;        // 所属区域名称
    private String address;         // 详细地址
    private String longitude;       // 经度
    private String latitude;        // 纬度
    private String coverType;       // 井盖类型
    private Integer coverStatus;    // 井盖状态（1-正常，2-位移，3-倾斜，4-异常）
    private String coverStatusName; // 井盖状态名称
    private RealTimeDataVO realTimeData; // 最新实时指标
    private DeviceInfoVO deviceInfo;     // 监测设备信息
    private String chainHash;       // 区块链存证哈希（示例值，可根据实际存储补充）
    private String chainQueryUrl;   // 区块链核验地址（示例值）
    private String tenantId;        // 租户ID
}
