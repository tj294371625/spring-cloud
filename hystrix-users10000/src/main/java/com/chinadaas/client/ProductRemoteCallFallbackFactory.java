package com.chinadaas.client;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;


/**
 * @author lawliet
 * @version 1.0.0
 * @description TODO
 * @createTime 2021.11.25
 */
@Component
public class ProductRemoteCallFallbackFactory implements FallbackFactory<ProductRemoteCallClient> {

    // zs: 创建一个处理错误的远程调用客户端，这个fallbackFactory配合errorDecoder来实现降级相关的逻辑
    @Override
    public ProductRemoteCallClient create(Throwable throwable) {
        String message = throwable.getMessage();

        return id -> {
            // zs: 被调用端触发的异常，我们会在errorDecoder中做解析处理，统一将其转换为RemoteCallException
            if (throwable instanceof RemoteCallException) {
                return ((RemoteCallException) throwable).getMsg();
            } else {
                // zs: 调用端触发的Hystrix相关异常(跟其它异常无关)在此处理
                // zs: 处理超时异常
                if (throwable instanceof HystrixTimeoutException) {
                    return "调用超时异常.";
                }

                // zs: 处理熔断
                if (throwable instanceof RuntimeException
                        && Objects.equals("Hystrix circuit short-circuited and is OPEN", message)) {
                    return "断路器已打开.";
                }

                return message;
            }
        };

    }

}
