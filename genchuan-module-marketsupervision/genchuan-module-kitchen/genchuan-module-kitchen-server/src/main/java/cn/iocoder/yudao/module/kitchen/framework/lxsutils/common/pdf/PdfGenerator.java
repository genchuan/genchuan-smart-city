package cn.iocoder.yudao.module.kitchen.framework.lxsutils.common.pdf;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfGenerator {
    public ResponseEntity<byte[]> generatePdfResponse(String htmlContent) {
        try {
            // 内存输出流，不写磁盘
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // 配置转换属性
            ConverterProperties converterProperties = new ConverterProperties();

            // 初始化字体提供者，注册系统字体和 PDF 标准字体
            DefaultFontProvider fontProvider = new DefaultFontProvider(true, true, true);

            // 如果不确保资源字体存在，可以先注释掉
            // fontProvider.addFont(PdfGenerator.class.getResourceAsStream("/fonts/SourceHanSerifSC-Regular.otf"));

            converterProperties.setFontProvider(fontProvider);

            // 转换 HTML 为 PDF
            HtmlConverter.convertToPdf(htmlContent, baos, converterProperties);

            // 设置响应头，让浏览器下载 PDF
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "notice.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(baos.toByteArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将 HTML 字符串生成 PDF
     *
     * @param htmlContent HTML 内容字符串
     * @param outputPdfPath 输出 PDF 文件路径
     */
    public static void generatePdfFromHtml(String htmlContent, String outputPdfPath) {
        try {
            // 创建输出文件
            File pdfFile = new File(outputPdfPath);
            if (!pdfFile.getParentFile().exists()) {
                pdfFile.getParentFile().mkdirs();
            }

            // 配置转换属性
            ConverterProperties converterProperties = new ConverterProperties();

            // 指定中文字体（宋体），防止中文乱码
// 初始化字体提供者，注册系统字体和 PDF 标准字体
            DefaultFontProvider fontProvider = new DefaultFontProvider(true, true, true);

// 添加中文字体（宋体）
            String fontPath = "C:/Windows/Fonts/simsun.ttc"; // 确保路径存在
            fontProvider.addFont(fontPath);

// 设置给 ConverterProperties
            converterProperties.setFontProvider(fontProvider);

            // 转换 HTML 为 PDF
            HtmlConverter.convertToPdf(htmlContent, new FileOutputStream(outputPdfPath), converterProperties);

            System.out.println("PDF 生成成功：" + outputPdfPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //测试
    public static void main(String[] args) {
        // HTML 字符串
        String htmlContent = "<div style=\"font-family:SimSun;font-size:16px;line-height:30px\">"
                + "<div style=\"text-align:center;font-size:22px;font-weight:bold\">__________市场监督管理局</div>"
                + "<div style=\"text-align:center;font-size:20px;font-weight:bold;margin-top:10px\">责令改正通知书</div>"
                + "<div style=\"text-align:center;margin-top:10px\">_市监责改〔1970〕WW12356号</div>"
                + "<br/><p>________________________：</p>"
                + "<p style=\"text-indent:2em\">经查，你（单位）在校园餐饮后厨操作过程中，存在________________________（如：从业人员未按规定佩戴工作帽/口罩、操作区卫生不达标等）的行为，违反了《中华人民共和国食品安全法》第四十七条（食品生产经营者应当建立并执行从业人员健康管理制度）、《餐饮服务食品安全操作规范》第十六条（从业人员个人卫生要求）等相关规定。</p>"
                + "</div>";

        // 输出 PDF 路径
        String outputPath = "D:/pdf/notice.pdf";

        // 调用生成方法
        generatePdfFromHtml(htmlContent, outputPath);
    }
}
