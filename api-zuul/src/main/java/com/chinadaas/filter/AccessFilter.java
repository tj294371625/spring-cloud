package com.chinadaas.filter;

import com.chinadaas.exception.AccessException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lawliet
 * @version 1.0.0
 * @description TODO
 * @createTime 2021.11.26
 */
@Component
public class AccessFilter extends ZuulFilter {

    // zs: 过滤器的类型，pre表示前置过滤
    @Override
    public String filterType() {
        return "pre";
    }

    // zs: 过滤器执行的顺序
    @Override
    public int filterOrder() {
        return -Integer.MAX_VALUE;
    }

    // zs: 过滤器是否生效，false表示不生效
    @Override
    public boolean shouldFilter() {
        return true;
    }

    // zs: 过滤逻辑
    @Override
    public Object run() throws ZuulException {
        // zs: 实战中权限校验逻辑需要替换
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String parameter = request.getParameter("accessToken");
        if (StringUtils.isBlank(parameter)) {
            throw new AccessException(401, "无访问权限.");
        }

        return null;
    }
}
