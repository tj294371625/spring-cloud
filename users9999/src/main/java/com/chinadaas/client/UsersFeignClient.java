package com.chinadaas.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/*******************************************************************************
 * - Copyright (c)  2021  chinadaas.com
 * - File Name: UsersFeignClient
 * - @author: liubc - Initial implementation
 * - Description:
 *
 * - Function List:
 *
 * - History:
 * Date         Author          Modification
 * 2021/6/2      liubc           Create the current class
 *******************************************************************************/
@Component
@FeignClient("products9998")
public interface UsersFeignClient {

    @GetMapping("/product/findAll")
    Map<String, Object> findAll();

    @GetMapping("/product/findById")
    Map<String, Object> findById(@RequestParam("id") String aaa);

    @PostMapping("/product/createProduct")
    Map<String, Object> createProduct(@RequestBody Map<String, Object> product);

    @PostMapping("/product/saveName")
    public Map<String, Object> saveName(@RequestParam("productName") String aabb);
}
