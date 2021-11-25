package com.chinadaas.controller;

import com.chinadaas.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lawliet
 * @version 1.0.0
 * @description TODO
 * @createTime 2021.11.22
 */
@RequestMapping("/products")
@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/ok/{id}")
    public String ok(@PathVariable("id") String id) {
        return productService.ok(id);
    }

    @GetMapping("/timeout/{id}")
    public String timeout(@PathVariable("id") String id) {
        return productService.timeout(id);
    }

    @GetMapping("/error_hystrix/{id}")
    public String errorWithHystrix(@PathVariable("id") String id) {
        return productService.errorWithHystrix(id);
    }

    @GetMapping("/thread/{id}")
    public String threadIsolation(@PathVariable("id") String id) {
        return productService.threadIsolation(id);
    }

    @GetMapping("/feign_hystrix")
    public String feignWithHystrix(@RequestParam("id") String id) {
        return productService.feignWithHystrix(id);
    }
}
