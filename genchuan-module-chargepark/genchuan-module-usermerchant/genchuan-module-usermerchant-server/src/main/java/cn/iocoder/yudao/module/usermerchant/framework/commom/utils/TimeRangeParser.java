package cn.iocoder.yudao.module.usermerchant.framework.commom.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;

/**
 * 时间范围解析工具类
 * 支持：
 * - 绝对时间范围："2025-04-01~2025-04-30"、"2025-04~2025-04"、"2025~2025"
 * - 相对时间范围："last7days"、"last30days"、"lastMonth"、"lastYear"、"thisMonth"、"thisYear"
 *
 * @author 亘川智城
 */
public class TimeRangeParser {

    /**
     * 解析时间范围字符串
     * @param timeRange 格式如 "2025-04-01~2025-04-30" 或 "2025-04~2025-04" 或 "2025~2025" 或 "last7days" 等
     * @return 解析结果，包含开始时间、结束时间和粒度；若解析失败返回 null
     */
    public static TimeRangeParsed parse(String timeRange) {
        if (!StringUtils.hasText(timeRange)) {
            return null;
        }

        // 1. 先尝试解析相对时间范围
        TimeRangeParsed relativeParsed = parseRelativeTimeRange(timeRange);
        if (relativeParsed != null) {
            return relativeParsed;
        }

        // 2. 再尝试绝对时间范围（原有逻辑）
        if (!timeRange.contains("~")) {
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
        String granularity = inferGranularity(start, end);
        return new TimeRangeParsed(start, end, granularity);
    }

    /**
     * 解析相对时间范围
     */
    private static TimeRangeParsed parseRelativeTimeRange(String timeRange) {
        LocalDate today = LocalDate.now();
        LocalDateTime start = null;
        LocalDateTime end = null;
        String granularity = "day";

        switch (timeRange) {
            case "today":
                start = today.atStartOfDay();
                end = today.atTime(23, 59, 59);
                granularity = "day";
                break;
            case "yesterday":
                LocalDate yesterday = today.minusDays(1);
                start = yesterday.atStartOfDay();
                end = yesterday.atTime(23, 59, 59);
                granularity = "day";
                break;
            case "thisWeek":
                start = today.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY)).atStartOfDay();
                end = today.with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.SUNDAY)).atTime(23, 59, 59);
                granularity = "day";
                break;
            case "lastWeek":
                start = today.minusWeeks(1).with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY)).atStartOfDay();
                end = today.minusWeeks(1).with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.SUNDAY)).atTime(23, 59, 59);
                granularity = "day";
                break;
            case "thisMonth":
                start = today.withDayOfMonth(1).atStartOfDay();
                end = today.with(TemporalAdjusters.lastDayOfMonth()).atTime(23, 59, 59);
                granularity = "day";
                break;
            case "lastMonth":
                LocalDate firstDayLastMonth = today.minusMonths(1).withDayOfMonth(1);
                start = firstDayLastMonth.atStartOfDay();
                end = firstDayLastMonth.with(TemporalAdjusters.lastDayOfMonth()).atTime(23, 59, 59);
                granularity = "day";
                break;
            case "thisYear":
                start = today.withDayOfYear(1).atStartOfDay();
                end = today.with(TemporalAdjusters.lastDayOfYear()).atTime(23, 59, 59);
                granularity = "month";
                break;
            case "lastYear":
                LocalDate firstDayLastYear = today.minusYears(1).withDayOfYear(1);
                start = firstDayLastYear.atStartOfDay();
                end = firstDayLastYear.with(TemporalAdjusters.lastDayOfYear()).atTime(23, 59, 59);
                granularity = "month";
                break;
            default:
                // 匹配 "last{N}days"
                if (timeRange.matches("last\\d+days")) {
                    int days = Integer.parseInt(timeRange.substring(4, timeRange.length() - 4));
                    start = today.minusDays(days).atStartOfDay();
                    end = today.atTime(23, 59, 59);
                    granularity = "day";
                } else {
                    return null;
                }
                break;
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

    /**
     * 根据开始和结束时间推断粒度
     */
    private static String inferGranularity(LocalDateTime start, LocalDateTime end) {
        // 年粒度：开始是年初，结束是年末，且同年
        if (start.getMonth() == Month.JANUARY && start.getDayOfMonth() == 1 && start.getHour() == 0 && start.getMinute() == 0 && start.getSecond() == 0 &&
                end.getMonth() == Month.DECEMBER && end.getDayOfMonth() == 31 && end.getHour() == 23 && end.getMinute() == 59 && end.getSecond() == 59 &&
                start.getYear() == end.getYear()) {
            return "year";
        }
        // 月粒度：开始是月初，结束是月末，同年同月
        if (start.getDayOfMonth() == 1 && start.getHour() == 0 && start.getMinute() == 0 && start.getSecond() == 0 &&
                start.getYear() == end.getYear() && start.getMonth() == end.getMonth() &&
                end.getDayOfMonth() == end.toLocalDate().lengthOfMonth() && end.getHour() == 23 && end.getMinute() == 59 && end.getSecond() == 59) {
            return "month";
        }
        return "day";
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