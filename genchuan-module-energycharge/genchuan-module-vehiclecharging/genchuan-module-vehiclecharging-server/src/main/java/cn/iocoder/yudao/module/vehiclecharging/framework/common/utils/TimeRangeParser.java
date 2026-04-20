package cn.iocoder.yudao.module.vehiclecharging.framework.common.utils;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * 时间范围解析工具类
 * 支持格式：开始~结束，自动推断粒度（年/月/日）
 *
 * @author 亘川智城
 */
@Slf4j
public class TimeRangeParser {

    /**
     * 解析时间范围字符串
     * @param timeRange 格式如 "2025-04-01~2025-04-30" 或 "2025-04~2025-04" 或 "2025~2025"
     * @return 解析结果，包含开始时间、结束时间和粒度；若解析失败返回 null
     */
    public static TimeRangeParsed parse(String timeRange) {
        if (!StringUtils.hasText(timeRange) || !timeRange.contains("~")) {
            return null;
        }
        String[] parts = timeRange.split("~");
        if (parts.length != 2) {
            return null;
        }
        String startStr = parts[0].trim();
        String endStr = parts[1].trim();

        LocalDateTime start = parseDateTime(startStr);
        LocalDateTime end = parseDateTime(endStr);
        if (start == null || end == null) {
            return null;
        }

        // 推断粒度
        String granularity;
        // 年粒度：开始是年初，结束是年末，且同年
        if (start.getMonth() == Month.JANUARY && start.getDayOfMonth() == 1 && start.getHour() == 0 && start.getMinute() == 0 && start.getSecond() == 0 &&
                end.getMonth() == Month.DECEMBER && end.getDayOfMonth() == 31 && end.getHour() == 23 && end.getMinute() == 59 && end.getSecond() == 59 &&
                start.getYear() == end.getYear()) {
            granularity = "year";
        }
        // 月粒度：开始是月初，结束是月末，同年同月
        else if (start.getDayOfMonth() == 1 && start.getHour() == 0 && start.getMinute() == 0 && start.getSecond() == 0 &&
                start.getYear() == end.getYear() && start.getMonth() == end.getMonth() &&
                end.getDayOfMonth() == end.toLocalDate().lengthOfMonth() && end.getHour() == 23 && end.getMinute() == 59 && end.getSecond() == 59) {
            granularity = "month";
        }
        else {
            granularity = "day";
        }

        return new TimeRangeParsed(start, end, granularity);
    }

    /**
     * 解析日期时间字符串，支持格式：
     * - yyyy-MM-dd HH:mm:ss
     * - yyyy-MM-dd
     */
    private static LocalDateTime parseDateTime(String str) {
        if (!StringUtils.hasText(str)) {
            return null;
        }
        try {
            return LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (DateTimeParseException e) {
            try {
                LocalDate date = LocalDate.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return date.atStartOfDay();
            } catch (DateTimeParseException e2) {
                return null;
            }
        }
    }

    public static class ReportRange {
        private final LocalDateTime start;
        private final LocalDateTime end;
        private final String groupPattern; // 用于 SQL 的 DATE_FORMAT 格式，如 "%Y-%m-%d"
        public ReportRange(LocalDateTime start, LocalDateTime end, String groupPattern) {
            this.start = start;
            this.end = end;
            this.groupPattern = groupPattern;
        }
        public LocalDateTime getStart() { return start; }
        public LocalDateTime getEnd() { return end; }
        public String getGroupPattern() { return groupPattern; }
    }

    /**
     * 根据报表类型和时间范围解析起止时间和分组格式
     * @param reportType 日/周/月/季/半年/年
     * @param timeRange  对应格式：yyyy-MM-dd, yyyy-MM, yyyy, yyyy-Q1, yyyy-H1 等
     * @return ReportRange 对象，若解析失败返回 null
     */
    public static ReportRange parseReport(String reportType, String timeRange) {
        if (StrUtil.isBlank(reportType) || StrUtil.isBlank(timeRange)) {
            return null;
        }
        LocalDateTime start = null;
        LocalDateTime end = null;
        String groupPattern = null;
        try {
            switch (reportType) {
                case "日":
                    LocalDate day = LocalDate.parse(timeRange);
                    start = day.atStartOfDay();
                    end = day.atTime(23, 59, 59);
                    groupPattern = "%Y-%m-%d";
                    break;
                case "周":
                    // 周统计较复杂，暂不支持，抛出异常或返回 null
                    throw new IllegalArgumentException("周统计暂未实现");
                case "月":
                    YearMonth ym = YearMonth.parse(timeRange);
                    start = ym.atDay(1).atStartOfDay();
                    end = ym.atEndOfMonth().atTime(23, 59, 59);
                    groupPattern = "%Y-%m-%d";
                    break;
                case "季":
                    String[] qParts = timeRange.split("-Q");
                    int year = Integer.parseInt(qParts[0]);
                    int quarter = Integer.parseInt(qParts[1]);
                    int startMonth = (quarter - 1) * 3 + 1;
                    int endMonth = startMonth + 2;
                    start = LocalDate.of(year, startMonth, 1).atStartOfDay();
                    end = LocalDate.of(year, endMonth, 1).plusMonths(1).minusDays(1).atTime(23, 59, 59);
                    groupPattern = "%Y-%m-%d";
                    break;
                case "半年":
                    String[] hParts = timeRange.split("-H");
                    int halfYear = Integer.parseInt(hParts[0]);
                    int half = Integer.parseInt(hParts[1]);
                    if (half == 1) {
                        start = LocalDate.of(halfYear, 1, 1).atStartOfDay();
                        end = LocalDate.of(halfYear, 6, 30).atTime(23, 59, 59);
                    } else {
                        start = LocalDate.of(halfYear, 7, 1).atStartOfDay();
                        end = LocalDate.of(halfYear, 12, 31).atTime(23, 59, 59);
                    }
                    groupPattern = "%Y-%m-%d";
                    break;
                case "年":
                    int y = Integer.parseInt(timeRange);
                    start = LocalDate.of(y, 1, 1).atStartOfDay();
                    end = LocalDate.of(y, 12, 31).atTime(23, 59, 59);
                    groupPattern = "%Y-%m";
                    break;
                default:
                    return null;
            }
        } catch (Exception e) {
            log.error("解析报表时间范围失败, reportType={}, timeRange={}", reportType, timeRange, e);
            return null;
        }
        return new ReportRange(start, end, groupPattern);
    }

    /**
     * 时间范围解析结果
     */
    @Getter
    @RequiredArgsConstructor
    public static class TimeRangeParsed {
        private final LocalDateTime start;
        private final LocalDateTime end;
        private final String granularity; // "day", "month", "year"
    }
}