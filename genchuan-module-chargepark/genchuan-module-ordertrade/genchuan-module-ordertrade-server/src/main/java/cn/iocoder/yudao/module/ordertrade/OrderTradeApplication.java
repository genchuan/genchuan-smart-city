package cn.iocoder.yudao.module.ordertrade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 订单交易微服务 - 启动类
 *
 * @author genchuan
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderTradeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderTradeApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  订单交易模块启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
