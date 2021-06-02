package com.chinadaas.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
        log.info("查询所有商品成功, 服务端口: {}", port);

        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "服务调用成功, 服务所提供端口: " + port);

        return result;
    }
}
