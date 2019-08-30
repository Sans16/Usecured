package com.supernova.shared.base.base;

public class BaseResponse<T> {

    private boolean status;
    private T data;

    public boolean isStatus() {return status;}

    public T getData() {return data;}
}
