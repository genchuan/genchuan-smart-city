package cn.iocoder.yudao.module.envirhealth.util.json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 通用字符串分割工具类
 * 适配逗号/分号等分隔符的字符串解析为列表，支持空值处理
 */
public class StringSplitUtils {
    /**
     * 通用解析方法（默认分隔符：逗号）
     * @param originalStr 原始分隔字符串（如："张三,李四" 或 null 或 ""）
     * @return 解析后的字符串列表（空值返回空列表，避免NullPointerException）
     */
    public static List<String> splitToStringList(String originalStr) {
        return splitToStringList(originalStr, ",");
    }

    /**
     * 通用解析方法（支持自定义分隔符）
     * @param originalStr 原始分隔字符串
     * @param separator 分隔符（如：","、";"、"|" 等）
     * @return 解析后的字符串列表
     */
    public static List<String> splitToStringList(String originalStr, String separator) {
        // 1. 空值/空字符串处理：直接返回空列表
        if (originalStr == null || originalStr.trim().isEmpty()) {
            return new ArrayList<>();
        }
        // 2. 按分隔符分割，转换为 ArrayList
        String[] splitArray = originalStr.trim().split(separator);
        List<String> resultList = new ArrayList<>(Arrays.asList(splitArray));
        // 3. 过滤分割后可能的空字符串（如："张三,,李四" 会分割出空元素）
        resultList.removeIf(String::isEmpty);
        return resultList;
    }
}