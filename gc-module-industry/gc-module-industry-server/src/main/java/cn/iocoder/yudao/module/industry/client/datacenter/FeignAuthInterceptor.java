package cn.iocoder.yudao.module.industry.client.datacenter;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class FeignAuthInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        // TODO: 实际应该从当前请求上下文获取 token 和 tenantId
        String token = "test1";
        String tenantId = "1";

        template.header("Authorization", "Bearer " + token);
        template.header("tenant-id", tenantId);
    }
}
