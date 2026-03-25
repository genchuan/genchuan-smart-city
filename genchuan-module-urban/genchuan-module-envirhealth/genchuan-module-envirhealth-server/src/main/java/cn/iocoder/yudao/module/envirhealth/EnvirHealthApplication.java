package cn.iocoder.yudao.module.envirhealth;

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
@MapperScan("cn.iocoder.yudao.module.envirhealth.dal.mysql.**")
@EnableFeignClients(basePackages = "cn.iocoder.yudao.module.envirhealth.framework.file")
public class EnvirHealthApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnvirHealthApplication.class, args);
    }
}