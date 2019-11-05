package com.upala.wong.entity;

import lombok.Data;

/********************************
 *  @program imageEngine
 *  @author upala
 *  @version 0.0.1
 *  @since 2019-11-05 17:36
 *  @description
 ********************************/

@Data
public class Result {

    public static String getResult(int code, String message, Object data) {
        return new Result(code, message, data).toString();
    }

    private Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private int code;
    private String message;
    private Object data;

}
