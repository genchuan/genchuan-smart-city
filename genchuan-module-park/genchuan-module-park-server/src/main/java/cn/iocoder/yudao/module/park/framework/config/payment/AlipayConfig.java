package cn.iocoder.yudao.module.park.framework.config.payment;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlipayConfig {

    @Value("${alipay.app-id}")
    private String appId;

    @Value("${alipay.private-key}")
    private String privateKey;

    @Value("${alipay.alipay-public-key}")
    private String alipayPublicKey;

    @Value("${alipay.notify-url}")
    private String notifyUrl;

    @Value("${alipay.gateway-url}")
    private String gatewayUrl;

    @PostConstruct
    public void init() {
        Config config = new Config();
        config.protocol = "https";
        config.gatewayHost = gatewayUrl.replace("https://", "").replace("/gateway.do", "");
        config.signType = "RSA2";

        config.appId = appId;
        config.merchantPrivateKey = privateKey;
        config.alipayPublicKey = alipayPublicKey;

        config.notifyUrl = notifyUrl;

        Factory.setOptions(config);
    }
}
