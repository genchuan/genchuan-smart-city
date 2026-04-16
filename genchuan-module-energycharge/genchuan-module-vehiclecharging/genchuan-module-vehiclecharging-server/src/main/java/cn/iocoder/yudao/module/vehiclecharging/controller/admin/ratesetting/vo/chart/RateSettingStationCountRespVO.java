package cn.iocoder.yudao.module.vehiclecharging.controller.admin.ratesetting.vo.chart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "汽车充电 - 费率档次数量统计 Response VO")
public class RateSettingStationCountRespVO {

    @Schema(description = "场站id", example = "场站id")
    private Long stationId;
    @Schema(description = "场站名称", example = "场站名称")
    private String stationName;

    @Schema(description = "数量", example = "3")
    private Integer rateCount;
}
