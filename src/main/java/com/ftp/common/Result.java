package com.ftp.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

import java.io.Serializable;

/**
 * @Author ZhouBing
 * @Date 2019/12/4 14:56
 * @Version 1.0
 **/
@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
@Getter
public class Result<T> implements Serializable {
    private int status;
    private String msg;
    private T data;

    private Result(int status){
        this.status = status;
    }
    private Result(int status, T data){
        this.status = status;
        this.data = data;
    }

    private Result(int status, String msg, T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private Result(int status, String msg){
        this.status = status;
        this.msg = msg;
    }

    @JsonIgnore
    //使之不在json序列化结果当中
    public boolean isSuccess(){
        return this.status == ResultCode.SUCCESS.getCode();
    }


    public static <T> Result<T> success(){
        return new Result<T>(ResultCode.SUCCESS.getCode());
    }

    public static <T> Result<T> success(String msg){
        return new Result<T>(ResultCode.SUCCESS.getCode(),msg);
    }

    public static <T> Result<T> success(T data){
        return new Result<T>(ResultCode.SUCCESS.getCode(),data);
    }

    public static <T> Result<T> success(String msg, T data){
        return new Result<T>(ResultCode.SUCCESS.getCode(),msg,data);
    }


    public static <T> Result<T> error(){
        return new Result<T>(ResultCode.ERROR.getCode(), ResultCode.ERROR.getDesc());
    }


    public static <T> Result<T> error(String errorMessage){
        return new Result<T>(ResultCode.ERROR.getCode(),errorMessage);
    }

    public static <T> Result<T> error(int errorCode, String errorMessage){
        return new Result<T>(errorCode,errorMessage);
    }

}
