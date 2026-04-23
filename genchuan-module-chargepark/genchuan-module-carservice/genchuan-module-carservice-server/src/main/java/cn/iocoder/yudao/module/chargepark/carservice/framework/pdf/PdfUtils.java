package cn.iocoder.yudao.module.chargepark.carservice.framework.pdf;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.FontSelector;
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
     * 构建字体选择器:ASCII 走 Helvetica,CJK 走 STSong-Light。
     * 原因:UniGB-UCS2-H 编码下 STSong 的 ASCII 字符宽度紊乱,数字会挤在一起。
     */
    private static FontSelector buildSelector(BaseFont cjk, int size, int style, Color color) {
        FontSelector selector = new FontSelector();
        selector.addFont(new Font(Font.HELVETICA, size, style, color));
        selector.addFont(new Font(cjk, size, style, color));
        return selector;
    }

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
        // FontSelector: ASCII 走 Helvetica(等宽正常),CJK 走 STSong-Light;解决"2026-04-22"数字紧挨
        FontSelector headerSelector = buildSelector(bf, 10, Font.BOLD, Color.WHITE);
        FontSelector cellSelector = buildSelector(bf, 9, Font.NORMAL, Color.BLACK);

        // 列数 >8 时升 A3 横向,防止中文挤压换行
        com.lowagie.text.Rectangle pageSize = headers.size() > 8
                ? PageSize.A3.rotate()
                : PageSize.A4.rotate();
        Document document = new Document(pageSize, 36, 36, 36, 36);
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
            // 按 "表头+前 50 行内容最长字符数" 分配相对列宽,防止某一列内容过长被压换行
            try {
                float[] widths = computeColumnWidths(headers, dataList);
                table.setWidths(widths);
            } catch (Exception ignore) {
                // 异常就退回等宽,保持原行为
            }

            // 表头
            for (String header : headers.values()) {
                Phrase hp = headerSelector.process(header);
                hp.setLeading(14f);
                PdfPCell cell = new PdfPCell(hp);
                cell.setBackgroundColor(new Color(46, 117, 182));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setPaddingTop(8f);
                cell.setPaddingBottom(8f);
                cell.setPaddingLeft(6f);
                cell.setPaddingRight(6f);
                table.addCell(cell);
            }

            // 数据行
            List<String> fieldNames = new ArrayList<>(headers.keySet());
            for (T item : dataList) {
                for (String fieldName : fieldNames) {
                    String text = readField(item, fieldName);
                    Phrase cp = text.isEmpty() ? new Phrase(" ", cellFont) : cellSelector.process(text);
                    cp.setLeading(12f);
                    PdfPCell cell = new PdfPCell(cp);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setPaddingTop(6f);
                    cell.setPaddingBottom(6f);
                    cell.setPaddingLeft(5f);
                    cell.setPaddingRight(5f);
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
     * 根据表头 + 前 50 行内容的最长字符数(中文按 2 倍宽),按比例生成列宽数组。
     * 每列最小宽度 3,最大宽度 30,避免极端列占满页面。
     */
    private static <T> float[] computeColumnWidths(LinkedHashMap<String, String> headers, List<T> dataList) {
        List<String> fieldNames = new ArrayList<>(headers.keySet());
        List<String> headerTitles = new ArrayList<>(headers.values());
        int cols = fieldNames.size();
        float[] widths = new float[cols];
        int sample = Math.min(dataList.size(), 50);
        for (int i = 0; i < cols; i++) {
            int max = visualLength(headerTitles.get(i));
            for (int r = 0; r < sample; r++) {
                int len = visualLength(readField(dataList.get(r), fieldNames.get(i)));
                if (len > max) max = len;
            }
            widths[i] = Math.min(30f, Math.max(3f, max));
        }
        return widths;
    }

    /** 中文等非 ASCII 字符按 2 倍宽计算,ASCII 按 1 倍 */
    private static int visualLength(String s) {
        if (s == null || s.isEmpty()) return 0;
        int len = 0;
        for (int i = 0; i < s.length(); i++) {
            len += s.charAt(i) < 0x80 ? 1 : 2;
        }
        return len;
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
