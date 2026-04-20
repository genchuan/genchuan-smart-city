package cn.iocoder.yudao.module.usermerchant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 项目的启动类
 *
 * @author 芋道源码
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"cn.iocoder.yudao.module.system.api"})
public class UserMerchantServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserMerchantServerApplication.class, args);
    }

}