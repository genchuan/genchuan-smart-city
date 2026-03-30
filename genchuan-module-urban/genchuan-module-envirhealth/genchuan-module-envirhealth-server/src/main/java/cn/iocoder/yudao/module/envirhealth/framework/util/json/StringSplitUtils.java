package cn.iocoder.yudao.module.envirhealth.framework.util.json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用字符串分割工具类
 * 适配逗号/分号等分隔符、JSON数组格式的字符串解析为列表，支持空值处理
 */
public class StringSplitUtils {
    // 匹配JSON数组的正则（如 [1,2,3]、["a","b"]）
    private static final Pattern JSON_ARRAY_PATTERN = Pattern.compile("^\\[(.+)\\]$");

    /**
     * 通用解析方法（默认分隔符：逗号）
     */
    public static List<String> splitToStringList(String originalStr) {
        return splitToStringList(originalStr, ",");
    }

    /**
     * 通用解析方法（支持自定义分隔符）
     */
    public static List<String> splitToStringList(String originalStr, String separator) {
        // 1. 空值/空字符串处理：直接返回空列表
        if (originalStr == null || originalStr.trim().isEmpty()) {
            return new ArrayList<>();
        }
        // 2. 处理JSON数组格式（如 [1,2,3]、["1","2"]）
        String handleStr = originalStr.trim();
        Matcher matcher = JSON_ARRAY_PATTERN.matcher(handleStr);
        if (matcher.find()) {
            // 提取数组内部内容（去掉 []），并去除引号
            handleStr = matcher.group(1).replaceAll("\"", "");
        }
        // 3. 按分隔符分割，转换为 ArrayList
        String[] splitArray = handleStr.split(separator);
        List<String> resultList = new ArrayList<>(Arrays.asList(splitArray));
        // 4. 过滤分割后可能的空字符串（如："1,,2" 或 "[1,,2]" 会分割出空元素）
        resultList.removeIf(String::isEmpty);
        return resultList;
    }

    /**
     * 重载：解析为Long类型列表（适配ID类参数）
     */
    public static List<Long> splitToLongList(String originalStr) {
        List<String> strList = splitToStringList(originalStr);
        List<Long> longList = new ArrayList<>();
        for (String str : strList) {
            try {
                longList.add(Long.valueOf(str.trim()));
            } catch (NumberFormatException e) {
                // 非数字ID忽略，或根据业务抛异常
                continue;
            }
        }
        return longList;
    }
}