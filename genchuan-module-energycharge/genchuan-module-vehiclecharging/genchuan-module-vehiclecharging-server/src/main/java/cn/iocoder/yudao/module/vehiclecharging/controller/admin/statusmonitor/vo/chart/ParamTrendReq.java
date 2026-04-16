package cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.chart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDateTime;

@Data
@Schema(description = "汽车充电 - 设备运行参数趋势请求 VO")
public class ParamTrendReq {

    @Schema(description = "设备编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "CP20250301001")
    private String deviceCode;

    @Schema(description = "开始时间", example = "2025-03-29 08:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startTime;

    @Schema(description = "结束时间", example = "2025-03-29 10:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;
}
