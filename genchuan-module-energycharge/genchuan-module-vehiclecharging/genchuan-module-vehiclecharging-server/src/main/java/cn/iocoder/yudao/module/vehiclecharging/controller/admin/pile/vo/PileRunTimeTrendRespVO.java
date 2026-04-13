package cn.iocoder.yudao.module.vehiclecharging.controller.admin.pile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "汽车充电 - 充电桩运行时长趋势 Response VO")
@Data
public class PileRunTimeTrendRespVO {

    @Schema(description = "日期（yyyy-MM-dd）", example = "2025-03-01")
    private String time;

    @Schema(description = "当日总运行时长（小时）", example = "12560")
    private Integer runTime;

}
