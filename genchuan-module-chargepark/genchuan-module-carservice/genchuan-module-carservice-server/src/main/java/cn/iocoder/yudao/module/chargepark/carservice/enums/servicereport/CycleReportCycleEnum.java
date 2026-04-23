package cn.iocoder.yudao.module.chargepark.carservice.enums.servicereport;

import cn.iocoder.yudao.module.chargepark.carservice.enums.ReportPeriodEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 周期报表的报表周期。
 *
 * 相比 ReportPeriodEnum 多了 CUSTOM(自定义报表)。
 * 日报/周报/月报/季报/半年报/年报 直接委托 ReportPeriodEnum 计算时间窗,
 * CUSTOM 需要调用方自己提供 start/end。
 */
@Getter
@AllArgsConstructor
public enum CycleReportCycleEnum {

    DAILY("日报", ReportPeriodEnum.DAILY),
    WEEKLY("周报", ReportPeriodEnum.WEEKLY),
    MONTHLY("月报", ReportPeriodEnum.MONTHLY),
    QUARTERLY("季报", ReportPeriodEnum.QUARTERLY),
    SEMI_ANNUAL("半年报", ReportPeriodEnum.SEMI_ANNUAL),
    ANNUAL("年报", ReportPeriodEnum.ANNUAL),
    CUSTOM("自定义报表", null);

    private final String label;
    private final ReportPeriodEnum periodEnum;

    /** 按 label 找枚举(例: "月报" -> MONTHLY),null/未知返回 MONTHLY 兜底 */
    public static CycleReportCycleEnum fromLabel(String label) {
        if (label == null || label.trim().isEmpty()) {
            return MONTHLY;
        }
        String s = label.trim();
        for (CycleReportCycleEnum e : values()) {
            if (e.label.equals(s) || e.name().equalsIgnoreCase(s)) {
                return e;
            }
        }
        // 兜底按"日/周/月/季/半/年/自"首字匹配
        switch (s.charAt(0)) {
            case '日': return DAILY;
            case '周': return WEEKLY;
            case '月': return MONTHLY;
            case '季': return QUARTERLY;
            case '半': return SEMI_ANNUAL;
            case '年': return ANNUAL;
            case '自': return CUSTOM;
            default:  return MONTHLY;
        }
    }

    /** 获取周期起点。CUSTOM 需由调用方保留入参 */
    public LocalDateTime startOf(LocalDate base) {
        if (periodEnum == null) {
            throw new IllegalStateException("CUSTOM 无固定起点,请由入参提供 statStartTime");
        }
        return periodEnum.startOf(base);
    }

    /** 获取周期终点(不含)。CUSTOM 同上 */
    public LocalDateTime endOf(LocalDate base) {
        if (periodEnum == null) {
            throw new IllegalStateException("CUSTOM 无固定终点,请由入参提供 statEndTime");
        }
        return periodEnum.endOf(base);
    }

    /** 上一周期基准日(用于环比)。CUSTOM 返回 null,由调用方自己决定如何对比 */
    public LocalDate previousPeriod(LocalDate base) {
        return periodEnum == null ? null : periodEnum.previousPeriod(base);
    }

    /** 格式化 statTime 字符串,例 MONTHLY → "2026-04";CUSTOM 由调用方拼 "[start~end]" */
    public String formatStatTime(LocalDate base) {
        return periodEnum == null ? "自定义" : periodEnum.formatStatPeriod(base);
    }

}
