package com.chinadaas.service.impl;

import com.chinadaas.client.ProductRemoteCallClient;
import com.chinadaas.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author lawliet
 * @version 1.0.0
 * @description Hystrix配置全部参数   https://github.com/Netflix/Hystrix/wiki/Configuration
 * @createTime 2021.11.22
 */
@DefaultProperties(defaultFallback = "defaultFallback")
@Service
public class UserServiceImpl implements UserService {

    private final RestTemplate restTemplate;
    private final ProductRemoteCallClient client;

    @Autowired
    public UserServiceImpl(RestTemplate restTemplate, ProductRemoteCallClient client) {
        this.restTemplate = restTemplate;
        this.client = client;
    }

    @HystrixCommand(
            commandProperties = {
                    // zs：一次服务耗时1s，将超时设置为2s(默认1s)，观察线程池资源耗尽(默认10个)、任务队列满了之后的熔断现象
                    // zs：结果是除了拿到线程的10个请求成功了，其余的都因为熔断出现降级提示了
                    // zs：所以结论就是，使用Hystrix的时候，一定要重新配置Hystrix中的线程池(这个资源池是共享的)，把coreSize提升上去，10个线程很容易达到瓶颈从而熔断
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            }
    )
    @Override
    public String ok(String id) {
        return restTemplate.getForObject("http://HYSTRIX-PRODUCTS9996/products/ok/{1}", String.class, id);
    }

    // zs: 在HystrixCommandProperties中寻找配置属性
    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            },
            fallbackMethod = "fallbackTimeout"
    )
    @Override
    public String timeout(String id) {
        return restTemplate.getForObject("http://HYSTRIX-PRODUCTS9996/products/timeout/{1}", String.class, id);
    }

    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 启用熔断，默认是开启熔断的
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 10秒内多少个请求才会起作用
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 熔断窗口时间
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), // 错误阈值百分比
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000") // 超时时间
            }
    )
    @Override
    public String errorWithHystrix(String id) {
        return restTemplate.getForObject("http://HYSTRIX-PRODUCTS9996/products/error_hystrix/{1}", String.class, id);
    }

    @HystrixCommand(
            groupKey = "testThreadIsolation",
            commandKey = "threadIsolation",
            threadPoolKey = "a",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "5"), // 核心线程数
                    @HystrixProperty(name = "maxQueueSize", value = "10"), // 任务队列中任务数
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "5") // 队列大小拒绝阈值，可以动态调整
            }
    )
    @Override
    public String threadIsolation(String id) {
        ResponseEntity<String> response = restTemplate
                .getForEntity("http://HYSTRIX-PRODUCTS9996/products/thread/{1}", String.class, id);
        return response.getBody();
    }

    @Override
    public String feignWithHystrix(String id) {
        return client.feignWithHystrix(id);
    }

    private String fallbackTimeout(String id) {
        return "id: " + id + ", 执行超时";
    }

    // zs：因为是全局通用的降级提示，使用这个fallback方法的service方法参数一定是各式各样的，所以不能加参数列表
    private String defaultFallback() {
        return "执行过程中发生异常";
    }
}
