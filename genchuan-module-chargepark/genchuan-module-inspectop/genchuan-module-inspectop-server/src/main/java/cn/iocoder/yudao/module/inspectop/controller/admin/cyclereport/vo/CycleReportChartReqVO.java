package cn.iocoder.yudao.module.inspectop.controller.admin.cyclereport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Schema(description = "巡查巡检 - 巡检运维报表图表查询 Request VO")
@Data
public class CycleReportChartReqVO {

    @Schema(description = "报表周期（日报/周报/月报/季报/半年报/年报/自定义报表）", example = "月报")
    private String reportCycle;

    @Schema(description = "所属场站 ID", example = "1001")
    private Long stationId;

    @Schema(description = "统计开始时间，格式 yyyy-MM-dd HH:mm:ss", example = "2026-01-01 00:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime statTimeStart;

    @Schema(description = "统计结束时间，格式 yyyy-MM-dd HH:mm:ss", example = "2026-01-31 23:59:59")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime statTimeEnd;

    /**
     * 获取开始时间（如果为空则返回默认值：当前年第一天）
     */
    public LocalDateTime getStatTimeStartOrDefault() {
        if (this.statTimeStart != null) {
            return this.statTimeStart;
        }
        // 默认返回当月第一天
        LocalDate firstDayOfMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfYear());
        return firstDayOfMonth.atStartOfDay();
    }

    /**
     * 获取结束时间（如果为空则返回默认值：当前时间）
     */
    public LocalDateTime getStatTimeEndOrDefault() {
        if (this.statTimeEnd != null) {
            return this.statTimeEnd;
        }
        // 默认返回当前时间
        return LocalDateTime.now();
    }
}