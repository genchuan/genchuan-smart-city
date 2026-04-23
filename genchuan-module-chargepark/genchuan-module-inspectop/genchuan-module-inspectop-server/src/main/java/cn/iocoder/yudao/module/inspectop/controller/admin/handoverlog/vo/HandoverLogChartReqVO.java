package cn.iocoder.yudao.module.inspectop.controller.admin.handoverlog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "巡查巡检 - 交接日志统计图表 Request VO")
@Data
public class HandoverLogChartReqVO {

    @Schema(description = "时间范围，格式：[开始时间, 结束时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] timeRange;

    /**
     * 获取开始时间，如果没有提供则默认为当月第一天
     */
    public LocalDateTime getStartTime() {
        if (timeRange != null && timeRange.length > 0 && timeRange[0] != null) {
            return timeRange[0];
        }
        // 默认当月第一天
        return LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay();
    }

    /**
     * 获取结束时间，如果没有提供则默认为当前时间
     */
    public LocalDateTime getEndTime() {
        if (timeRange != null && timeRange.length > 1 && timeRange[1] != null) {
            return timeRange[1];
        }
        // 默认当前时间
        return LocalDateTime.now();
    }
}