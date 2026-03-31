package cn.iocoder.yudao.module.envirhealth.framework.util.json;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONException;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * JSON数组操作工具类
 */
public class JsonArrayUtils {

    /**
     * 向JSON数组字符串中添加元素（去重）
     * @param jsonStr 原JSON数组字符串（如 "[1,2,3]" 或 null/空）
     * @param element 要添加的元素
     * @return 新的JSON数组字符串
     */
    public static String addElement(String jsonStr, Object element) {
        if (element == null) {
            return jsonStr;
        }
        JSONArray jsonArray = parseJsonArray(jsonStr);
        // 去重添加
        if (!jsonArray.contains(element)) {
            jsonArray.add(element);
        }
        return jsonArray.toJSONString();
    }

    /**
     * 从JSON数组字符串中移除元素
     * @param jsonStr 原JSON数组字符串（如 "[1,2,3]" 或 null/空）
     * @param element 要移除的元素
     * @return 新的JSON数组字符串
     */
    public static String removeElement(String jsonStr, Object element) {
        if (element == null) {
            return jsonStr;
        }
        JSONArray jsonArray = parseJsonArray(jsonStr);

        // 遍历删除，支持 String / Long / Integer 通用匹配
        String target = String.valueOf(element);
        jsonArray.removeIf(obj -> target.equals(String.valueOf(obj)));

        return jsonArray.toJSONString();
    }

    /**
     * 解析JSON数组字符串（兼容null/空/非数组格式）
     */
    private static JSONArray parseJsonArray(String jsonStr) {
        if (StringUtils.isBlank(jsonStr)) {
            return new JSONArray();
        }
        try {
            return JSONArray.parseArray(jsonStr);
        } catch (JSONException e) {
            // 非数组格式则返回空数组
            return new JSONArray();
        }
    }

    /**
     * 去重JSON数组
     */
    public static String distinctJsonArray(String jsonStr) {
        JSONArray jsonArray = parseJsonArray(jsonStr);
        Set<Object> set = new HashSet<>(jsonArray);
        return new JSONArray(set).toJSONString();
    }
}