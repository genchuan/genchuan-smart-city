package cn.iocoder.yudao.module.usermerchant.framework.commom.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * 时间范围解析工具类
 * 支持格式：开始~结束，自动推断粒度（年/月/日）
 *
 * @author 亘川智城
 */
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