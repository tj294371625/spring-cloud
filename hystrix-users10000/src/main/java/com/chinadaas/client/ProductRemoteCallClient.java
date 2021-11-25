package com.chinadaas.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lawliet
 * @version 1.0.0
 * @description TODO
 * @createTime 2021.11.25
 */
@FeignClient(
        name = "hystrix-products9996", // 要调用的服务名称，忽略大小写
        fallbackFactory = ProductRemoteCallFallbackFactory.class,
        configuration = ProductRemoteCallErrorConfiguration.class
)
public interface ProductRemoteCallClient {

    @GetMapping("/products/feign_hystrix")
    String feignWithHystrix(@RequestParam("id") String id);
}
