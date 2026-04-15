package cn.iocoder.yudao.module.kitchen.vrv.utils.common.pdf;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.itextpdf.kernel.PdfException.PdfEncodings;

@Slf4j
public class VrvPdfGenerator {



    /**
     * 列表数据直接导出PDF（通用所有带@ExcelProperty的实体类）
     * 自动读取表头、自动排序、自动处理格式
     *
     * @param response 响应对象
     * @param clazz 导出实体类或者有@ExcelProperty注解的类
     * @param title PDF文件标题
     * @param list 数据列表
     * @throws Exception 导出异常
     */
    public static <T> void listExportPdf(HttpServletResponse response, Class<T> clazz, String title, List<T> list) throws Exception {
        // 设置PDF响应头信息
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        // 构建文件名：标题+当前日期
        String fileName = URLEncoder.encode(title + "_" + LocalDate.now() + ".pdf", StandardCharsets.UTF_8)
                .replace("+", "%20");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        // 根据Excel注解自动生成HTML表格内容
        String html = buildPdfHtmlFromExcelClass(clazz, list, title);

        // 调用项目现有PDF生成工具，输出到前端
        try (OutputStream out = response.getOutputStream()) {
            VrvPdfGenerator generator = new VrvPdfGenerator();
            byte[] pdfBytes = generator.generatePdfResponse(html).getBody();
            out.write(pdfBytes);
            out.flush();
        }
    }

    /**
     * 从带@ExcelProperty的实体类自动构建PDF所需的HTML
     * 自动过滤无效字段、自动按index排序、自动生成表头与数据
     */
    private static <T> String buildPdfHtmlFromExcelClass(Class<T> clazz, List<T> list, String title) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("<meta charset=\"UTF-8\">");

        // PDF样式控制：字体、表格、边距、自动换行（保证PDF显示正常）
        html.append("<style>");
        html.append("body { font-family: 'SimSun', '宋体'; font-size: 11px; margin: 10px; }");
        html.append(".title { text-align: center; font-size: 18px; font-weight: bold; margin: 15px 0; }");
        html.append("table { width: 100%; border-collapse: collapse; table-layout: fixed; }");
        html.append("th, td { border: 1px solid #333; padding: 5px 3px; text-align: center; word-wrap: break-word; }");
        html.append("th { background-color: #f5f5f5; font-weight: bold; font-size: 12px; }");
        html.append("</style>");
        html.append("</head>");
        html.append("<body>");

        // PDF标题
        html.append("<div class='title'>").append(title).append("</div>");

        // ===================== 核心逻辑：筛选有效业务字段 =====================
        // 只保留带@ExcelProperty、非合成、非静态、非$开头的字段
        List<Field> validFields = new ArrayList<>();
        Field[] allFields = clazz.getDeclaredFields();
        for (Field field : allFields) {
            if (field.isAnnotationPresent(ExcelProperty.class)
                    && !field.isSynthetic()
                    && !Modifier.isStatic(field.getModifiers())
                    && !field.getName().startsWith("$")) {
                validFields.add(field);
            }
        }

        // 按@ExcelProperty(index)排序，保证表头顺序与Excel完全一致
        validFields.sort(Comparator.comparingInt(f -> f.getAnnotation(ExcelProperty.class).index()));

        // ===================== 生成表头 =====================
        // 重点：ExcelProperty的value是数组，必须取[0]作为表头名称
        html.append("<table>");
        html.append("<tr>");
        for (Field field : validFields) {
            ExcelProperty anno = field.getAnnotation(ExcelProperty.class);
            String[] values = anno.value();
            String headerName = (values != null && values.length > 0) ? values[0] : field.getName();
            html.append("<th>").append(headerName).append("</th>");
        }
        html.append("</tr>");

        // ===================== 填充数据行 =====================
        for (T data : list) {
            html.append("<tr>");
            for (Field field : validFields) {
                try {
                    field.setAccessible(true);
                    Object val = field.get(data);
                    String cellValue = "";

                    if (val != null) {
                        // 报表类型特殊转换：1=月报 2=自定义报表
                        if ("reportType".equals(field.getName())) {
                            cellValue = (Integer) val == 1 ? "月报" : "自定义报表";
                        } else {
                            cellValue = val.toString();
                        }
                    }
                    html.append("<td>").append(cellValue).append("</td>");
                } catch (Exception e) {
                    html.append("<td></td>");
                }
            }
            html.append("</tr>");
        }

        html.append("</table>");
        html.append("</body>");
        html.append("</html>");
        return html.toString();
    }



    /**
     * 生成 PDF 字节数组并包装为 ResponseEntity（供接口下载使用）
     */
    public ResponseEntity<byte[]> generatePdfResponse(String htmlContent) {
        // 获取内存管理器
        Runtime runtime = Runtime.getRuntime();

// 执行前
        long beforeMem = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("PDF导出前占用内存：" + beforeMem / 1024 / 1024 + " MB");


        long start = System.currentTimeMillis();
        // 禁止返回 null！出现异常直接抛错
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            // 1. 校验 HTML 内容
            if (htmlContent == null || htmlContent.isBlank()) {
                log.error("生成PDF失败：HTML 内容为空或 null");
                throw exception("生成PDF失败：HTML 内容不能为空");
            }
            if (htmlContent.isBlank()) {
                log.error("生成PDF失败：HTML 内容为空字符串");
                throw exception("生成PDF失败：HTML 内容为空字符串");
            }

            // 2. 配置字体

            // ==========================
            // 关键：这里直接使用全局字体，不再重复加载
            // ==========================
            ConverterProperties converterProperties = PdfFontCache.getConverterProperties();
//            ConverterProperties converterProperties = new ConverterProperties();
//
//// 创建 FontProvider
//// 从 jar 内读取字体流
//            FontProvider fontProvider = new FontProvider();
//            try (InputStream fontStream = getClass().getClassLoader().getResourceAsStream("fonts/simsun.ttf")) {
//                if (fontStream == null) {
//                    log.error("生成PDF失败：字体文件未找到，请检查 resources/fonts/simsun.ttf");
//                    throw exception("生成PDF失败：字体文件未找到，请检查 resources/fonts/simsun.ttf");
//                }
//
//                // 创建临时文件
//                File tempFontFile = File.createTempFile("simsun", ".ttf");
//                tempFontFile.deleteOnExit(); // JVM 退出时删除
//
//                // 写入临时文件
//                try (OutputStream out = new FileOutputStream(tempFontFile)) {
//                    byte[] buffer = new byte[1024];
//                    int len;
//                    while ((len = fontStream.read(buffer)) != -1) {
//                        out.write(buffer, 0, len);
//                    }
//                }
//
//                // 注册字体
//                fontProvider.addFont(tempFontFile.getAbsolutePath());
//            }catch (IOException ioe) {
//                ioe.printStackTrace();
//                log.error("字体加载/写入临时文件异常", ioe);
//                throw exception("生成PDF失败：字体加载/写入临时文件异常 | " + ioe.getMessage());
//            }
//
//            converterProperties.setFontProvider(fontProvider);

            // 3. 强制使用宋体
            htmlContent = "<style>body { font-family: 'SimSun'; }</style>" + htmlContent;

            // 4. 转换 HTML 为 PDF
            try {
                HtmlConverter.convertToPdf(htmlContent, baos, converterProperties);
            } catch (Exception pdfEx) {
                pdfEx.printStackTrace();
                log.error("HTML 转 PDF 异常", pdfEx);
                throw exception("生成PDF失败：HTML 转 PDF 异常 | " + pdfEx.getMessage());
            }

            // 5. 构建下载响应头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "notice.pdf");

            // 执行后
            long afterMem = runtime.totalMemory() - runtime.freeMemory();
            System.out.println("PDF导出后占用内存：" + afterMem / 1024 / 1024 + " MB");
            System.out.println("本次导出消耗内存：" + (afterMem - beforeMem)/1024/1024 + " MB");


            return ResponseEntity.ok()
                    .headers(headers)
                    .body(baos.toByteArray());


        } catch (Exception e) {
            // 关键：异常不捕获后静默返回 null，而是抛出异常，方便排查
            log.error("生成PDF失败，总体异常", e);
            e.printStackTrace(); // 控制台打印完整堆栈
            Throwable cause = e.getCause();
            String causeMsg = (cause != null) ? cause.toString() : "无根本原因";
            throw exception("生成PDF失败：" + e.getMessage() + " | 根本原因：" + causeMsg);
        }
    }


    /**
     * 将 HTML 字符串生成 PDF（项目内 SimSun 字体）
     */
    public static void generatePdfFromHtml(String htmlContent, String outputPdfPath) {
        try {
            // 创建输出文件夹
            File pdfFile = new File(outputPdfPath);
            if (!pdfFile.getParentFile().exists()) {
                pdfFile.getParentFile().mkdirs();
            }

            // 项目内字体路径（resources/fonts/simsun.ttf）
            URL fontUrl = VrvPdfGenerator.class.getClassLoader()
                    .getResource("fonts/simsun.ttf");
            if (fontUrl == null) {
                throw new RuntimeException("字体文件未找到，请检查 resources/fonts/simsun.ttf 是否存在");
            }
            String fontPath = new File(fontUrl.toURI()).getAbsolutePath();

            // 创建 FontProvider 并注册字体
            FontProvider fontProvider = new FontProvider();
            fontProvider.addFont(fontPath);

            // 配置 ConverterProperties
            ConverterProperties converterProperties = new ConverterProperties();
            converterProperties.setFontProvider(fontProvider);

            // 转换 HTML 为 PDF
            HtmlConverter.convertToPdf(htmlContent, new FileOutputStream(outputPdfPath), converterProperties);

            System.out.println("PDF 生成成功：" + outputPdfPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String htmlContent = "<div style=\"font-family:'SimSun';font-size:16px;line-height:30px\">"
                + "<div style=\"text-align:center;font-size:22px;font-weight:bold\">__________市场监督管理局</div>"
                + "<div style=\"text-align:center;font-size:20px;font-weight:bold;margin-top:10px\">责令改正通知书</div>"
                + "<div style=\"text-align:center;margin-top:10px\">_市监责改〔1970〕WW12356号</div>"
                + "<br/><p>________________________：</p>"
                + "<p style=\"text-indent:2em\">经查，你（单位）在校园餐饮后厨操作过程中，存在________________________（如：从业人员未按规定佩戴工作帽/口罩、操作区卫生不达标等）的行为，违反了《中华人民共和国食品安全法》第四十七条（从业人员健康管理制度）、《餐饮服务食品安全操作规范》第十六条（从业人员个人卫生要求）等相关规定。</p>"
                + "</div>";

        String outputPath = "D:/pdf/notice.pdf";
        generatePdfFromHtml(htmlContent, outputPath);
    }
}
