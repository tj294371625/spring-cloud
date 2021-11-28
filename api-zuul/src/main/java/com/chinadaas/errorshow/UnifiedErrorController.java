package com.chinadaas.errorshow;

import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author lawliet
 * @version 1.0.0
 * @description TODO
 * @createTime 2021.11.27
 */
@Controller
public class UnifiedErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    @Autowired
    public UnifiedErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @GetMapping("/error")
    public ResponseEntity<Map<String, Object>> error() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        WebRequest webRequest = new ServletWebRequest(request);
        Map<String, Object> error = this.errorAttributes.getErrorAttributes(webRequest, true);
        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<>(error, HttpStatus.BAD_GATEWAY);
        return responseEntity;
    }

}
