package com.chinadaas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/*******************************************************************************
 * - Copyright (c)  2021  chinadaas.com
 * - File Name: UsersController
 * - @author: liubc - Initial implementation
 * - Description:
 *
 * - Function List:
 *
 * - History:
 * Date         Author          Modification
 * 2021/6/2      liubc           Create the current class
 *******************************************************************************/
@RestController
public class UsersController {

    private final RestTemplate restTemplate;

    @Autowired
    public UsersController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/user/findAll")
    public Map<String, Object> findAll() {

        final String PRODUCT_URL = "http://products9998/product/findAll";

        return restTemplate.getForObject(PRODUCT_URL, Map.class);
    }
}
