package cn.iocoder.yudao.module.facility.controller.admin.road.roadmonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "道路监测卡片统计信息")
@Data
public class RoadMonitorCardVO {

    @Schema(description = "总监测路段数")
    private Long totalRoads;

    @Schema(description = "在线设备数")
    private Long onlineDevices;

    @Schema(description = "设备在线率 (%)")
    private Double deviceOnlineRate;

    @Schema(description = "超标指标数")
    private Long overThresholdCount;

    @Schema(description = "正常监测路段数")
    private Long normalRoads;
}
