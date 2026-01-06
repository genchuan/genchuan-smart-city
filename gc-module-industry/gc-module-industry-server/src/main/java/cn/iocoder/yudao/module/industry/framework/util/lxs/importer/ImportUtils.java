package cn.iocoder.yudao.module.industry.framework.util.lxs.importer;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import com.alibaba.excel.EasyExcel;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.PARK_BERTH_LOCK_NOT_EXISTS;

//导入工具类
public class ImportUtils {

    /**
     * Excel 数据转实体列表，并返回调试信息
     * 参数targetClass是指类名，通常用.getClass.getName()得到,
     * 比如cn.iocoder.yudao.module.industry.controller.admin.importer.ImportVO
     */
    public static <T> Map<String, Object> importExcelAndReturnEntity(
            MultipartFile file,
            String targetClassName
    ) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (file.isEmpty()) {
            throw exception(new ErrorCode(500,"文件是空的"));
        }
        // 1. 读取 Excel 为 List<Map<String,Object>>
        List<Map<String, Object>> dataList = EasyExcel.read(file.getInputStream())
                .sheet()
                .headRowNumber(1)   // 第一行为表头
                .doReadSync();

        // 1.2 调试输出
        System.out.println("=== Excel 原始数据调试 ===");
        System.out.println("总行数: " + dataList.size());
        for (int i = 0; i < dataList.size(); i++) {
            Map<String, Object> row = dataList.get(i);
            System.out.println("第 " + (i + 1) + " 行: " + row);
        }
        System.out.println("=== 调试结束 ===");

        // 2. 收集 Excel 字段信息
        List<Map<String, String>> excelFieldInfoList = new ArrayList<>();
        for (Map<String, Object> row : dataList) {
            Map<String, String> rowFieldInfo = new LinkedHashMap<>();
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                rowFieldInfo.put(String.valueOf(entry.getKey()),
                        entry.getValue() == null ? "null" : entry.getValue().getClass().getSimpleName());
            }
            excelFieldInfoList.add(rowFieldInfo);
        }

        // 3. 获取目标实体类字段信息
        Class<?> targetClass = Class.forName(targetClassName);
        Map<String, String> entityFieldInfo = new LinkedHashMap<>();
        for (Field field : targetClass.getDeclaredFields()) {
            entityFieldInfo.put(field.getName(), field.getType().getSimpleName());
        }

        // 3. 输出调试  excel字段类型和实体类字段信息
        System.out.println("=== Excel 字段信息 ===");
        for (int i = 0; i < excelFieldInfoList.size(); i++) {
            System.out.println("第 " + (i + 1) + " 行: " + excelFieldInfoList.get(i));
        }
        System.out.println("=== 实体类字段信息 ===");
        System.out.println(entityFieldInfo);

        //4.将Excel数据转化为 实体数据
        List<T> entityList = new ArrayList<>();
        //列名序列（第一列默认是0）
        int colIndex=0;
        for (Map<String, Object> row : dataList) {
            T obj = (T) targetClass.getDeclaredConstructor().newInstance(); // 创建实例
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
                        }else if (fieldType == Date.class) {
                            // 将 String 转 Date
                            if (value instanceof Date) {
                                field.set(obj, value);
                            } else {
                                // 解析 yyyy/M/d 或 yyyy/MM/dd 格式
                                String text = value.toString().trim();
                                try {
                                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy/M/d");
                                    field.set(obj, sdf.parse(text));
                                } catch (Exception e) {
                                    throw new RuntimeException("字段转换失败: " + field.getName() + ", 值: " + value + ", 类型: Date", e);
                                }
                            }
                        } else if (fieldType == LocalDate.class) {
                            // 将 String 或 Date 转 LocalDate
                            if (value instanceof LocalDate) {
                                field.set(obj, value);
                            } else if (value instanceof Date) {
                                field.set(obj, ((Date) value).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                            } else {
                                String text = value.toString().trim();
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d");
                                field.set(obj, LocalDate.parse(text, formatter));
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


        // 5. 返回结果
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("excelFieldInfo", excelFieldInfoList);
        result.put("entityFieldInfo", entityFieldInfo);
        result.put("entityList", entityList);
        return result;
    }

    //原始版本，暂时留着不用
    /**
     * Excel 数据转实体列表，并返回调试信息
     * 参数targetClassName是指类全名，比如cn.iocoder.yudao.module.industry.controller.admin.importer.ImportVO
     */
    public static Map<String, Object> importExcelAndCollectInfo(
            MultipartFile file,
            String targetClassName
    ) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (file.isEmpty()) {
            throw exception(new ErrorCode(500,"文件是空的"));
        }
        // 1. 读取 Excel 为 List<Map<String,Object>>
        List<Map<String, Object>> dataList = EasyExcel.read(file.getInputStream())
                .sheet()
                .headRowNumber(1)   // 第一行为表头
                .doReadSync();

        // 1.2 调试输出
        System.out.println("=== Excel 原始数据调试 ===");
        System.out.println("总行数: " + dataList.size());
        for (int i = 0; i < dataList.size(); i++) {
            Map<String, Object> row = dataList.get(i);
            System.out.println("第 " + (i + 1) + " 行: " + row);
        }
        System.out.println("=== 调试结束 ===");

        // 2. 收集 Excel 字段信息
        List<Map<String, String>> excelFieldInfoList = new ArrayList<>();
        for (Map<String, Object> row : dataList) {
            Map<String, String> rowFieldInfo = new LinkedHashMap<>();
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                rowFieldInfo.put(String.valueOf(entry.getKey()),
                        entry.getValue() == null ? "null" : entry.getValue().getClass().getSimpleName());
            }
            excelFieldInfoList.add(rowFieldInfo);
        }

        // 3. 获取目标实体类字段信息
        Class<?> targetClass = Class.forName(targetClassName);
        Map<String, String> entityFieldInfo = new LinkedHashMap<>();
        for (Field field : targetClass.getDeclaredFields()) {
            entityFieldInfo.put(field.getName(), field.getType().getSimpleName());
        }

        // 3. 输出调试  excel字段类型和实体类字段信息
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
                        }else if (fieldType == Date.class) {
                            // 将 String 转 Date
                            if (value instanceof Date) {
                                field.set(obj, value);
                            } else {
                                // 解析 yyyy/M/d 或 yyyy/MM/dd 格式
                                String text = value.toString().trim();
                                try {
                                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy/M/d");
                                    field.set(obj, sdf.parse(text));
                                } catch (Exception e) {
                                    throw new RuntimeException("字段转换失败: " + field.getName() + ", 值: " + value + ", 类型: Date", e);
                                }
                            }
                        } else if (fieldType == LocalDate.class) {
                            // 将 String 或 Date 转 LocalDate
                            if (value instanceof LocalDate) {
                                field.set(obj, value);
                            } else if (value instanceof Date) {
                                field.set(obj, ((Date) value).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                            } else {
                                String text = value.toString().trim();
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d");
                                field.set(obj, LocalDate.parse(text, formatter));
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


        // 5. 返回结果
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("excelFieldInfo", excelFieldInfoList);
        result.put("entityFieldInfo", entityFieldInfo);
        result.put("entityList", entityList);
        return result;
    }

    // 多格式日期时间解析
    private static final DateTimeFormatter[] DATE_TIME_FORMATTERS = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-M-d HH:mm"),
            DateTimeFormatter.ofPattern("yyyy/M/d HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy/M/d")
    };

    /**
     * 解析 Object 为 LocalDateTime（兼容 String/Date）
     */
    public static LocalDateTime parseLocalDateTime(Object value) {
        if (value == null) return null;

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
                // 尝试下一个格式
            }
        }
        throw new IllegalArgumentException("无法解析时间格式：" + text);
    }
}
