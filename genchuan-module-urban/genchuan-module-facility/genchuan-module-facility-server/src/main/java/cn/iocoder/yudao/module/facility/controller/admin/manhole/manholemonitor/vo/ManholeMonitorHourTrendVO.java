package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 窨井盖监测小时趋势 Response VO")
@Data
public class ManholeMonitorHourTrendVO {

    @Schema(description = "小时时间点", example = "2024-03-19 10:00:00")
    private String hourTime;

    @Schema(description = "平均倾斜角度", example = "12.35")
    private BigDecimal avgTiltAngle;

    @Schema(description = "最大倾斜角度", example = "15.20")
    private BigDecimal maxTiltAngle;

    @Schema(description = "最小倾斜角度", example = "9.80")
    private BigDecimal minTiltAngle;

    @Schema(description = "平均振动值", example = "5.67")
    private BigDecimal avgVibration;

    @Schema(description = "最大振动值", example = "8.90")
    private BigDecimal maxVibration;

    @Schema(description = "最小振动值", example = "2.30")
    private BigDecimal minVibration;

    @Schema(description = "记录数", example = "5")
    private Integer recordCount;

}