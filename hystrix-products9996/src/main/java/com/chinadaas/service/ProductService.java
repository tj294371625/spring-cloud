package com.chinadaas.service;

/**
 * @author lawliet
 * @version 1.0.0
 * @description TODO
 * @createTime 2021.11.22
 */
public interface ProductService {

    String ok(String id);

    String timeout(String id);

    String errorWithHystrix(String id);

    String threadIsolation(String id);

    String feignWithHystrix(String id);
}
