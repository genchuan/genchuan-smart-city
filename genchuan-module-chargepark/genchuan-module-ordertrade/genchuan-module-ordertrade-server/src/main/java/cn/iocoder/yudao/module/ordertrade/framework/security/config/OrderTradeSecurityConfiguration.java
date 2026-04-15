package cn.iocoder.yudao.module.ordertrade.framework.security.config;

import cn.iocoder.yudao.framework.security.config.AuthorizeRequestsCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

/**
 * 订单交易模块 - Security 配置
 *
 * @author genchuan
 */
@Configuration(proxyBeanMethods = false)
public class OrderTradeSecurityConfiguration {

    @Bean("orderTradeAuthorizeRequestsCustomizer")
    public AuthorizeRequestsCustomizer authorizeRequestsCustomizer() {
        return new AuthorizeRequestsCustomizer() {
            @Override
            public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
                // 放行所有 /ordertrade/** 的访问，具体权限由 @PreAuthorize 控制
                registry.requestMatchers("/ordertrade/**").authenticated();
            }
        };
    }

}
