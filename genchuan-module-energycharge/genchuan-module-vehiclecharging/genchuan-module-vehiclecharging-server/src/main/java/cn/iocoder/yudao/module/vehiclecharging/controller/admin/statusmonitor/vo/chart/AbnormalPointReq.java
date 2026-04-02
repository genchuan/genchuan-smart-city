package cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.chart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AbnormalPointReq {
    @Schema(description = "地区", requiredMode = Schema.RequiredMode.REQUIRED, example = "CP20250301001")
    private String areaName;

//    @Schema(description = "开始时间", example = "2025-03-29 08:00")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
//    private LocalDateTime startTime;
//
//    @Schema(description = "结束时间", example = "2025-03-29 10:00")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
//    private LocalDateTime endTime;
}
