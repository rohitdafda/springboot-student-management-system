package com.rohitdafda.springbootstudentmanagementsystem.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GlobalResponse<T> {
    private T data;
    private String message;
    private int successCode;

    public GlobalResponse() {
    }

    public GlobalResponse(T data, String message, int successCode) {
        this.data = data;
        this.message = message;
        this.successCode = successCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(int successCode) {
        this.successCode = successCode;
    }
}
