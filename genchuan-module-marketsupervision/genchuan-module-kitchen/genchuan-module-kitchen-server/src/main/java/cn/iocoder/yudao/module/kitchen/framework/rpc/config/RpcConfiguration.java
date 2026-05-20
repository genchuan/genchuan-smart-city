package cn.iocoder.yudao.module.kitchen.framework.rpc.config;

import cn.iocoder.yudao.module.stationresource.api.parking.ParkingSpaceInfoApi;
import cn.iocoder.yudao.module.stationresource.api.station.StationInfoApi;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * RPC 远程调用配置 —— 注册本模块需要调用的 Feign 客户端
 */
@Configuration(value = "kitchenRpcConfiguration", proxyBeanMethods = false)
@EnableFeignClients(clients = {
        StationInfoApi.class,
        ParkingSpaceInfoApi.class
})
public class RpcConfiguration {
}
