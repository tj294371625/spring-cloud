package com.chinadaas.controller;

import com.chinadaas.client.UsersFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    private static final String PRODUCT_URL = "http://products9998/product/findAll";
    private final RestTemplate restTemplate;
    private final UsersFeignClient feignClient;

    @Autowired
    public UsersController(RestTemplate restTemplate, UsersFeignClient feignClient) {
        this.restTemplate = restTemplate;
        this.feignClient = feignClient;
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/user/findAll")
    public Map<String, Object> findAll() {
        return restTemplate.getForObject(PRODUCT_URL, Map.class);
    }

    @GetMapping("/user/feign/findAll")
    public Map<String, Object> findAllForFeignClient() {
        return feignClient.findAll();
    }

    @GetMapping("/user/feign/findById")
    public Map<String, Object> findByIdForFeignClient(String id) {
        return feignClient.findById(id);
    }

    @PostMapping("/user/feign/createProduct")
    public Map<String, Object> createProductForFeignClient(@RequestBody Map<String, Object> product) {
        return feignClient.createProduct(product);
    }

    @GetMapping("/user/feign/saveName")
    public Map<String, Object> saveNameForFeignClient(String name) {
        return feignClient.saveName(name);
    }
}
