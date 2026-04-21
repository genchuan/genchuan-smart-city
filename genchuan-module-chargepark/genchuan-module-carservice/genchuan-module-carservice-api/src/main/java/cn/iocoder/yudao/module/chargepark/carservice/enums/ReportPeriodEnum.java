package cn.iocoder.yudao.module.chargepark.carservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;

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
