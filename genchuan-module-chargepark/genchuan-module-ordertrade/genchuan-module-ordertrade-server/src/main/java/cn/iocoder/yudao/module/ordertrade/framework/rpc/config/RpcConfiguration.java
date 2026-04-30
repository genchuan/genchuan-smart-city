package cn.iocoder.yudao.module.ordertrade.framework.rpc.config;

import cn.iocoder.yudao.module.ordertrade.rpc.stationresource.StationInfoFeignClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = StationInfoFeignClient.class)
public class RpcConfiguration {
}
