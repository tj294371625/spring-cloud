package com.chinadaas.errorshow;

import com.chinadaas.exception.AccessException;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lawliet
 * @version 1.0.0
 * @description TODO
 * @createTime 2021.11.27
 */
@Component
public class UnifiedErrorAttribute extends DefaultErrorAttributes {
    public static final String CODE = "code";
    public static final String MSG = "msg";

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Throwable error = this.getError(webRequest);
        Throwable cause = error.getCause();

        Map<String, Object> errorResponse = new HashMap<>();
        if (cause instanceof AccessException) {
            errorResponse.put(CODE, ((AccessException) cause).getCode());
            errorResponse.put(MSG, ((AccessException) cause).getMsg());
        } else {
            errorResponse.put(CODE, 500);
            errorResponse.put(MSG, "系统内部错误，请联系管理人员.");
        }

        return errorResponse;
    }
}
