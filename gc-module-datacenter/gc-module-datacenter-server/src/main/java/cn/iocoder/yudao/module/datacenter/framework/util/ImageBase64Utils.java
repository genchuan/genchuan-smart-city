package cn.iocoder.yudao.module.datacenter.framework.util;

import com.anji.captcha.util.Base64Utils;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 图片Base64处理工具类
 */
public class ImageBase64Utils {

    private static final Pattern BASE64_PATTERN = Pattern.compile("^data:image/(jpeg|jpg|png|gif);base64,[A-Za-z0-9+/]*={0,2}$");

    private static final int MAX_IMAGE_SIZE = 2 * 1024 * 1024; // 2MB
    private static final int MAX_TOTAL_SIZE = 10 * 1024 * 1024; // 10MB

    /**
     * 验证Base64图片格式
     */
    public static boolean isValidBase64Image(String base64Str) {
        if (!StringUtils.hasText(base64Str)) {
            return false;
        }
        return BASE64_PATTERN.matcher(base64Str).matches();
    }

    /**
     * 压缩图片Base64数据
     */
    public static String compressBase64Image(String base64Str, float quality) throws IOException {
        if (!isValidBase64Image(base64Str)) {
            throw new IllegalArgumentException("无效的Base64图片格式");
        }

        // 提取Base64数据部分
        String base64Data = base64Str.substring(base64Str.indexOf(",") + 1);
        byte[] imageBytes = Base64Utils.decodeFromString(base64Data);

        // 检查图片大小
        if (imageBytes.length > MAX_IMAGE_SIZE) {
            throw new IllegalArgumentException("图片大小不能超过2MB");
        }

        // 读取图片
        ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
        BufferedImage image = ImageIO.read(inputStream);
        inputStream.close();

        if (image == null) {
            throw new IllegalArgumentException("无法读取图片数据");
        }

        // 计算压缩后的尺寸
        int newWidth = image.getWidth();
        int newHeight = image.getHeight();

        if (image.getWidth() > 1024 || image.getHeight() > 1024) {
            double ratio = Math.min(1024.0 / image.getWidth(), 1024.0 / image.getHeight());
            newWidth = (int) (image.getWidth() * ratio);
            newHeight = (int) (image.getHeight() * ratio);
        }

        // 创建压缩后的图片
        BufferedImage compressedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        compressedImage.createGraphics().drawImage(
                image.getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);

        // 转换为Base64
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(compressedImage, "JPEG", outputStream);
        byte[] compressedBytes = outputStream.toByteArray();
        outputStream.close();

        // 重新构建Base64字符串
        String mimeType = base64Str.substring(5, base64Str.indexOf(";"));
        return "data:image/" + mimeType + ";base64," + Base64Utils.encodeToString(compressedBytes);
    }

    /**
     * 从Base64字符串中提取MIME类型
     */
    public static String getMimeType(String base64Str) {
        if (!isValidBase64Image(base64Str)) {
            return null;
        }
        return base64Str.substring(5, base64Str.indexOf(";"));
    }

    /**
     * 验证多张图片的总大小
     */
    public static void validateTotalSize(List<String> base64Images) {
        long totalSize = 0;
        for (String base64 : base64Images) {
            if (isValidBase64Image(base64)) {
                String base64Data = base64.substring(base64.indexOf(",") + 1);
                totalSize += base64Data.length() * 3 / 4; // Base64编码后大小约为原文件的4/3
            }
        }

        if (totalSize > MAX_TOTAL_SIZE) {
            throw new IllegalArgumentException("所有图片总大小不能超过10MB");
        }
    }

    /**
     * 将Base64图片列表转换为分号分隔的字符串
     */
    public static String imagesToListString(List<String> base64Images) {
        if (base64Images == null || base64Images.isEmpty()) {
            return null;
        }
        return String.join(";", base64Images);
    }

    /**
     * 从分号分隔的字符串解析Base64图片列表
     */
    public static List<String> listStringToImages(String imagesString) {
        if (!StringUtils.hasText(imagesString)) {
            return new ArrayList<>();
        }
        return Arrays.asList(imagesString.split(";"));
    }

    /**
     * 获取Base64图片的数据大小（字节）
     */
    public static int getImageSize(String base64Str) {
        if (!isValidBase64Image(base64Str)) {
            return 0;
        }
        String base64Data = base64Str.substring(base64Str.indexOf(",") + 1);
        return base64Data.length() * 3 / 4; // 近似计算
    }
}