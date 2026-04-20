package cn.iocoder.yudao.module.stationresource.vrv.utils.common.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

public class QrCodeUtils {

    /**
     * 生成二维码 Base64(简化参数)
     */
    public static String generateBase64(String content) {
        return generateBase64(content,300,300);
    }
    /**
     * 生成二维码 Base64
     */
    public static String generateBase64(String content, int width, int height) {
        try {
            BitMatrix bitMatrix = new MultiFormatWriter()
                    .encode(content, BarcodeFormat.QR_CODE, width, height);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "png", baos);

            byte[] bytes = baos.toByteArray();
            return "data:image/png;base64," +
                    Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            throw exception("生成二维码失败，原因："+e.getMessage());
//            throw exception("生成二维码失败"+ e);
//            throw new RuntimeException("生成二维码失败2", e);
        }

    }


    // ==================== 测试方法，直接运行 ====================
    public static void main(String[] args) {
        // 测试1：正常内容 → 应该成功
        System.out.println("===== 测试1：正常生成 =====");
        try {
            String qr = generateBase64("123456");
            System.out.println("成功：" + qr.substring(0, 50) + "...");
        } catch (Exception e) {
            System.out.println("失败：" + e.getMessage());
        }

        // 测试2：传 null → 一定会报错！
        System.out.println("\n===== 测试2：传 null 故意报错 =====");
        try {
            String qr = generateBase64(null); // 这里会报错
            System.out.println("成功：" + qr);
        } catch (Exception e) {
            System.out.println("失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
