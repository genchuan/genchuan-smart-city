package cn.iocoder.yudao.module.chargepark.carservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 * 决策分析-报表时间尺度
 *
 * 客户文档要求：日/周/月/季/半年/年 6 个尺度
 */
@Getter
@AllArgsConstructor
public enum ReportPeriodEnum {

    DAILY("日报"),
    WEEKLY("周报"),
    MONTHLY("月报"),
    QUARTERLY("季报"),
    SEMI_ANNUAL("半年报"),
    ANNUAL("年报");

    private final String label;

    /**
     * 计算指定基准日期所属时间窗口的起始时刻
     */
    public LocalDateTime startOf(LocalDate baseDate) {
        switch (this) {
            case DAILY:
                return baseDate.atStartOfDay();
            case WEEKLY:
                return baseDate.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY)).atStartOfDay();
            case MONTHLY:
                return baseDate.withDayOfMonth(1).atStartOfDay();
            case QUARTERLY:
                int qStartMonth = (baseDate.get(IsoFields.QUARTER_OF_YEAR) - 1) * 3 + 1;
                return LocalDate.of(baseDate.getYear(), qStartMonth, 1).atStartOfDay();
            case SEMI_ANNUAL:
                int hStartMonth = baseDate.getMonthValue() <= 6 ? 1 : 7;
                return LocalDate.of(baseDate.getYear(), hStartMonth, 1).atStartOfDay();
            case ANNUAL:
                return LocalDate.of(baseDate.getYear(), 1, 1).atStartOfDay();
            default:
                throw new IllegalStateException("未支持的尺度：" + this);
        }
    }

    /**
     * 计算上一周期的基准日期(用于环比对比)。
     * DAILY=减1天 / WEEKLY=减1周 / MONTHLY=减1月 / QUARTERLY=减3月 / SEMI_ANNUAL=减6月 / ANNUAL=减1年
     */
    public LocalDate previousPeriod(LocalDate baseDate) {
        switch (this) {
            case DAILY:        return baseDate.minusDays(1);
            case WEEKLY:       return baseDate.minusWeeks(1);
            case MONTHLY:      return baseDate.minusMonths(1);
            case QUARTERLY:    return baseDate.minusMonths(3);
            case SEMI_ANNUAL:  return baseDate.minusMonths(6);
            case ANNUAL:       return baseDate.minusYears(1);
            default:
                throw new IllegalStateException("未支持的尺度：" + this);
        }
    }

    /** 下一周期基准日期(用于遍历统计区间) */
    public LocalDate nextPeriod(LocalDate baseDate) {
        switch (this) {
            case DAILY:        return baseDate.plusDays(1);
            case WEEKLY:       return baseDate.plusWeeks(1);
            case MONTHLY:      return baseDate.plusMonths(1);
            case QUARTERLY:    return baseDate.plusMonths(3);
            case SEMI_ANNUAL:  return baseDate.plusMonths(6);
            case ANNUAL:       return baseDate.plusYears(1);
            default:
                throw new IllegalStateException("未支持的尺度：" + this);
        }
    }

    /** 格式化成客户文档的 statPeriod 字符串。例: MONTHLY→"2025-03" / WEEKLY→"2025-W15" / QUARTERLY→"2025-Q1" */
    public String formatStatPeriod(LocalDate baseDate) {
        switch (this) {
            case DAILY:
                return baseDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
            case WEEKLY:
                int week = baseDate.get(WeekFields.ISO.weekOfWeekBasedYear());
                int weekYear = baseDate.get(WeekFields.ISO.weekBasedYear());
                return String.format("%d-W%02d", weekYear, week);
            case MONTHLY:
                return baseDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));
            case QUARTERLY:
                return baseDate.getYear() + "-Q" + baseDate.get(IsoFields.QUARTER_OF_YEAR);
            case SEMI_ANNUAL:
                return baseDate.getYear() + "-H" + (baseDate.getMonthValue() <= 6 ? 1 : 2);
            case ANNUAL:
                return String.valueOf(baseDate.getYear());
            default:
                throw new IllegalStateException("未支持的尺度：" + this);
        }
    }

    /** 按客户文档 timeScale 汉字(日/周/月/季/半年/年)匹配枚举。容错匹配 reportType(日报/周报/...) */
    public static ReportPeriodEnum fromTimeScale(String s) {
        if (s == null) return MONTHLY; // 默认月度
        String x = s.trim();
        if (x.isEmpty()) return MONTHLY;
        if (x.startsWith("日")) return DAILY;
        if (x.startsWith("周")) return WEEKLY;
        if (x.startsWith("月")) return MONTHLY;
        if (x.startsWith("季")) return QUARTERLY;
        if (x.startsWith("半")) return SEMI_ANNUAL;
        if (x.startsWith("年")) return ANNUAL;
        return MONTHLY;
    }

    /**
     * 计算指定基准日期所属时间窗口的结束时刻（不含）
     */
    public LocalDateTime endOf(LocalDate baseDate) {
        switch (this) {
            case DAILY:
                return baseDate.plusDays(1).atStartOfDay();
            case WEEKLY:
                return baseDate.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY))
                        .plusWeeks(1).atStartOfDay();
            case MONTHLY:
                return baseDate.withDayOfMonth(1).plusMonths(1).atStartOfDay();
            case QUARTERLY:
                int qStartMonth = (baseDate.get(IsoFields.QUARTER_OF_YEAR) - 1) * 3 + 1;
                return LocalDate.of(baseDate.getYear(), qStartMonth, 1).plusMonths(3).atStartOfDay();
            case SEMI_ANNUAL:
                int hStartMonth = baseDate.getMonthValue() <= 6 ? 1 : 7;
                return LocalDate.of(baseDate.getYear(), hStartMonth, 1).plusMonths(6).atStartOfDay();
            case ANNUAL:
                return LocalDate.of(baseDate.getYear() + 1, 1, 1).atStartOfDay();
            default:
                throw new IllegalStateException("未支持的尺度：" + this);
        }
    }

}
