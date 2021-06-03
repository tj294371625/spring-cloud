package com.chinadaas.controller;

import com.chinadaas.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/*******************************************************************************
 * - Copyright (c)  2021  chinadaas.com
 * - File Name: ProductsController
 * - @author: liubc - Initial implementation
 * - Description:
 *
 * - Function List:
 *
 * - History:
 * Date         Author          Modification
 * 2021/6/2      liubc           Create the current class
 *******************************************************************************/
@Slf4j
@RestController
public class ProductsController {

    @Value("${server.port}")
    private int port;

    @GetMapping("/product/findAll")
    public Map<String, Object> findAll() {

        try {
            // zs: openfeign调用，默认超时时间是1秒，我们让线程睡眠2秒，观察调用结果
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("查询所有商品成功, 服务端口: {}", port);

        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "服务调用成功, 服务所提供端口: " + port);

        return result;
    }

    @GetMapping("/product/findById")
    public Map<String, Object> findById(@RequestParam("id") String productId) {
        log.info("查询成功, 服务端口为: {}, 商品Id为: {}", port, productId);

        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "服务调用成功, 服务所提供端口: " + port);

        return result;
    }

    @PostMapping("/product/createProduct")
    public Map<String, Object> createProduct(@RequestBody Product product) {
        log.info("创建成功, 服务端口为: {}, 商品信息为: {}", port, product);

        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "服务调用成功, 服务所提供端口: " + port);

        return result;
    }

    @PostMapping("/product/saveName")
    public Map<String, Object> saveName(String productName) {
        log.info("保存成功, 服务端口为: {}, 商品名称为: {}", port, productName);

        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "服务调用成功, 服务所提供端口: " + port);

        return result;
    }
}
