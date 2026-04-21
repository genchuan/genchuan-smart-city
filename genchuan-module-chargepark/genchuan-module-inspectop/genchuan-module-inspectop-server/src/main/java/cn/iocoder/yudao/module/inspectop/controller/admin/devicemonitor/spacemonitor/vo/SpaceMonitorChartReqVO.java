package cn.iocoder.yudao.module.inspectop.controller.admin.devicemonitor.spacemonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "巡查巡检 - 车位状态监控 Request VO")
@Data
public class SpaceMonitorChartReqVO {

    @Schema(description = "场站ID")
    private Long stationId;

    @Schema(description = "时间范围",example = "时间范围参数需要符合yyyy-MM-dd'T'HH:mm:ss格式")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] timeRange;
}