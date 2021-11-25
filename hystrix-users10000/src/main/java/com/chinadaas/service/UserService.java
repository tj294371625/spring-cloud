package com.chinadaas.service;

/**
 * @author lawliet
 * @version 1.0.0
 * @description TODO
 * @createTime 2021.11.22
 */
public interface UserService {

    String ok(String id);

    String timeout(String id);

    /**
     * 通过开关控制方法报错，测试熔断专用
     *
     * @param id
     * @return
     */
    String errorWithHystrix(String id);

    String threadIsolation(String id);

    String feignWithHystrix(String id);
}
