package cn.iocoder.yudao.module.evaluate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 项目的启动类
 *
 * @author 芋道源码
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"cn.iocoder.yudao.module.system.api", "cn.iocoder.yudao.module.evaluate.api", "cn.iocoder.yudao.module.evaluate.file"}) // 微服务版注解，自动扫描所有Feign接口
public class EvaluateServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EvaluateServerApplication.class, args);
    }

}
