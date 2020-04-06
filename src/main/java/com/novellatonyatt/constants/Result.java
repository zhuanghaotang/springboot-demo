package com.novellatonyatt.constants;

import lombok.Data;

/**
 * @Author: Zhuang HaoTang
 * @Date: 2020/4/5 15:35
 * @Description:
 */
@Data
public class Result<T> {

    /**
     * 响应结果状态码
     */
    private String code;

    /**
     * 响应结果描述
     */
    private String message;

    /**
     * 数据源
     */
    private T data;

    public Result(ResultCode resultCode,  T data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getName();
        this.data = data;
    }

    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getName();
    }

    public Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
