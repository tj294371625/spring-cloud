package com.chinadaas.controller;

import com.chinadaas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lawliet
 * @version 1.0.0
 * @description TODO
 * @createTime 2021.11.22
 */
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/ok/{id}")
    public String ok(@PathVariable("id") String id) {
        return userService.ok(id);
    }

    @GetMapping("/timeout/{id}")
    public String timeout(@PathVariable("id") String id) {
        return userService.timeout(id);
    }


    /**
     * 通过开关控制方法报错，测试熔断专用
     *
     * @param id
     * @return
     */
    @GetMapping("/error_hystrix/{id}")
    public String errorWithHystrix(@PathVariable("id") String id) {
        return userService.errorWithHystrix(id);
    }

    /**
     * 测试线程池隔离
     *
     * @param id
     * @return
     */
    @GetMapping("/thread/{id}")
    public String threadIsolation(@PathVariable("id") String id) {
        return userService.threadIsolation(id);
    }

    @GetMapping("/feign_hystrix/{id}")
    public String feignWithHystrix(@PathVariable("id") String id) {
        return userService.feignWithHystrix(id);
    }
}
