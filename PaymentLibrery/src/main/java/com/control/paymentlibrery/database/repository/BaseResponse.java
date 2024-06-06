package com.control.paymentlibrery.database.repository;

public class BaseResponse {
    private String message;
    private String code;
    private String response;

    public BaseResponse() {
        this.message = "Error";
        this.code = "400";
    }

    public BaseResponse(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public BaseResponse(String message, String code, String response) {
        this.message = message;
        this.code = code;
        this.response = response;
    }

    public BaseResponse(String message) {
        this.message = message;
        this.code = "400";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
