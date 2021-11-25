package com.chinadaas.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author lawliet
 * @version 1.0.0
 * @description TODO
 * @createTime 2021.11.25
 */
@Configuration
public class ProductRemoteCallErrorConfiguration {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new RemoteCallErrorDecoder();
    }

    @Slf4j
    private static class RemoteCallErrorDecoder implements ErrorDecoder {

        @Override
        public Exception decode(String s, Response response) {

            try {
                if (Objects.nonNull(response.body())) {
                    String body = Util.toString(response.body().asReader(StandardCharsets.UTF_8));
                    log.error("feign调用失败，响应信息:{}", body);
                    ErrorInfo errorInfo = JSON.parseObject(body, new TypeReference<ErrorInfo>() {});
                    return packageException(errorInfo);
                }

                return new RemoteCallException(500, "系统内部错误，请联系管理员");
            } catch (IOException e) {
                log.error("feign调用失败，响应解析异常", e);
                return new RemoteCallException(500, "系统内部错误，请联系管理员");
            }

        }

        private RemoteCallException packageException(ErrorInfo errorInfo) {
            String message = errorInfo.getMessage();
            if (!Objects.equals("No message available", message)) {
                return new RemoteCallException(500, message);
            }

            return new RemoteCallException(500, "系统内部错误，请联系管理员");
        }

    }

}
