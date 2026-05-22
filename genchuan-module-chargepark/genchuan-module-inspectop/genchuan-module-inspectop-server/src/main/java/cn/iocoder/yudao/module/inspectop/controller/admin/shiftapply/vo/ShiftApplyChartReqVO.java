package cn.iocoder.yudao.module.inspectop.controller.admin.shiftapply.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "巡查巡检 - 换班申请统计图表 Request VO")
@Data
public class ShiftApplyChartReqVO {

    @Schema(description = "时间范围，格式：[开始时间, 结束时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] timeRange;

    /**
     * 获取开始时间，如果没有提供则默认改为2000年1月1日
     */
    public LocalDateTime getStartTime() {
        if (timeRange != null && timeRange.length > 0 && timeRange[0] != null) {
            return timeRange[0];
        }
        // 默认改为2000年1月1日
        return LocalDate.of(2000, 1, 1).atStartOfDay();
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