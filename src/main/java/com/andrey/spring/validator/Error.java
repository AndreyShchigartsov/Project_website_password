package com.andrey.spring.validator;

//@Builder
public class Error {
    public Error(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private String code;
    private String message;
}
