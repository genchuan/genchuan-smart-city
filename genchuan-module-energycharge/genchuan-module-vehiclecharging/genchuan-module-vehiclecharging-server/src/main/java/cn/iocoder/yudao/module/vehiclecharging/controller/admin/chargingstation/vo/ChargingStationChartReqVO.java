package cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "充电站图表统计 Request VO")
public class ChargingStationChartReqVO {

    @Schema(description = "开始时间戳(秒)", example = "1774011986")
    private String startTime;

    @Schema(description = "结束时间戳(秒)", example = "1775011986")
    private String endTime;
}