package cn.iocoder.yudao.module.inspectop.controller.admin.handoverlog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Schema(description = "巡查巡检 - 交接日志统计图表 Request VO")
@Data
public class HandoverLogChartReqVO {

    @Schema(description = "开始时间，格式：yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @Schema(description = "结束时间，格式：yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @Schema(description = "月份，格式：yyyy-MM，优先级高于startTime/endTime")
    @DateTimeFormat(pattern = "yyyy-MM")
    private String month;

    /**
     * 获取开始时间
     * 优先级：month > startTime
     */
    public LocalDateTime getStartTime() {
        if (month != null && !month.trim().isEmpty()) {
            try {
                YearMonth yearMonth = YearMonth.parse(month, DateTimeFormatter.ofPattern("yyyy-MM"));
                return yearMonth.atDay(1).atStartOfDay();
            } catch (Exception e) {
                // 解析失败，返回null
            }
        }
        return startTime;
    }

    /**
     * 获取结束时间
     * 优先级：month > endTime
     */
    public LocalDateTime getEndTime() {
        if (month != null && !month.trim().isEmpty()) {
            try {
                YearMonth yearMonth = YearMonth.parse(month, DateTimeFormatter.ofPattern("yyyy-MM"));
                return yearMonth.atEndOfMonth().atTime(23, 59, 59);
            } catch (Exception e) {
                // 解析失败，返回null
            }
        }
        return endTime;
    }
}