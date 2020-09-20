package com.icop.nuri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: liukj
 * @date: 2020/6/6
 * @description：
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NuriNacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(NuriNacosApplication.class,args);
    }


}
