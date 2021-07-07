package com.zhulong.network.interceptor;

public class ApiException extends RuntimeException {
    private int errorCode;
    private String msg;

    public ApiException(int code, String msg) {
        super(msg);
        this.errorCode = code;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMsg() {
        return msg;
    }
}
