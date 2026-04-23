package cn.iocoder.yudao.module.inspectop.controller.admin.inspecttrack.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "巡查巡检 - 巡检轨迹图表统计 Request VO")
@Data
public class InspectTrackChartReqVO {

    @Schema(description = "时间范围", example = "['2024-01-01 00:00:00', '2024-01-31 23:59:59']")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] timeRange;
}