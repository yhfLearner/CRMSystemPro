package com.hwua.exceptionhandler;

public class SysException extends RuntimeException {
    private String message = null;

    public SysException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
