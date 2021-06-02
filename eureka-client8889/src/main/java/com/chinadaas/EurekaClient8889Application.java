package com.chinadaas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EurekaClient8889Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClient8889Application.class, args);
    }

}
