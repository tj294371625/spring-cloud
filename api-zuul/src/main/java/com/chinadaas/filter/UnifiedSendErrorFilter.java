package com.chinadaas.filter;

import com.alibaba.fastjson.JSON;
import com.chinadaas.exception.AccessException;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lawliet
 * @version 1.0.0
 * @description TODO
 * @createTime 2021.11.28
 */
@Component
public class UnifiedSendErrorFilter extends SendErrorFilter {

    public static final String CODE = "code";
    public static final String MSG = "msg";

    // zs: 使用这种方式，需要在配置中禁用原有的SendErrorFilter

    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();

        Throwable throwable = currentContext.getThrowable();
        Throwable cause = throwable.getCause();
        Map<String, Object> errorResponse = new HashMap<>();
        if (cause instanceof AccessException) {
            errorResponse.put(CODE, ((AccessException) cause).getCode());
            errorResponse.put(MSG, ((AccessException) cause).getMsg());
        } else {
            errorResponse.put(CODE, 500);
            errorResponse.put(MSG, "系统内部错误，请联系管理人员.");
        }

        HttpServletResponse response = currentContext.getResponse();
        try {
            // 200代表正常响应
            response.setStatus(200);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getOutputStream().write(JSON.toJSONBytes(errorResponse));
        } catch (IOException e) {
            // zs: 此处会抛出异常，需要进一步处理
            throw new RuntimeException();
        }

        return null;
    }
}
