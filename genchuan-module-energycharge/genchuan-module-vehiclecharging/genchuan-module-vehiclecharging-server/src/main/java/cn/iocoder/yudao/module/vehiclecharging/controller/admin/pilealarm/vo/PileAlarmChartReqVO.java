package cn.iocoder.yudao.module.vehiclecharging.controller.admin.pilealarm.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Schema(description = "充电桩告警图表查询 Request VO")
public class PileAlarmChartReqVO {

    @Schema(description = "开始时间")
    private LocalDateTime startTime;

    @Schema(description = "结束时间")
    private LocalDateTime endTime;

    @Schema(description = "场站ID")
    private Long stationId;

}