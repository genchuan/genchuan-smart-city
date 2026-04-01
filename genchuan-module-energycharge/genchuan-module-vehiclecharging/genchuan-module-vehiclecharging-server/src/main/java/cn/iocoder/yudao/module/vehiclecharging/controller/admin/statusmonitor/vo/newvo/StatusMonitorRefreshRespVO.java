package cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.newvo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class StatusMonitorRefreshRespVO {
    private Long id;
    private Long pileId;
    private String pileModel;
    private String monitorType;
    private BigDecimal monitorValue;
    private String monitorStatus;
    private LocalDateTime monitorTime;
}
