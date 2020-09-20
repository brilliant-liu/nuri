package com.icop.gateway.config;

import com.icop.base.entities.WhiteList;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: liukj
 * @date: 2020/6/11
 * @description：
 */
@Configuration
public class RequestPathConvert {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {


        return  builder.routes()
                .route("unoauth", r ->r.path("/nuri/un/**")
                        .filters(f->f.rewritePath("/nuri/un", WhiteList.UN_AUTH_REQUEST_PREFIX_PATH))
                        .uri("lb://nuri-oauth"))
                .route("oauth", r ->r.path("/nuri/on/**")
                        .filters(f->f.rewritePath("/nuri/on/","/oauth/"))
//                        .circuitBreaker(cb -> cb.setName("myCircuitBreaker")
//                                .setFallbackUri("forward:/nuri/un/fallback"))
//                                .rewritePath("/nuri/on","/nuri/un"))
                        .uri("lb://nuri-oauth"))
                .build();


   //
        // /nuri/un/login/login -- > /unauth/login/login


         /*builder.routes()
                .route("path_route", r -> r.path("/get")
                        .uri("http://httpbin.org"))
                .route("host_route", r -> r.host("*.myhost.org")
                        .uri("http://httpbin.org"))
                .route("rewrite_route", r -> r.host("*.rewrite.org")
                        .filters(f -> f.rewritePath("/foo/(?<segment>.*)", "/${segment}"))
                        .uri("http://httpbin.org"))
                .route("hystrix_route", r -> r.host("*.hystrix.org")
                        .filters(f -> f.hystrix(c -> c.setName("slowcmd")))
                        .uri("http://httpbin.org"))
                .route("hystrix_fallback_route", r -> r.host("*.hystrixfallback.org")
                        .filters(f -> f.hystrix(c -> c.setName("slowcmd").setFallbackUri("forward:/hystrixfallback")))
                        .uri("http://httpbin.org"))
                .route("limit_route", r -> r
                        .host("*.limited.org").and().path("/anything/**")
                        .filters(f -> f.requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter())))
                        .uri("http://httpbin.org"))
                .build();*/
    }

}
