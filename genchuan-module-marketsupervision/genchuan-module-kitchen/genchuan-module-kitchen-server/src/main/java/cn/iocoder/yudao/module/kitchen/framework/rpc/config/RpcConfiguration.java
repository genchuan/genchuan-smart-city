package cn.iocoder.yudao.module.kitchen.framework.rpc.config;

import cn.iocoder.yudao.module.stationresource.api.stationresource.AreaInfoApi;
import cn.iocoder.yudao.module.stationresource.api.stationresource.ParkingSpaceInfoApi;
import cn.iocoder.yudao.module.stationresource.api.stationresource.StationInfoApi;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * RPC 远程调用配置 —— 注册本模块需要调用的 Feign 客户端
 * <p>使用新 api/stationresource 下的完整版接口
 */
@Configuration(value = "kitchenRpcConfiguration", proxyBeanMethods = false)
@EnableFeignClients(clients = {
        StationInfoApi.class,
        ParkingSpaceInfoApi.class,
        AreaInfoApi.class
})
public class RpcConfiguration {
}
