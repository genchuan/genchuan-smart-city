package cn.iocoder.yudao.module.vehiclecharging.controller.admin.pile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "汽车充电 - 充电桩图表统计 Request VO")
@Data
public class PileChartReqVO {

    @Schema(description = "统计开始时间（时间戳）", example = "1774011986")
    private String startTime;

    @Schema(description = "统计结束时间（时间戳）", example = "1775011986")
    private String endTime;

    @Schema(description = "所属场站ID，不传则统计全部", example = "1")
    private Long stationId;
}
