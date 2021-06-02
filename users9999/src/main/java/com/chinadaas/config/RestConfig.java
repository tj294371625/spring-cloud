package com.chinadaas.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/*******************************************************************************
 * - Copyright (c)  2021  chinadaas.com
 * - File Name: RestConfig
 * - @author: liubc - Initial implementation
 * - Description:
 *
 * - Function List:
 *
 * - History:
 * Date         Author          Modification
 * 2021/6/2      liubc           Create the current class
 *******************************************************************************/
@Configuration
public class RestConfig {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
