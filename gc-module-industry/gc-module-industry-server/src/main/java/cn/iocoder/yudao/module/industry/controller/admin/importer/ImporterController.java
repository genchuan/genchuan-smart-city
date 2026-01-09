package cn.iocoder.yudao.module.industry.controller.admin.importer;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.industry.framework.util.lxs.importer.ImportUtils;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.IMPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "工具 - 导入")
@RestController
@RequestMapping("/industry/util-import")
@Validated
@Slf4j
public class ImporterController {
    @PostMapping("/import-excel-to-raw-list")
    @Operation(summary = "导入excel，转化为批量新增到数据库的原始数据列表")
    public <T> CommonResult<List<T>> importExcelToRawList(
            @RequestParam("file") MultipartFile file,
            @RequestParam("targetClassName") String targetClassName
    ) throws Exception {

        List<T> list= ImportUtils.importExcelAndReturnEntity(file,targetClassName);
        return CommonResult.success(list);
    }

    //(暂时不用)该部分代码已被整合入工具方法ImportUtils
    @PostMapping("/import-any")
    @Operation(summary = "(暂时不用)通用导入 Excel - 先收集字段信息")
    public <T> CommonResult<Map<String, Object>> importAny(
            @RequestParam("file") MultipartFile file,
            @RequestParam("targetClassName") String targetClassName
    ) throws Exception {

        ImportVO importVO=new ImportVO();
        System.out.println("cs2026-01-06 11:56:19:测试假如的ImportVO类型:"+ImportVO.class);
        System.out.println("cs2026-01-06 11:56:19:测试假如的importVO类型:"+importVO.getClass());
        System.out.println("cs2026-01-06 11:56:19:测试targetClassName类型:"+targetClassName);
        if (file.isEmpty()) {
            return CommonResult.success(Collections.emptyMap());
        }

        // 1. 读取 Excel 为 List<Map<Integer,Object>>
        List<Map<String, Object>> dataList = EasyExcel.read(file.getInputStream())
                .sheet()
                .headRowNumber(1) // 第一行为表头
                .doReadSync();

        // 1.2 调试输出
        System.out.println("=== Excel 原始数据调试 ===");
        System.out.println("总行数: " + dataList.size());
        for (int i = 0; i < dataList.size(); i++) {
            Map<String, Object> row = dataList.get(i);
            System.out.println("第 " + (i + 1) + " 行: " + row);
        }
        System.out.println("=== 调试结束 ===");
        // 1.2 收集 Excel Map 的字段名及类型
        List<Map<String, String>> excelFieldInfoList = new ArrayList<>();
        for (Map<String, Object> row : dataList) {
            Map<String, String> rowFieldInfo = new LinkedHashMap<>();
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                rowFieldInfo.put(String.valueOf(entry.getKey()), entry.getValue() == null ? "null" : entry.getValue().getClass().getSimpleName());
            }
            excelFieldInfoList.add(rowFieldInfo);
        }

        // 2. 获取目标实体类字段信息
        Class<?> targetClass = Class.forName(targetClassName);
        Map<String, String> entityFieldInfo = new LinkedHashMap<>();
        for (Field field : targetClass.getDeclaredFields()) {
            entityFieldInfo.put(field.getName(), field.getType().getSimpleName());
        }

        // 3. 输出调试
        System.out.println("=== Excel 字段信息 ===");
        for (int i = 0; i < excelFieldInfoList.size(); i++) {
            System.out.println("第 " + (i + 1) + " 行: " + excelFieldInfoList.get(i));
        }
        System.out.println("=== 实体类字段信息 ===");
        System.out.println(entityFieldInfo);


        //4.将Excel数据转化为 实体数据
        List<Object> entityList = new ArrayList<>();
        //列名序列（第一列默认是0）
        int colIndex=0;
        for (Map<String, Object> row : dataList) {
            Object obj = targetClass.getDeclaredConstructor().newInstance(); // 创建实例
            //每到一个新对象，列名都要重置到1
            colIndex=0;
            for (Field field : targetClass.getDeclaredFields()) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Class<?> fieldType = field.getType();

//                Object value = row.get(fieldName);
                Object value = row.get(colIndex);
                colIndex++;
                if (value != null) {
                    System.out.println("cs2026-01-06 11:47:10:value:"+value);
                    // 安全转换类型
                    try {
                        if (fieldType == String.class) {
                            field.set(obj, value.toString());
                        } else if (fieldType == Integer.class || fieldType == int.class) {
                            field.set(obj, Integer.parseInt(value.toString()));
                        } else if (fieldType == Long.class || fieldType == long.class) {
                            field.set(obj, Long.parseLong(value.toString()));
                        } else if (fieldType == Double.class || fieldType == double.class) {
                            field.set(obj, Double.parseDouble(value.toString()));
                        } else if (fieldType == BigDecimal.class) {
                            field.set(obj, new BigDecimal(value.toString()));
                        } else if (fieldType == LocalDateTime.class) {
//                            field.set(obj, LocalDateTime.parse(value.toString())); // 按需修改解析格式
                            try {
                                field.set(obj, parseLocalDateTime(value));
                            } catch (Exception e) {
                                throw new RuntimeException(
                                        "字段转换失败: " + field.getName()
                                                + ", 值: " + value
                                                + ", 类型: LocalDateTime",
                                        e
                                );
                            }
                        } else {
                            field.set(obj, value); // 其他类型直接塞
                        }
                    } catch (Exception e) {
                        System.err.println("字段转换失败: " + fieldName + ", 值: " + value + ", 类型: " + fieldType);
                        e.printStackTrace();
                    }
                }
            }
            entityList.add(obj);
        }


        System.out.println("=== 实体数据调试 ===");
        System.out.println("总行数: " + entityList.size());
        for (int i = 0; i < entityList.size(); i++) {
            Object row = entityList.get(i);
            System.out.println("第 " + (i + 1) + " 行: " + row);
            System.out.println("cs2026-01-06 11:53:03:实体类型为："+row.getClass());
        }
        System.out.println("=== 调试结束 ===");


        // 4. 返回信息给前端
        Map<String, Object> result = new HashMap<>();
        result.put("excelFieldInfo", excelFieldInfoList);
        result.put("entityFieldInfo", entityFieldInfo);
        return CommonResult.success(result);
    }
    private static final DateTimeFormatter[] DATE_TIME_FORMATTERS = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-M-d HH:mm"),
            DateTimeFormatter.ofPattern("yyyy/M/d HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy/M/d")
    };

    private LocalDateTime parseLocalDateTime(Object value) {
        if (value == null) {
            return null;
        }

        // EasyExcel 有时直接给 Date
        if (value instanceof Date) {
            return LocalDateTime.ofInstant(
                    ((Date) value).toInstant(),
                    ZoneId.systemDefault()
            );
        }

        String text = value.toString().trim();
        for (DateTimeFormatter formatter : DATE_TIME_FORMATTERS) {
            try {
                return LocalDateTime.parse(text, formatter);
            } catch (Exception ignored) {
                //放空，因为要尝试下一个格式
            }
        }

        throw new IllegalArgumentException("无法解析时间格式：" + text);
    }




    /**
     * 通用类型转换
     */
    private Object convertValue2(Object value, Class<?> targetType) {
        if (value == null) return null;

        // 先处理 String 类型
        if (targetType == String.class) {
            return value.toString();
        }

        // 数字自动转换
        if (targetType == Long.class || targetType == long.class) {
            if (value instanceof Number) return ((Number) value).longValue();
            return Long.parseLong(value.toString());
        }
        if (targetType == Integer.class || targetType == int.class) {
            if (value instanceof Number) return ((Number) value).intValue();
            return Integer.parseInt(value.toString());
        }
        if (targetType == Double.class || targetType == double.class) {
            if (value instanceof Number) return ((Number) value).doubleValue();
            return Double.parseDouble(value.toString());
        }
        if (targetType == Float.class || targetType == float.class) {
            if (value instanceof Number) return ((Number) value).floatValue();
            return Float.parseFloat(value.toString());
        }
        if (targetType == Boolean.class || targetType == boolean.class) {
            return Boolean.parseBoolean(value.toString());
        }

        // LocalDateTime 自动解析
        if (targetType == LocalDateTime.class) {
            if (value instanceof LocalDateTime) return value;
            if (value instanceof Date) return LocalDateTime.ofInstant(((Date) value).toInstant(), ZoneId.systemDefault());
            String str = value.toString().trim();
            // 尝试多种常见格式解析
            DateTimeFormatter[] formatters = new DateTimeFormatter[]{
                    DateTimeFormatter.ofPattern("yyyy/M/d H:mm"),
                    DateTimeFormatter.ofPattern("yyyy/M/d HH:mm"),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
            };
            for (DateTimeFormatter fmt : formatters) {
                try {
                    return LocalDateTime.parse(str, fmt);
                } catch (Exception ignored) {}
            }
            throw new IllegalArgumentException("无法解析日期时间: " + str);
        }

        // 其他类型直接返回
        return value;
    }


}
