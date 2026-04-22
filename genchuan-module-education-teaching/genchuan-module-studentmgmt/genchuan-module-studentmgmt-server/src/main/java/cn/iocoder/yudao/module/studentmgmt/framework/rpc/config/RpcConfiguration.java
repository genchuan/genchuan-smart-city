package cn.iocoder.yudao.module.studentmgmt.framework.rpc.config;

import cn.iocoder.yudao.module.system.api.dept.DeptApi;
import cn.iocoder.yudao.module.system.api.dict.DictDataApi;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "studentRpcConfiguration", proxyBeanMethods = false)
@EnableFeignClients(clients = {DeptApi.class, DictDataApi.class})
public class RpcConfiguration {
}
