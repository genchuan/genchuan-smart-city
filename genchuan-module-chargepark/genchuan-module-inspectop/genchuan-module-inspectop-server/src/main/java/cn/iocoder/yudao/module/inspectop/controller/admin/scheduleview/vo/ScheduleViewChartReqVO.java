package cn.iocoder.yudao.module.inspectop.controller.admin.scheduleview.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Schema(description = "巡查巡检 - 排班统计图表 Request VO")
@Data
public class ScheduleViewChartReqVO {

    @Schema(description = "月份，格式：yyyy-MM，默认为空（统计所有数据）")
    @DateTimeFormat(pattern = "yyyy-MM")
    private String month;

    /**
     * 获取开始日期（当月第一天），如果月份为空则返回null
     */
    public LocalDate getStartDate() {
        YearMonth yearMonth = getYearMonth();
        if (yearMonth != null) {
            return yearMonth.atDay(1);
        }
        return null;  // 返回null表示不限制开始日期
    }

    /**
     * 获取结束日期（当月最后一天），如果月份为空则返回null
     */
    public LocalDate getEndDate() {
        YearMonth yearMonth = getYearMonth();
        if (yearMonth != null) {
            return yearMonth.atEndOfMonth();
        }
        return null;  // 返回null表示不限制结束日期
    }

    /**
     * 获取年份月份对象，如果月份为空则返回null
     */
    private YearMonth getYearMonth() {
        if (month != null && !month.trim().isEmpty()) {
            try {
                return YearMonth.parse(month, DateTimeFormatter.ofPattern("yyyy-MM"));
            } catch (Exception e) {
                // 如果解析失败，返回null
                return null;
            }
        }
        return null;  // 返回null表示不限制月份
    }
}