package cn.iocoder.yudao.module.kitchen.framework.lxsutils.common.pdf;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.font.FontProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
@Slf4j
public class PdfGenerator {
    /**
     * 生成 PDF 字节数组并包装为 ResponseEntity（供接口下载使用）
     */
    public ResponseEntity<byte[]> generatePdfResponse(String htmlContent) {
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
            ConverterProperties converterProperties = new ConverterProperties();
//            URL fontUrl = getClass().getClassLoader().getResource("fonts/simsun.ttf");
//            if (fontUrl == null) {
//                throw exception("生成PDF失败：字体文件未找到，请检查 resources/fonts/simsun.ttf");
//            }
//
//            String fontPath = new File(fontUrl.toURI()).getAbsolutePath();
//            if (fontPath==null){
//                throw exception("生成PDF失败：fontPath为空");
//            }
//            FontProvider fontProvider = new FontProvider();
//            fontProvider.addFont(fontPath);
// 创建 FontProvider
// 从 jar 内读取字体流
            FontProvider fontProvider = new FontProvider();
            try (InputStream fontStream = getClass().getClassLoader().getResourceAsStream("fonts/simsun.ttf")) {
                if (fontStream == null) {
                    log.error("生成PDF失败：字体文件未找到，请检查 resources/fonts/simsun.ttf");
                    throw exception("生成PDF失败：字体文件未找到，请检查 resources/fonts/simsun.ttf");
                }

                // 创建临时文件
                File tempFontFile = File.createTempFile("simsun", ".ttf");
                tempFontFile.deleteOnExit(); // JVM 退出时删除

                // 写入临时文件
                try (OutputStream out = new FileOutputStream(tempFontFile)) {
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = fontStream.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                    }
                }

                // 注册字体
                fontProvider.addFont(tempFontFile.getAbsolutePath());
            }catch (IOException ioe) {
                ioe.printStackTrace();
                log.error("字体加载/写入临时文件异常", ioe);
                throw exception("生成PDF失败：字体加载/写入临时文件异常 | " + ioe.getMessage());
            }

            converterProperties.setFontProvider(fontProvider);

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
            URL fontUrl = PdfGenerator.class.getClassLoader()
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
