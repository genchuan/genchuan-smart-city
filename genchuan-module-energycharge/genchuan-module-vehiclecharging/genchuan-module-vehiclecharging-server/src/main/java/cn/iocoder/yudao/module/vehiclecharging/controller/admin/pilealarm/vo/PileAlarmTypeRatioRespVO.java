package cn.iocoder.yudao.module.vehiclecharging.controller.admin.pilealarm.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description ="充电桩告警 - 类型占比统计 Response VO")
@Data
public class PileAlarmTypeRatioRespVO {

    @Schema(description = "故障类型名称", example = "硬件故障")
    private String name;

    @Schema(description = "告警数量", example = "60")
    private Integer value;

    @Schema(description = "占比（保留2位小数）", example = "0.50")
    private BigDecimal ratio;
}