package cn.iocoder.yudao.module.envirhealth.util.convert;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * URL地址转换工具类
 * 用于将内网地址转换为公网地址
 */
@Component
public class UrlConvert {

    @Value("${file.inner-url-prefix:http://192.168.8.68:9000}")
    private String innerUrlPrefix;

    @Value("${file.public-url-prefix:http://112.47.127.21:59000}")
    private String publicUrlPrefix;

    private static UrlConvert instance;

    @PostConstruct
    public void init() {
        instance = this;
    }

    /**
     * 将内网地址转换为公网地址
     * @param innerUrl 内网地址
     * @return 公网地址
     */
    public static String toPublicUrl(String innerUrl) {
        if (instance == null) {
            return innerUrl;
        }
        return instance.convertToPublicUrl(innerUrl);
    }

    /**
     * 将内网地址转换为公网地址（实例方法）
     */
    public String convertToPublicUrl(String innerUrl) {
        if (!StringUtils.hasText(innerUrl)) {
            return innerUrl;
        }
        return innerUrl.replace(innerUrlPrefix, publicUrlPrefix);
    }
}