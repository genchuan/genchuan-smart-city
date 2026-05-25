package cn.iocoder.yudao.module.ordertrade.framework.rpc.config;

import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "ordertradeRpcConfiguration", proxyBeanMethods = false)
@EnableFeignClients(clients = {
        AdminUserApi.class
})
public class RpcConfiguration {
}
