package cn.iocoder.yudao.module.ordertrade.framework.rpc.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "cn.iocoder.yudao.module.ordertrade.rpc")
public class RpcConfiguration {
}
