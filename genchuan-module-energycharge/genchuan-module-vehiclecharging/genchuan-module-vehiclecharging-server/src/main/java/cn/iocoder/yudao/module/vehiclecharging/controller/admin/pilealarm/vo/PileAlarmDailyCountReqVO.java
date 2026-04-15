package cn.iocoder.yudao.module.vehiclecharging.controller.admin.pilealarm.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PileAlarmDailyCountReqVO {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long stationId;
}