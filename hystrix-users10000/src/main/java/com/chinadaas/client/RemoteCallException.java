package com.chinadaas.client;

public class RemoteCallException extends RuntimeException {

    private String msg;
    private int code;

    public RemoteCallException(int code, String msg) {
        super();
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}