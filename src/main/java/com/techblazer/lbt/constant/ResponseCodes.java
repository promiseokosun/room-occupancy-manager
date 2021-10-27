package com.techblazer.lbt.constant;

public enum ResponseCodes {

    SUCCESSFUL("I00", "Successful");

    private String code;
    private String message;

    ResponseCodes(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ResponseCodes{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
