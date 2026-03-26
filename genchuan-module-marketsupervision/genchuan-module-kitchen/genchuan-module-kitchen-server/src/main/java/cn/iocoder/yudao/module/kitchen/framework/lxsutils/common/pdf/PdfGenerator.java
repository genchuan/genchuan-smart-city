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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

public class PdfGenerator {
    public ResponseEntity<byte[]> generatePdfResponse(String htmlContent) {
        try {
            // 内存输出流
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // 配置转换属性
            ConverterProperties converterProperties = new ConverterProperties();

            // 使用项目内 SimSun 字体
            URL fontUrl = PdfGenerator.class.getClassLoader()
                    .getResource("fonts/simsun.ttf"); // 放在 resources/fonts/
            if (fontUrl == null) {
                throw new RuntimeException("字体文件未找到，请检查 resources/fonts/simsun.ttf 是否存在");
            }
            String fontPath = new File(fontUrl.toURI()).getAbsolutePath();

            // 创建 FontProvider 并注册字体
            FontProvider fontProvider = new FontProvider();
            fontProvider.addFont(fontPath); // 添加项目内字体

            // 设置给 ConverterProperties
            converterProperties.setFontProvider(fontProvider);

            // 强制 HTML 使用字体（可选）
            htmlContent = "<style>body { font-family: 'SimSun'; }</style>" + htmlContent;

            // 转换 HTML 为 PDF
            HtmlConverter.convertToPdf(htmlContent, baos, converterProperties);

            // 设置响应头，让浏览器下载 PDF
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "notice.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(baos.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
//    public ResponseEntity<byte[]> generatePdfResponse(String htmlContent) {
//        try {
//            // 内存输出流，不写磁盘
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//
//            // 配置转换属性
//            ConverterProperties converterProperties = new ConverterProperties();
//
////            // 初始化字体提供者，注册系统字体和 PDF 标准字体
////            DefaultFontProvider fontProvider = new DefaultFontProvider(true, true, true);
////
////            // 如果不确保资源字体存在，可以先注释掉
////             fontProvider.addFont(PdfGenerator.class.getResourceAsStream("/fonts/SourceHanSerifSC-Regular.otf"));
//
//// 初始化字体提供者（不要再用true,true,true）
//            DefaultFontProvider fontProvider = new DefaultFontProvider(false, false, false);
//
//            // 获取字体文件路径（关键）
//            String fontPath = PdfGenerator.class
//                    .getClassLoader()
//                    .getResource("fonts/simsun.ttf")
//                    .toExternalForm();
////                    .getPath();
//
//
//            // 加载字体
//            fontProvider.addFont(fontPath, "SourceHanSerifCN");
//
//// 读取字体到 byte[]
//            InputStream fontStream = PdfGenerator.class.getClassLoader()
//                    .getResourceAsStream("fonts/simsun.ttf");
//            byte[] fontBytes = fontStream.readAllBytes(); // JDK9+，JDK8用 ByteArrayOutputStream
//
//// 创建字体对象
//            FontProgram fontProgram = FontProgramFactory.createFont(fontBytes);
//
//// 注册字体
//            fontProvider.addFont(fontProgram);
//
//// 设置给 ConverterProperties
//
//            // 设置字体
//            converterProperties.setFontProvider(fontProvider);
//
////            // 强制HTML使用字体
////            htmlContent = "<style>body { font-family: SourceHanSerifCN; }</style>" + htmlContent;
//            // 转换 HTML 为 PDF
//            HtmlConverter.convertToPdf(htmlContent, baos, converterProperties);
//
//            // 设置响应头，让浏览器下载 PDF
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_PDF);
//            headers.setContentDispositionFormData("attachment", "notice.pdf");
//
//            return ResponseEntity.ok()
//                    .headers(headers)
//                    .body(baos.toByteArray());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }

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
