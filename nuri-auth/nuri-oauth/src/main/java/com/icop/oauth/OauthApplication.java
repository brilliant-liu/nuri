package com.icop.oauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author: liukj
 * @date: 2020/6/7
 * @description：
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.icop.schema.dao")
@ComponentScan("com.icop")
public class OauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthApplication.class,args);
    }
}
