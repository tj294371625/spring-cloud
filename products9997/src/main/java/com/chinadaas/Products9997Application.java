package com.chinadaas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Products9997Application {

    public static void main(String[] args) {
        SpringApplication.run(Products9997Application.class, args);
    }

}
