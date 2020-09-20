package com.icop.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: liukj
 * @date: 2020/6/9
 * @description：
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.icop")
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class,args);
    }
}
