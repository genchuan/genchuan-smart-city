package cn.iocoder.yudao.module.stationresource.vrv.utils.common.excel;

import cn.hutool.core.bean.BeanUtil;
import cn.idev.excel.EasyExcel;
import cn.idev.excel.annotation.ExcelIgnore;
import cn.idev.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * Excel导入导出工具类（通用）
 * <p>统一处理 Excel 导入模板下载、列表导出、数据导入解析等功能。
 * <p>V2.1 新增：导入错误提示中文化（字段名+类型+示例），Boolean 支持"是/否"输入
 * <p>V2.2 新增：导入模板文件名改为中文业务名+日期、表头自动去除中括号 [xxx]
 * <p>V2.3 新增：导入模板表头非必选字段自动标注“（可选）”
 *
 * @author vrvliang
 * @version V2.3 2026-05-25
 */
public class VrvExcelUtils {

    /**
     * 下载 Excel 导入模板（通用，适配 importExcelAndReturnEntity 解析）
     * <p>自动从实体类读取字段信息生成模板，跳过 @ExcelIgnore 字段，
     * 表头和示例值优先取 @Schema 注解的中文描述和示例值。
     *
     * <pre>
     * 版本历史：
     *   V1 2026-04-08  —— 初始版本：英文字段名作表头，"请输入XXX" 作示例行
     *   V2 2026-05-09 10:07 —— 表头改为读取 @Schema.description（中文名）
     *                           示例行改为读取 @Schema.example（真实示例值）
     *                           支持跳过 @ExcelIgnore 标记的字段
     *                           无注解字段回退到字段名 + 类型默认值
     *   V3 2026-05-21 —— 文件名改为中文业务名+日期（取自类@Schema，如"场站信息_导入模板_20260521.xlsx"）
     *                   表头中文字段自动去除中括号，如 [主键ID] → 主键ID
     * </pre>
     */
    public static <T> void downloadImportTemplate(HttpServletResponse response, Class<T> clazz) throws Exception {
        // 1. 收集字段信息：跳过@ExcelIgnore字段，从@Schema提取中文名和示例值
        List<Field> validFields = new ArrayList<>();
        List<String> headerList = new ArrayList<>();
        List<String> exampleList = new ArrayList<>();

        for (Field field : clazz.getDeclaredFields()) {
            // 跳过 @ExcelIgnore 标记的字段
            if (field.isAnnotationPresent(ExcelIgnore.class)) {
                continue;
            }

            validFields.add(field);

            // 表头中文名：优先取 @Schema.description，无则取字段名
            String headerName = field.getName();
            String example = getDefaultExample(field.getType());
            if (field.isAnnotationPresent(Schema.class)) {
                Schema schema = field.getAnnotation(Schema.class);
                if (schema.description() != null && !schema.description().isEmpty()) {
                    headerName = schema.description();
                    // 去掉中括号，如 [主键ID] → 主键ID
                    headerName = headerName.replaceAll("\\[([^\\]]+)\\]", "$1");
                }
                if (schema.example() != null && !schema.example().isEmpty()) {
                    example = schema.example();
                }
            }
            // 非必选字段表头标注“可选”
            Schema schema = field.getAnnotation(Schema.class);
            if (!field.isAnnotationPresent(NotNull.class)
                    && !field.isAnnotationPresent(NotBlank.class)
                    && !field.isAnnotationPresent(NotEmpty.class)
                    && (schema == null || schema.requiredMode() != Schema.RequiredMode.REQUIRED)) {
                headerName = "（可选）" + headerName;
            }
            headerList.add(headerName);
            exampleList.add(example);
        }

        // 2. 构造 EasyExcel 要求的表头格式
        List<List<String>> head = new ArrayList<>();
        for (String header : headerList) {
            List<String> columnHead = new ArrayList<>();
            columnHead.add(header);
            head.add(columnHead);
        }

        // 3. 构造示例数据行
        List<List<String>> data = new ArrayList<>();
        List<String> exampleRow = new ArrayList<>();
        for (String example : exampleList) {
            exampleRow.add(example);
        }
        data.add(exampleRow);

        // 4. 响应头配置
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        // 文件名：优先取类上@Schema的description作为业务中文名，如"场站信息"，否则取类名
        String bizName = clazz.getSimpleName(); // 兜底用类名
        Schema classSchema = clazz.getAnnotation(Schema.class);
        if (classSchema != null && classSchema.description() != null && !classSchema.description().isEmpty()) {
            bizName = classSchema.description()
                    .replace("管理后台 - ", "")       // 去掉"管理后台 - "前缀
                    .replace(" Request VO", "")        // 去掉" Request VO"后缀
                    .replace(" 新增", "")              // 去掉" 新增"
                    .replace(" 创建", "")              // 去掉" 创建"
                    .replace("新增/修改", "")          // 去掉"新增/修改"
                    .replaceAll("[\\s\\-]+$", "")      // 去掉尾部空格和横线
                    .trim();
        }
        // 文件名加日期，如 场站信息_导入模板_20260521.xlsx
        String timeStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String fileName = URLEncoder.encode(bizName + "_导入模板_" + timeStr + ".xlsx", StandardCharsets.UTF_8);
        response.setHeader("Content-Disposition", "attachment; filename*=" + fileName);

        // 5. EasyExcel 写出
        EasyExcel.write(response.getOutputStream())
                .head(head)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .sheet("导入模板")
                .doWrite(data);
    }

    /**
     * 根据字段类型返回默认示例值
     *
     * <pre>
     * 版本历史：
     *   V1 2026-05-09 10:07 —— 初始版本：String→"示例文本"，数字→1，日期→2026-01-01
     * </pre>
     */
    private static String getDefaultExample(Class<?> type) {
        if (type == String.class) {
            return "示例文本";
        } else if (type == Integer.class || type == int.class) {
            return "1";
        } else if (type == Long.class || type == long.class) {
            return "1";
        } else if (type == Double.class || type == double.class) {
            return "1.0";
        } else if (type == BigDecimal.class) {
            return "1.00";
        } else if (type == LocalDateTime.class) {
            return "2026-01-01 00:00:00";
        } else if (type == LocalDate.class) {
            return "2026-01-01";
        } else if (type == Date.class) {
            return "2026-01-01";
        } else if (type == Boolean.class || type == boolean.class) {
            return "true";
        }
        return "";
    }

    /**
     * Java 类型 → 中文描述（用于导入错误提示，让普通人能看懂）
     *
     * <pre>
     * 版本历史：
     *   V1 2026-05-09 10:07 —— 初始版本：Long/Integer→整数，BigDecimal→数字，LocalDateTime→日期时间，LocalDate/Date→日期，Boolean→true或false
     * </pre>
     */
    private static String typeToChinese(Class<?> type) {
        if (type == String.class) {
            return "文本";
        } else if (type == Integer.class || type == int.class || type == Long.class || type == long.class) {
            return "整数";
        } else if (type == Double.class || type == double.class || type == BigDecimal.class) {
            return "数字";
        } else if (type == LocalDateTime.class) {
            return "日期时间";
        } else if (type == LocalDate.class || type == Date.class) {
            return "日期";
        } else if (type == Boolean.class || type == boolean.class) {
            return "true或false";
        }
        return type.getSimpleName();
    }



    /**
     * 列表导出 Excel（简化版，自动生成时间戳文件名）
     * <p>只需传入数据列表，自动获取实体类并生成带时间戳的文件名。
     * <p>评价：4.5
     *
     * @param response  HttpServletResponse
     * @param dataList  数据列表
     * @param <T>       泛型
     * @throws Exception 异常直接抛出
     *
     * <pre>
     * 版本历史：
     *   V1 2026-04-08 —— 初始版本：自动生成文件名 + 列表导出
     * </pre>
     */
    public static <T> void listExportExcelSimple(HttpServletResponse response,
                                  List<?> dataList) throws Exception {

        // ===================== 核心：自动生成文件名 =====================
        String timeStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        String fileName = "Excel导出_" + timeStr;

        // 从列表第一个元素获取真实实体类（必须保证列表非空）
        if (dataList == null || dataList.isEmpty()) {
            throw exception("导出数据列表不能为空");
        }
        Class<T> excelTemplateClass = (Class<T>) dataList.get(0).getClass();

        // 调用完整导出方法
        listExportExcel(response, fileName, excelTemplateClass, dataList);
    }
    /**
     * 列表导出 Excel（完整版，指定文件名 + 模板类）
     * <p>支持自定义文件名前缀和 Excel 模板类（通常为带 @ExcelProperty 的 RespVO）。
     * <p>评价：4
     *
     * @param response           HttpServletResponse
     * @param fileName           文件前缀（如：企业风险评估报告）
     * @param excelTemplateClass 【重要！】Excel 导出模板类（加 @ExcelProperty 注解的实体类，决定表头、顺序、列宽）
     * @param dataList           数据列表
     * @param <T>                泛型
     * @throws Exception         异常直接抛出
     *
     * <pre>
     * 版本历史：
     *   V1 2026-04-08 —— 初始版本：文件名编码 + 响应头配置 + BeanUtil 转换 + Excel 写出
     * </pre>
     */
    public static <T> void listExportExcel(HttpServletResponse response,
                                  String fileName,
                                  Class<T> excelTemplateClass,
                                  List<?> dataList) throws Exception {

        // ===================== 1. 文件名编码处理（解决中文乱码 + 空格变+号） =====================
        String encodeFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");

        // ===================== 2. 设置下载响应头（告诉浏览器：这是要下载的Excel文件） =====================
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment; filename*=" + encodeFileName);

        // ===================== 3. 数据类型转换 =====================
        // 作用：将原始数据列表 → 转换成【Excel模板类】的列表（必须和模板类字段对应）
        // 注意：这里的 T 就是上面传入的 excelTemplateClass 类型
        List<T> exportList = BeanUtil.copyToList(dataList, excelTemplateClass);

        // ===================== 4. 执行Excel写出 =====================
        // 说明：第二个参数 "temp.xls" 是内部临时文件名，不影响用户下载的真实文件名
        // 真正生效的文件名是前面 response.setHeader 设置的 fileName
        ExcelUtils.write(response, "temp.xls", "数据", excelTemplateClass, exportList);
    }


    /**
     * Excel 文件导入解析为实体列表
     * <p>读取 Excel 文件，按字段声明顺序映射到目标实体类，支持多种日期格式自动解析。
     * <p>注意：Excel 列顺序必须与 targetClass 字段声明顺序一致（跳过 @ExcelIgnore 字段）。
     * <p>缺点：Excel 的字段顺序必须和参数 targetClass 全类名的字段顺序一样
     * <p>参数 targetClass 是指类名（全类名），通常用 .getClass().getName() 得到，
     * 如 cn.iocoder.yudao.module.industry.controller.admin.importer.ImportVO
     * <p>评价：4
     *
     * <pre>
     * 版本历史：
     *   V1 2026-04-07 —— 初始版本：Excel 读取 → 类型转换 → 实体列表输出，支持调试模式
     *   V2 2026-05-09 10:07 —— 导入时跳过 @ExcelIgnore 字段，与下载模板列序保持一致
     *   V3 2026-05-09 10:07 —— 错误提示中文化：字段名→@Schema中文名，类型→中文描述，附带示例值
     *                          Boolean 支持填"是/否"（兼容 true/false）
     * </pre>
     */
    public static <T> Map<String, Object> importExcelAndReturnEntity(
            MultipartFile file,
            String targetClassName
    ) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //调试flag
        boolean debugFlag=true;
        if (file.isEmpty()) {
            throw exception(new ErrorCode(500,"请检查文件，文件必须Excel或者非空"));
        }
        // 1. 读取 Excel 为 List<Map<String,Object>>
        List<Map<String, Object>> dataList = EasyExcel.read(file.getInputStream())
                .sheet()
                .headRowNumber(1)   // 第一行为表头
                .doReadSync();

        // 1.2 调试输出
        if (debugFlag){
            System.out.println("=== Excel 原始数据调试 ===");
            System.out.println("总行数: " + dataList.size());
            for (int i = 0; i < dataList.size(); i++) {
                Map<String, Object> row = dataList.get(i);
                System.out.println("第 " + (i + 1) + " 行: " + row);
            }
            System.out.println("=== 调试结束 ===");
        }

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

        // 3. 获取目标实体类字段信息（跳过@ExcelIgnore字段，与模板保持一致）
        Class<?> targetClass = Class.forName(targetClassName);
        Map<String, String> entityFieldInfo = new LinkedHashMap<>();
        for (Field field : targetClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(ExcelIgnore.class)) {
                continue;
            }
            entityFieldInfo.put(field.getName(), field.getType().getSimpleName());
        }

        // 3. 输出调试  excel字段类型和实体类字段信息
        if (debugFlag){
            System.out.println("=== Excel 字段信息 ===");
            for (int i = 0; i < excelFieldInfoList.size(); i++) {
                System.out.println("第 " + (i + 1) + " 行: " + excelFieldInfoList.get(i));
            }
            System.out.println("=== 实体类字段信息 ===");
            System.out.println(entityFieldInfo);
        }

        //4.将Excel数据转化为 实体数据
        List<T> entityList = new ArrayList<>();
        //列名序列（第一列默认是0）
        int colIndex=0;
        int rowIndex = 1; // Excel 行号（第 1 行是表头）
        for (Map<String, Object> row : dataList) {
            T obj = (T) targetClass.getDeclaredConstructor().newInstance(); // 创建实例
            //每到一个新对象，列名都要重置到1
            rowIndex++;   // 从第 2 行（数据第一行）开始
            colIndex=0;
            for (Field field : targetClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(ExcelIgnore.class)) {
                    continue;
                }
                field.setAccessible(true);
                String fieldName = field.getName();
                Class<?> fieldType = field.getType();

                Object value = row.get(colIndex);
                colIndex++;
                if (value != null) {
                    if (debugFlag){
                        System.out.println("cs2026-01-06 11:47:10:value:"+value);
                    }

                    // 安全转换类型
                    try {
                        if (fieldType == Boolean.class || fieldType == boolean.class) {
                            String boolStr = value.toString().trim();
                            if ("是".equals(boolStr) || "true".equalsIgnoreCase(boolStr)) {
                                field.set(obj, true);
                            } else if ("否".equals(boolStr) || "false".equalsIgnoreCase(boolStr)) {
                                field.set(obj, false);
                            } else {
                                field.set(obj, Boolean.parseBoolean(boolStr));
                            }
                        } else if (fieldType == String.class) {
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
                            // 按需修改解析格式
                            field.set(obj, parseLocalDateTime(value));
                        }else if (fieldType == Date.class) {
                            // 将 String 转 Date
                            if (value instanceof Date) {
                                field.set(obj, value);
                            } else {
                                // 解析 yyyy/M/d 或 yyyy/MM/dd 格式
                                String text = value.toString().trim();
                                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy/M/d");
                                field.set(obj, sdf.parse(text));
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
                        // 取中文名 + 中文类型 + 示例值，让普通人能看懂错误提示
                        String cnFieldName = field.getName();
                        String example = getDefaultExample(field.getType());
                        if (field.isAnnotationPresent(Schema.class)) {
                            Schema schema = field.getAnnotation(Schema.class);
                            if (schema.description() != null && !schema.description().isEmpty()) {
                                cnFieldName = schema.description();
                            }
                            if (schema.example() != null && !schema.example().isEmpty()) {
                                example = schema.example();
                            }
                        }
                        throw exception(
                                new ErrorCode(
                                        500,
                                        "Excel 第{}行，【{}】填写错误：您填的是【{}】，这里需要填{}，例如：{}"
                                ),
                                rowIndex,
                                cnFieldName,
                                value,
                                typeToChinese(field.getType()),
                                example
                        );
                    }

                }
            }
            entityList.add(obj);
        }

        if (debugFlag){
            System.out.println("=== 实体数据查看 ===");
            System.out.println("总行数: " + entityList.size());
            for (int i = 0; i < entityList.size(); i++) {
                Object row = entityList.get(i);
                System.out.println("第 " + (i + 1) + " 行: " + row);
                System.out.println("cs2026-01-06 11:53:03:实体类型为："+row.getClass());
            }
            System.out.println("=== 调试结束 ===");
        }

        // 5. 返回结果
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("excelFieldInfo", excelFieldInfoList);
        result.put("entityFieldInfo", entityFieldInfo);
        result.put("entityList", entityList);   //解析出的实体列表，用于导入数据库

        return result;
    }


    // 多格式日期时间解析
    private static final DateTimeFormatter[] DATE_TIME_FORMATTERS = new DateTimeFormatter[]{
            // 带毫秒
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS"),
            DateTimeFormatter.ofPattern("yyyy-M-d H:m:s.SSS"),
            DateTimeFormatter.ofPattern("yyyy/M/d H:m:s.SSS"),

            // 带秒
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-M-d H:m:s"),
            DateTimeFormatter.ofPattern("yyyy/M/d H:m:s"),

            // 不带秒
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-M-d H:m"),
            DateTimeFormatter.ofPattern("yyyy/M/d H:m"),

            // 仅日期
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy/M/d"),
            DateTimeFormatter.ofPattern("yyyyMMdd"),

            // ISO 标准
            DateTimeFormatter.ISO_LOCAL_DATE_TIME
    };



    /**
     * 多格式日期时间字符串 → LocalDateTime 解析
     * <p>兼容 String 和 Date 类型，依次尝试 yyyy-MM-dd HH:mm:ss / yyyy/M/d 等多种格式。
     *
     * <pre>
     * 版本历史：
     *   V1 2026-04-07 —— 初始版本：多格式 DateTimeFormatter 数组依次匹配
     * </pre>
     */
    private static LocalDateTime parseLocalDateTime(Object value) {
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
        throw exception("无法解析时间格式：" + text);
    }



    // ==================== 【测试1：测试 buildHead】 ====================
//    public static void main(String[] args) {
//        Class<?> doClass = AiAlertMessageDO .class;
//        Class<?> respVOClass = AiAlertMessageRespVO .class;
//        List<List<String>> head = buildHead(doClass, respVOClass);
//        System.out.println("cs2026-04-07 16:36:12:"+head);
//        System.out.println("===== 生成的 EasyExcel 表头 =====");
//        for (int i = 0; i < head.size(); i++) {
//            System.out.println("第 " + (i+1) + " 列：" + head.get(i));
//        }
//    }

}
