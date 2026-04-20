package cn.iocoder.yudao.module.vehiclecharging.framework.common.utils;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import com.alibaba.nacos.shaded.javax.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class NameQueryHelper {

    private static JdbcTemplate jdbcTemplate;

    /**
     * 为对象列表批量填充名称（例如根据 userId 填充用户昵称）
     * @param list         需要填充的对象列表
     * @param idGetter     从对象中获取 ID 的函数
     * @param nameSetter   将名称设置到对象的函数
     * @param tableName    名称来源的表名（需安全）
     * @param idField      名称来源表的 ID 字段名
     * @param nameField    名称来源表的名称字段名
     * @param <T>          对象类型
     */
    public static <T> void fillNamesByIds(List<T> list,
                                          Function<T, Long> idGetter,
                                          BiConsumer<T, String> nameSetter,
                                          String tableName,
                                          String idField,
                                          String nameField) {
        if (CollUtil.isEmpty(list)) return;
        Set<Long> ids = list.stream().map(idGetter).filter(Objects::nonNull).collect(Collectors.toSet());
        if (ids.isEmpty()) return;
        Map<Long, String> nameMap = getNamesByIds(tableName, idField, ids, nameField);
        for (T obj : list) {
            Long id = idGetter.apply(obj);
            if (id != null && nameMap.containsKey(id)) {
                nameSetter.accept(obj, nameMap.get(id));
            }
        }
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        NameQueryHelper.jdbcTemplate = jdbcTemplate;
    }

    private static Long getIdByName(String tableName, String nameField, String nameValue, String idField) {
        if (isBlank(tableName) || isBlank(nameField) || isBlank(nameValue) || isBlank(idField)) {
            return null;
        }
        String sql = String.format("SELECT %s FROM %s WHERE %s = ? AND deleted = 0 LIMIT 1",
                idField, tableName, nameField);
        try {
            return jdbcTemplate.queryForObject(sql, Long.class, nameValue);
        } catch (Exception e) {
            log.error("根据名称查询ID失败, table={}, field={}, value={}", tableName, nameField, nameValue, e);
            return null;
        }
    }

    @Nullable
    public static String getNamesById(String tableName, String idField, Long id, String nameField) {
        if (isBlank(tableName) || isBlank(idField) || id == null || isBlank(nameField)) {
            return null;
        }
        String sql = String.format("SELECT %s FROM %s WHERE %s = ? AND deleted = 0 LIMIT 1",
                nameField, tableName, idField);
        try {
            return jdbcTemplate.queryForObject(sql, String.class, id);
        } catch (Exception e) {
            log.error("根据ID查询名称失败, table={}, id={}", tableName, id, e);
            return null;
        }
    }

    public static Map<Long, String> getNamesByIds(String tableName, String idField, Collection<Long> ids, String nameField) {
        if (CollUtil.isEmpty(ids) || isBlank(tableName) || isBlank(idField) || isBlank(nameField)) {
            return Collections.emptyMap();
        }
        String placeholders = String.join(",", Collections.nCopies(ids.size(), "?"));
        String sql = String.format("SELECT %s, %s FROM %s WHERE %s IN (%s) AND deleted = 0",
                idField, nameField, tableName, idField, placeholders);
        try {
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, ids.toArray());
            Map<Long, String> result = new HashMap<>();
            for (Map<String, Object> row : rows) {
                Long id = ((Number) row.get(idField)).longValue();
                String name = (String) row.get(nameField);
                if (name != null) result.put(id, name);
            }
            return result;
        } catch (Exception e) {
            log.error("批量根据ID查询名称失败, table={}, ids={}", tableName, ids, e);
            return Collections.emptyMap();
        }
    }

    public static <T> PageResult<T> queryPageByName(String nameTable, String nameField, String nameValue,
                                                    String idField,
                                                    Function<Long, PageResult<T>> pageQueryExecutor,
                                                    BiConsumer<T, String> nameSetter) {
        if (nameValue == null || nameValue.trim().isEmpty()) {
            return new PageResult<>(Collections.emptyList(), 0L);
        }
        Long id = getIdByName(nameTable, nameField, nameValue, idField);
        if (id == null) {
            return new PageResult<>(Collections.emptyList(), 0L);
        }
        PageResult<T> pageResult = pageQueryExecutor.apply(id);
        if (CollUtil.isNotEmpty(pageResult.getList())) {
            pageResult.getList().forEach(item -> nameSetter.accept(item, nameValue));
        }
        return pageResult;
    }

    private static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }
}