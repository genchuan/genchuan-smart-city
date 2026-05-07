package cn.iocoder.yudao.module.vehiclepass.framework.util;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Map 值安全获取工具类
 * 用于从 Map 中安全获取各种类型的值，避免空指针异常
 *
 * @author Claude
 */
public class MapValueUtils {

    /**
     * 安全获取 Long 值
     *
     * @param map Map 对象
     * @param key 键
     * @return Long 值，如果不存在或为 null 则返回 0L
     */
    public static Long getLongValue(Map<String, Object> map, String key) {
        if (map == null || key == null) {
            return 0L;
        }
        Object value = map.get(key);
        if (value == null) {
            return 0L;
        }
        if (value instanceof Number) {
            return ((Number) value).longValue();
        }
        try {
            return Long.parseLong(value.toString());
        } catch (NumberFormatException e) {
            return 0L;
        }
    }

    /**
     * 安全获取 Integer 值
     *
     * @param map Map 对象
     * @param key 键
     * @return Integer 值，如果不存在或为 null 则返回 0
     */
    public static Integer getIntValue(Map<String, Object> map, String key) {
        if (map == null || key == null) {
            return 0;
        }
        Object value = map.get(key);
        if (value == null) {
            return 0;
        }
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        try {
            return Integer.parseInt(value.toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * 安全获取 Double 值
     *
     * @param map Map 对象
     * @param key 键
     * @return Double 值，如果不存在或为 null 则返回 0.0
     */
    public static Double getDoubleValue(Map<String, Object> map, String key) {
        if (map == null || key == null) {
            return 0.0;
        }
        Object value = map.get(key);
        if (value == null) {
            return 0.0;
        }
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        try {
            return Double.parseDouble(value.toString());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    /**
     * 安全获取 String 值
     *
     * @param map Map 对象
     * @param key 键
     * @return String 值，如果不存在或为 null 则返回空字符串
     */
    public static String getStringValue(Map<String, Object> map, String key) {
        if (map == null || key == null) {
            return "";
        }
        Object value = map.get(key);
        return value == null ? "" : value.toString();
    }

    /**
     * 安全获取 BigDecimal 值
     *
     * @param map Map 对象
     * @param key 键
     * @return BigDecimal 值，如果不存在或为 null 则返回 BigDecimal.ZERO
     */
    public static BigDecimal getBigDecimalValue(Map<String, Object> map, String key) {
        if (map == null || key == null) {
            return BigDecimal.ZERO;
        }
        Object value = map.get(key);
        if (value == null) {
            return BigDecimal.ZERO;
        }
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        }
        if (value instanceof Number) {
            return BigDecimal.valueOf(((Number) value).doubleValue());
        }
        try {
            return new BigDecimal(value.toString());
        } catch (NumberFormatException e) {
            return BigDecimal.ZERO;
        }
    }
}
