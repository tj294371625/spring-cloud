package com.chinadaas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
public class HystrixUsers10000Application {

    public static void main(String[] args) {
        SpringApplication.run(HystrixUsers10000Application.class, args);
    }

}
