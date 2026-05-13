package cn.iocoder.yudao.module.stationresource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 项目的启动类
 *
 * @author 芋道源码
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "cn.iocoder.yudao.module.kitchen.api") //加上这个
public class StationresourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StationresourceServerApplication.class, args);
    }

}



