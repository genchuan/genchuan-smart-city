package cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.newvo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StatusMonitorExportReqVO {
    private Long stationId;
    private Long pileId;
    private String monitorStatus;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String exportType;   // Excel / PDF
    private Long tenantId;
}
