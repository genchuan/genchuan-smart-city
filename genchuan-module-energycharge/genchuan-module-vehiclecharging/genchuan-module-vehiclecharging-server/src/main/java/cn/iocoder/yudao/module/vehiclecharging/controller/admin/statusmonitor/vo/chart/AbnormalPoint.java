package cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.chart;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AbnormalPoint {
    private Long id;              // 监测数据ID
    private String deviceName;    // 设备名称
    private BigDecimal lon;       // 经度(12位精度)
    private BigDecimal lat;       // 纬度(12位精度)
    private String alarmLevel;    // 告警等级枚举值
    private String alarmLevelName;// 告警等级名称
}
