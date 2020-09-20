package com.icop.testservices;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * @author: liukj
 * @date: 2020/6/5
 * @description：
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.icop.schema.dao")
@ComponentScan("com.icop.*")
public class TestServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestServicesApplication.class,args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
