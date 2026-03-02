package cn.iocoder.yudao.module.park.framework.common;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class QrCodeUtils {

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
            throw new RuntimeException("生成二维码失败", e);
        }
    }
}
