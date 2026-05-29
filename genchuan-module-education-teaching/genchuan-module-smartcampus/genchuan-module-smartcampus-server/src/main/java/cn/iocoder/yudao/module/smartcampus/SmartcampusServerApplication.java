package cn.iocoder.yudao.module.smartcampus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 项目的启动类
 *
 * @author 芋道源码
 */
@SpringBootApplication
@MapperScan("cn.iocoder.yudao.module.smartcampus.dal.mysql.**")
@EnableFeignClients(basePackages = "cn.iocoder.yudao.module.infra.api")
public class SmartcampusServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartcampusServerApplication.class, args);
    }
}
