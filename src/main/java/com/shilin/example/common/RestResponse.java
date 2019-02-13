package com.shilin.example.common;

import java.io.Serializable;


public class RestResponse<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String message;

    protected T data;

    protected String bizCode;

    public RestResponse() {
    }

    public RestResponse(String bizCode, String message, T data) {
        super();
        this.bizCode = bizCode;
        this.message = message;
        this.data = data;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

