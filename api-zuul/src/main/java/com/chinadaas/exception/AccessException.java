package com.chinadaas.exception;

/**
 * @author lawliet
 * @version 1.0.0
 * @description TODO
 * @createTime 2021.11.27
 */
public class AccessException extends RuntimeException {

    private final int code;
    private final String msg;

    public AccessException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
