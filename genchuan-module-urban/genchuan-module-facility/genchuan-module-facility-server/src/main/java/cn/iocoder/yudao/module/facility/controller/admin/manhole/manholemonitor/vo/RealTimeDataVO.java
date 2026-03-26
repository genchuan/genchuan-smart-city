package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo;

import lombok.Data;

// 响应VO - 实时数据子对象
@Data
public class RealTimeDataVO {
    private String tiltAngle;       // 倾斜角度（带单位）
    private String displacement;    // 位移距离（带单位）
    private String waterLevel;      // 井内水位（带单位）
    private Integer isOpen;         // 是否开启（0-否，1-是）
    private String collectTime;     // 采集时间
}
