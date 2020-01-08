package com.ftp.common;

import lombok.Getter;

/**
 * @Author ZhouBing
 * @Date 2019/12/4 15:02
 * @Version 1.0
 **/
@Getter
public enum ResultCode {
    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    NEED_LOGIN(10,"NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT");

    private final int code;
    private final String desc;

    ResultCode(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

}
