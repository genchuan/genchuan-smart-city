package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 窨井盖监测统计 Response VO")
@Data
public class ManholeMonitorStatsRespVO {

    @Schema(description = "平均倾斜角度", example = "12.35")
    private BigDecimal avgTiltAngle;

    @Schema(description = "最大倾斜角度", example = "18.50")
    private BigDecimal maxTiltAngle;

    @Schema(description = "最小倾斜角度", example = "8.20")
    private BigDecimal minTiltAngle;

    @Schema(description = "平均振动值", example = "5.67")
    private BigDecimal avgVibration;

    @Schema(description = "最大振动值", example = "9.80")
    private BigDecimal maxVibration;

    @Schema(description = "最小振动值", example = "2.10")
    private BigDecimal minVibration;


}
