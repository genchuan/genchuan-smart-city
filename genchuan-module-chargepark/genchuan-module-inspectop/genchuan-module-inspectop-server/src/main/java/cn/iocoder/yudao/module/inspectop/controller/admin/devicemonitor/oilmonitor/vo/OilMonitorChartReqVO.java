package cn.iocoder.yudao.module.inspectop.controller.admin.devicemonitor.oilmonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "巡查巡检 - 油车占位监控图表 Request VO")
@Data
public class OilMonitorChartReqVO {

    @Schema(description = "场站ID")
    private Long stationId;

    @Schema(description = "时间范围", example = "时间范围参数需要符合yyyy-MM-dd HH:mm:ss格式")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] timeRange;
}