package cn.iocoder.yudao.module.envirhealth.framework.util.json;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/3/17 14:25
 */
public class JsonFieldUtils {

    public static String emptyToEmptyArray(String value) {
        if (value == null || value.trim().isEmpty() || "[]".equals(value.trim())) {
            return "[]";
        }
        // 验证是否为有效的 JSON
        try {
            // 如果是 JSON 数组，确保格式正确
            if (value.trim().startsWith("[") && value.trim().endsWith("]")) {
                return value;
            }
            // 如果是其他格式，转换为数组
            return "[" + value + "]";
        } catch (Exception e) {
            return "[]";
        }
    }
}