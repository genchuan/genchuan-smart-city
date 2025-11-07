package cn.iocoder.yudao.module.industry.framework.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * StatCycleUtil 工具类
 *
 * 用途：将前端传入的 statCycle（YYYY / YYYYQn / YYYYMM）转换为起止日期，
 * 方便在 SQL 中按日期范围查询数据。
 *
 * 示例：
 *  - "202511" -> ["2025-11-01", "2025-12-01"]
 *  - "2025Q3" -> ["2025-07-01", "2025-10-01"]
 *  - "2025"   -> ["2025-01-01", "2026-01-01"]
 */
public class StatCycleUtil {
    /**
     * 判断： statCycle 非空判断
     *
     * @param statCycle 前端传入的统计周期
     * @return true 表示非空，false 表示为空
     */
    public static boolean isNotEmpty(String statCycle) {
        return statCycle != null && !statCycle.isEmpty();
    }

    /**
     * 解析 statCycle 并生成起止日期
     * 注意：统计周期区间采用左闭右开 [startDate, endDate)，例如输入 "202510"，返回的区间为 ["2025-10-01", "2025-11-01")，表示包含 10 月 1 日起至 10 月 31 日结束的数据。
     * @param statCycle 前端传入的统计周期，支持以下三种格式：
     *                  1. YYYYMM，例如 202511
     *                  2. YYYYQn，例如 2025Q3
     *                  3. YYYY，例如 2025
     * @return String[] 长度为 2 的数组：{startDate, endDate}，格式为 yyyy-MM-dd
     * @throws IllegalArgumentException 如果 statCycle 格式不符合要求
     * @throws RuntimeException         如果解析过程中出现异常
     */
    public static String[] parseStatCycle(String statCycle) {
        // 空值及格式检查
        if (statCycle == null || statCycle.isEmpty()) {
            throw new IllegalArgumentException("statCycle 不能为空");
        }
        if (!statCycle.matches("\\d{4}|\\d{6}|\\d{4}Q[1-4]")) {
            throw new IllegalArgumentException("statCycle 格式错误，必须为 YYYY / YYYYQn / YYYYMM");
        }

        String startDate;
        String endDate;

        try {
            if (statCycle.matches("\\d{6}")) { // YYYYMM 格式
                startDate = statCycle.substring(0, 4) + "-" + statCycle.substring(4, 6) + "-01";
                LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate end = start.plusMonths(1); // 当月结束是下个月的第一天
                endDate = end.toString();

            } else if (statCycle.matches("\\d{4}Q[1-4]")) { // YYYYQn 格式
                int year = Integer.parseInt(statCycle.substring(0, 4));
                int quarter = Integer.parseInt(statCycle.substring(5, 6));
                int startMonth = (quarter - 1) * 3 + 1; // 计算季度开始月份
                startDate = String.format("%04d-%02d-01", year, startMonth);
                LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate end = start.plusMonths(3); // 季度结束是三个月后第一天
                endDate = end.toString();

            } else if (statCycle.matches("\\d{4}")) { // YYYY 格式
                int year = Integer.parseInt(statCycle);
                startDate = String.format("%04d-01-01", year);
                LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate end = start.plusYears(1); // 年结束是下一年的第一天
                endDate = end.toString();

            } else {
                throw new IllegalArgumentException("statCycle 格式错误，必须为 YYYY / YYYYQn / YYYYMM");
            }
        } catch (Exception e) {
            // 捕获所有解析异常，并包装成 RuntimeException
            throw new RuntimeException("解析 statCycle 出错: " + statCycle, e);
        }

        // 返回起止日期数组
        String[] statCycleRange = new String[]{startDate, endDate};
        return statCycleRange;

    }
}
