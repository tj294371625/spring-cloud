package com.chinadaas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsulClient8890Application {

    public static void main(String[] args) {
        SpringApplication.run(ConsulClient8890Application.class, args);
    }

}
