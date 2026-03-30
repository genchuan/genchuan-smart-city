package cn.iocoder.yudao.module.kitchen.framework.lxsutils.common.codeutils;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 通用“编号查询工具类”
 *
 * 功能：
 * 1. 通过 DO 自动解析表名
 * 2. 自动识别编号字段（按优先级）
 * 3. 查询唯一数据并返回 DO
 *
 * 特性：
 * - 支持手动字段名
 * - 支持缓存开关
 * - 支持清空缓存
 */
public class CodeQueryUtils {

    // JdbcTemplate（建议通过 Spring 注入）
    private static JdbcTemplate jdbcTemplate;

    public static void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        CodeQueryUtils.jdbcTemplate = jdbcTemplate;
    }

    // ================= 缓存相关 =================
    private static final Map<Class<?>, String> FIELD_CACHE = new ConcurrentHashMap<>();
    private static final Map<Class<?>, String> TABLE_CACHE = new ConcurrentHashMap<>();

    /**
     * 缓存开关，默认关闭
     */
    private static boolean cacheEnabled = false;

    public static void setCacheEnabled(boolean enabled) {
        cacheEnabled = enabled;
    }

    /**
     * 清空缓存
     */
    public static void clearCache() {
        FIELD_CACHE.clear();
        TABLE_CACHE.clear();
    }

    // ================= 查询入口 =================
    /**
     * 通用查询入口（自动查询版本）
     *
     * @param clazz DO类型
     * @param codeValue 编号值
     */
    public static <T> T queryByCode(Class<T> clazz, String codeValue){
        return queryByCode(clazz,codeValue,null);
    }

    /**
     * 通用查询入口
     *
     * @param clazz DO类型
     * @param codeValue 编号值
     * @param codeName 手动编号字段名，可空，表示自动识别
     */
    public static <T> T queryByCode(Class<T> clazz, String codeValue, String codeName) {

        if (codeValue == null || codeValue.isEmpty()) {
            throw exception("编号不能为空");
        }

        if (jdbcTemplate == null) {
            throw exception("JdbcTemplate 未初始化");
        }

        // 1. 获取表名
        String tableName = resolveTableName(clazz);

        // 2. 获取字段
        String fieldName = resolveCodeField(clazz, codeName);

        // 3. 执行查询
        String sql = "SELECT * FROM " + tableName + " WHERE " + fieldName + " = ?";

        List<T> list = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(clazz),
                codeValue
        );

        // 4. 强校验
        if (list.isEmpty()) {
            throw exception("未查询到数据：table=" + tableName + ", code=" + codeValue);
        }

        if (list.size() > 1) {
            throw exception("编号不唯一：table=" + tableName + ", code=" + codeValue);
        }

        return list.get(0);
    }

    // ================= 辅助方法 =================

    /**
     * 解析表名（支持缓存，可根据开关禁用）
     */
    private static String resolveTableName(Class<?> clazz) {
        if (cacheEnabled) {
            return TABLE_CACHE.computeIfAbsent(clazz, CodeQueryUtils::parseTableName);
        } else {
            return parseTableName(clazz);
        }
    }

    private static String parseTableName(Class<?> clazz) {
        TableName tableName = clazz.getAnnotation(TableName.class);
        if (tableName == null || tableName.value().isEmpty()) {
            throw exception("未找到 @TableName 注解：" + clazz.getSimpleName());
        }
        return tableName.value();
    }

    /**
     * 解析编号字段（支持缓存 + 手动指定 + 优先级）
     *
     * 优先级：
     * 1. 手动指定字段名（非空直接使用）
     * 2. @CodeField
     * 3. 精确搜索 DO 字段 uniCode
     * 4. *_code（仅一个）
     * 5. *_no（仅一个）
     */
    private static String resolveCodeField(Class<?> clazz, String manualFieldName) {
        if (manualFieldName != null && !manualFieldName.isEmpty()) {
            return manualFieldName;
        }

        if (cacheEnabled) {
            return FIELD_CACHE.computeIfAbsent(clazz, CodeQueryUtils::parseCodeField);
        } else {
            return parseCodeField(clazz);
        }
    }

    private static String parseCodeField(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields(); // 只扫描当前类字段

        // ================== 1. @CodeField ==================
        List<Field> annoFields = new ArrayList<>();
        for (Field field : fields) {
            if (field.isAnnotationPresent(CodeField.class)) {
                annoFields.add(field);
            }
        }
        if (annoFields.size() == 1) {
            return annoFields.get(0).getName();
        }
        if (annoFields.size() > 1) {
            throw exception("@CodeField 标记字段不唯一：" + clazz.getSimpleName());
        }

        // ================== 2. 精确 uniCode ==================
        for (Field field : fields) {
            if ("uniCode".equalsIgnoreCase(field.getName())) {
                return field.getName();
            }
        }

        // ================== 3. *_code ==================
        List<Field> codeFields = new ArrayList<>();
        for (Field field : fields) {
            if (field.getName().toLowerCase().endsWith("code")) {
                codeFields.add(field);
            }
        }
        if (codeFields.size() == 1) {
            return codeFields.get(0).getName();
        }
        if (codeFields.size() > 1) {
            throw exception("存在多个 *_code 字段，无法自动识别：" + clazz.getSimpleName());
        }

        // ================== 4. *_no ==================
        List<Field> noFields = new ArrayList<>();
        for (Field field : fields) {
            if (field.getName().toLowerCase().endsWith("no")) {
                noFields.add(field);
            }
        }
        if (noFields.size() == 1) {
            return noFields.get(0).getName();
        }
        if (noFields.size() > 1) {
            throw exception("存在多个 *_no 字段，无法自动识别：" + clazz.getSimpleName());
        }

        throw exception("未找到编号字段：" + clazz.getSimpleName());
    }
}
