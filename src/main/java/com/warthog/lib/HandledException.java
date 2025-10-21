package com.warthog.lib;

public class HandledException extends RuntimeException {

    private String code;

    public HandledException(String code, String message) {
        super(message);
        this.code = code;
    }

    public HandledException(String code, String message, Throwable cause) {
        super(message, cause);
        this.setCode(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
