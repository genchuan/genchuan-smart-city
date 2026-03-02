package cn.iocoder.yudao.module.park.controller.admin.testpay.qrcode;

import cn.iocoder.yudao.module.park.framework.common.QrCodeUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "停车管理后台 - 生成二维码")
@RestController
@RequestMapping("/park/test/QRCode")
public class GenerateQRCodeController {

    @PostMapping("/arrears/generate-qrcode")
    public String generateQrCode(@RequestBody QrCodeRequest request) {


        Long paymentId=request.getPayId();

        // 2. 支付页面 URL（不要把金额放前端）
        String payUrl = "https://yourdomain.com/pay?paymentId=" + paymentId;
//        String payUrl = "http://r68a969f.natappfree.cc/alipay/notify";

        // 3. 生成二维码 Base64
        return QrCodeUtils.generateBase64(payUrl, 300, 300);
    }


}
