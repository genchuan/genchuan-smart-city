package cn.iocoder.yudao.module.facility.controller.admin.sysdevice.vo;

import lombok.Data;

// 响应VO - 设备信息子对象
@Data
public class DeviceInfoVO {
    private String deviceId;        // 设备ID
    private String deviceModel;     // 设备型号
    private Integer batteryLevel;   // 设备电量
    private String signalStrength;  // 信号强度
    private Integer onlineStatus;   // 在线状态（0-离线，1-在线）
}
