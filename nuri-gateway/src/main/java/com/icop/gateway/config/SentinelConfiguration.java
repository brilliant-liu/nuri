package com.icop.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.Collections;
import java.util.List;

/**
 * @author: liukj
 * @date: 2020/7/2
 * @description：
 */
@Configuration
public class SentinelConfiguration {

    private List<ViewResolver> viewResolvers;
    private ServerCodecConfigurer serverCodecConfigurer;

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }

    public SentinelConfiguration(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                 ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    /*@Bean//自定义异常
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public R sentinelGatewayBlockExceptionHandler() {
        // Register the block exception handler for Spring Cloud Gateway.
        return new ExceptionHandler(viewResolvers, serverCodecConfigurer);
    }*/
}
