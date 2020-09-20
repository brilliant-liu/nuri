package com.icop.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

/**
 * @author: liukj
 * @date: 2020/6/12
 * @description： 跨站式请求
 */
@Configuration
public class MyCorsConfiguration {

    @Bean
    public CorsConfiguration corsConfiguration(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.applyPermitDefaultValues();
        return corsConfiguration;
    }

}
