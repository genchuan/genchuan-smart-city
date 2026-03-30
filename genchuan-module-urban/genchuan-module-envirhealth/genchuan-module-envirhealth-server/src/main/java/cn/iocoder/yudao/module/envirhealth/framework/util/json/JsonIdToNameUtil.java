package cn.iocoder.yudao.module.envirhealth.framework.util.json;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 万能工具：JSON数组ID 自动转 名称字符串
 */
public class JsonIdToNameUtil {

    // ===================== 1. 从列表提取所有ID =====================
    public static <T> Set<String> collectIds(List<T> list, Function<T, String> jsonFunc) {
        Set<String> idSet = CollUtil.newHashSet();
        for (T item : list) {
            String json = jsonFunc.apply(item);
            if (StrUtil.isBlank(json)) continue;
            try {
                List<String> ids = JSON.parseArray(json, String.class);
                idSet.addAll(ids);
            } catch (Exception ignored) {}
        }
        return idSet;
    }

    // ===================== 2. 通用批量查询 → 转Map =====================
    public static <ID, ENTITY> Map<ID, String> toMap(
            List<ENTITY> list,
            Function<ENTITY, ID> idFunc,
            Function<ENTITY, String> nameFunc
    ) {
        if (CollUtil.isEmpty(list)) return Map.of();
        return list.stream().collect(Collectors.toMap(idFunc, nameFunc));
    }

    // ===================== 3. 回填名称到列表 =====================
    public static <T> void fillNames(
            List<T> list,
            Function<T, String> jsonFunc,
            Function<T, Map<String, String>> nameMapFunc,
            NameSetter<T> setter
    ) {
        for (T item : list) {
            String json = jsonFunc.apply(item);
            Map<String, String> nameMap = nameMapFunc.apply(item);

            if (StrUtil.isBlank(json)) {
                setter.setName(item, "");
                continue;
            }
            try {
                List<String> ids = JSON.parseArray(json, String.class);
                String nameStr = ids.stream()
                        .map(id -> nameMap.getOrDefault(id, ""))
                        .filter(StrUtil::isNotBlank)
                        .collect(Collectors.joining(","));
                setter.setName(item, nameStr);
            } catch (Exception e) {
                setter.setName(item, "");
            }
        }
    }

    // 简化版回填（最常用）
    public static <T> void fillNames(
            List<T> list,
            Function<T, String> getJson,
            NameSetter<T> setNameStr,
            Map<String, String> nameMap
    ) {
        fillNames(list, getJson, t -> nameMap, setNameStr);
    }

    @FunctionalInterface
    public interface NameSetter<T> {
        void setName(T entity, String nameStr);
    }
}