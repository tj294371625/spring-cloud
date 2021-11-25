package com.chinadaas.client;

/**
 * @author lawliet
 * @version 1.0.0
 * @description TODO
 * @createTime 2021.11.25
 */
public class ErrorInfo {

    /**
     * 以下字段对应feign调用失败的响应体信息
     */
    private Long timestamp;
    private Integer status;
    private String error;
    private String trace;
    private String message;
    private String path;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
