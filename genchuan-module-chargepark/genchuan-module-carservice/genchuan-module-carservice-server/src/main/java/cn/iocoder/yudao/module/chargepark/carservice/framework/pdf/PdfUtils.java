package cn.iocoder.yudao.module.chargepark.carservice.framework.pdf;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * PDF 导出工具
 *
 * 基于 OpenPDF（Apache 2.0），默认使用 STSong-Light 亚洲字体支持中文。
 *
 * 用法：
 * <pre>
 *   PdfUtils.write(response, "救援信息.pdf", "救援信息", headers, dataList);
 * </pre>
 */
public class PdfUtils {

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 中文字体（OpenPDF fonts-extra 提供，无需外部 ttf）
     */
    private static BaseFont createChineseBaseFont() {
        try {
            return BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        } catch (Exception ex) {
            throw new RuntimeException("初始化 PDF 中文字体失败", ex);
        }
    }

    /**
     * 把 dataList 写为 PDF 表格，下载到 response。
     *
     * @param response   HTTP 响应
     * @param fileName   下载文件名（含 .pdf）
     * @param title      文档标题
     * @param headers    表头映射 {字段名: 显示标题}（按 Map 顺序作为列顺序）
     * @param dataList   数据列表（Bean 列表，反射读字段）
     */
    public static <T> void write(HttpServletResponse response,
                                 String fileName,
                                 String title,
                                 LinkedHashMap<String, String> headers,
                                 List<T> dataList) throws IOException {
        BaseFont bf = createChineseBaseFont();
        Font titleFont = new Font(bf, 16, Font.BOLD);
        Font headerFont = new Font(bf, 10, Font.BOLD, Color.WHITE);
        Font cellFont = new Font(bf, 9, Font.NORMAL);

        Document document = new Document(PageSize.A4.rotate(), 36, 36, 36, 36);
        try {
            response.setContentType("application/pdf");
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            String encoded = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replace("+", "%20");
            response.setHeader("Content-Disposition", "attachment; filename=" + encoded);

            OutputStream os = response.getOutputStream();
            PdfWriter.getInstance(document, os);
            document.open();

            // 标题
            Paragraph titlePara = new Paragraph(title, titleFont);
            titlePara.setAlignment(Element.ALIGN_CENTER);
            titlePara.setSpacingAfter(12f);
            document.add(titlePara);

            // 生成时间
            Paragraph timePara = new Paragraph(
                    "导出时间：" + LocalDateTime.now().format(DATE_FMT),
                    new Font(bf, 9, Font.ITALIC, Color.GRAY));
            timePara.setAlignment(Element.ALIGN_RIGHT);
            timePara.setSpacingAfter(8f);
            document.add(timePara);

            // 表格
            int columnCount = headers.size();
            PdfPTable table = new PdfPTable(columnCount);
            table.setWidthPercentage(100);
            table.setSpacingBefore(8f);

            // 表头
            for (String header : headers.values()) {
                PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
                cell.setBackgroundColor(new Color(46, 117, 182));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setPadding(6f);
                table.addCell(cell);
            }

            // 数据行
            List<String> fieldNames = new ArrayList<>(headers.keySet());
            for (T item : dataList) {
                for (String fieldName : fieldNames) {
                    String text = readField(item, fieldName);
                    PdfPCell cell = new PdfPCell(new Phrase(text, cellFont));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setPadding(4f);
                    table.addCell(cell);
                }
            }

            if (dataList.isEmpty()) {
                PdfPCell empty = new PdfPCell(new Phrase("无数据", cellFont));
                empty.setColspan(columnCount);
                empty.setHorizontalAlignment(Element.ALIGN_CENTER);
                empty.setPadding(12f);
                table.addCell(empty);
            }

            document.add(table);
        } finally {
            if (document.isOpen()) {
                document.close();
            }
        }
    }

    /**
     * 反射读取 bean 字段，统一转 String。null/空值显示为空。
     */
    private static String readField(Object bean, String fieldName) {
        if (bean == null) return "";
        try {
            Field field = findField(bean.getClass(), fieldName);
            if (field == null) return "";
            field.setAccessible(true);
            Object value = field.get(bean);
            if (value == null) return "";
            if (value instanceof LocalDateTime) {
                return ((LocalDateTime) value).format(DATE_FMT);
            }
            return value.toString();
        } catch (Exception ex) {
            return "";
        }
    }

    private static Field findField(Class<?> cls, String name) {
        Class<?> c = cls;
        while (c != null && c != Object.class) {
            try {
                return c.getDeclaredField(name);
            } catch (NoSuchFieldException ignored) {
                c = c.getSuperclass();
            }
        }
        return null;
    }

    /**
     * 便捷构建表头 map（保持插入顺序）
     */
    public static LinkedHashMap<String, String> headers(String... fieldHeaderPairs) {
        if (fieldHeaderPairs.length % 2 != 0) {
            throw new IllegalArgumentException("headers 参数必须成对（field, header）");
        }
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        for (int i = 0; i < fieldHeaderPairs.length; i += 2) {
            map.put(fieldHeaderPairs[i], fieldHeaderPairs[i + 1]);
        }
        return map;
    }

    private PdfUtils() {}

}
