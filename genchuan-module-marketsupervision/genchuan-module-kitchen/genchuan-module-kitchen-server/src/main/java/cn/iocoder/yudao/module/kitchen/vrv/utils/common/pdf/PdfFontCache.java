package cn.iocoder.yudao.module.kitchen.vrv.utils.common.pdf;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.layout.font.FontProvider;
import org.springframework.core.io.ClassPathResource;
import java.io.InputStream;

/**
 * 字体全局缓存工具类
 * 作用：整个项目启动时【只加载一次字体】，避免多次加载导致内存溢出 OOM
 * 这是一个枚举单例，全局唯一，不会重复创建
 */
public enum PdfFontCache {

    /**
     * 单例实例（整个项目只有这一个对象）
     * 作用：保证字体只加载一次
     */
    INSTANCE;

    /**
     * 字体提供者：iText 用来加载和管理字体
     * 用 final 修饰，只会初始化一次，不会重复加载
     */
    private final FontProvider fontProvider;

    /**
     * HTML 转 PDF 的配置项
     * 里面存放了我们加载好的字体
     */
    private final ConverterProperties converterProperties;

    /**
     * 枚举的构造方法
     * 重点：【整个项目只会执行 1 次】！！！
     * 作用：在类加载时就把字体读进内存，永久使用
     */
    PdfFontCache() {
        try {
            // 1. 创建字体管理对象
            fontProvider = new FontProvider();

            // 2. 从项目 resources/fonts/ 目录下读取宋体字体文件
            InputStream fontStream = new ClassPathResource("fonts/simsun.ttf").getInputStream();

            // 3. 把字体读取到内存中（只执行这一次！）
            fontProvider.addFont(fontStream.readAllBytes());

            // 4. 创建 PDF 转换配置
            converterProperties = new ConverterProperties();

            // 5. 将全局字体设置到转换配置里（永久生效）
            converterProperties.setFontProvider(fontProvider);

            // 打印日志，告诉你字体只初始化了一次
            System.out.println("✅ 字体全局初始化成功（仅一次）");

        } catch (Exception e) {
            // 如果字体文件找不到、读取失败，直接抛出异常
            throw new RuntimeException("字体加载失败", e);
        }
    }

    /**
     * 给外部提供获取【已经加载好字体】的配置方法
     * 每次生成 PDF 时，直接调用这个方法拿配置
     * 不会再重新加载字体！！！
     */
    public static ConverterProperties getConverterProperties() {
        return INSTANCE.converterProperties;
    }
}
