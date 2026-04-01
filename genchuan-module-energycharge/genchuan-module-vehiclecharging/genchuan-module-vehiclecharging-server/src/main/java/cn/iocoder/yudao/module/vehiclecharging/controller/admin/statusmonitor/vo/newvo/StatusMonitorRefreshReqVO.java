package cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.newvo;

import lombok.Data;

@Data
public class StatusMonitorRefreshReqVO {
    private Long stationId;
    private Long pileId;
    private Long tenantId;
}
