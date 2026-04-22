package cn.iocoder.yudao.module.chargepark.marketop.framework.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * <p>时间工具类<p/>
 *
 * @author suntao
 * @date 2026/4/17
 */
public class DateUtils {
    // 常量：日期格式
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Long毫秒戳 转 标准日期字符串
     */
    public static String longToDateTime(long timestamp) {
        return LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(timestamp), ZoneId.systemDefault())
                .format(FORMATTER);
    }

    /**
     * Long秒戳 转 标准日期字符串
     */
    public static String secondToDateTime(long timestamp) {
        return longToDateTime(timestamp * 1000);
    }

    // 测试
    public static void main(String[] args) {
        System.out.println(longToDateTime(1735689600000L));
    }
}
