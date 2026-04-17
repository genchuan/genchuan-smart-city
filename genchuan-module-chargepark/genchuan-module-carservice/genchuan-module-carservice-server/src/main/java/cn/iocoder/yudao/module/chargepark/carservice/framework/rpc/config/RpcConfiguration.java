package cn.iocoder.yudao.module.chargepark.carservice.framework.rpc.config;

import cn.iocoder.yudao.module.system.api.notify.NotifyMessageSendApi;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * carservice 模块的 Feign RPC 配置
 *
 * 显式声明需要使用的 RPC 接口，让 Spring Cloud OpenFeign 注入对应的代理 bean
 */
@Configuration(value = "carserviceRpcConfiguration", proxyBeanMethods = false)
@EnableFeignClients(clients = {
        AdminUserApi.class,
        NotifyMessageSendApi.class
})
public class RpcConfiguration {
}
