package com.novellatonyatt.constants;

/**
 * @Author: Zhuang HaoTang
 * @Date: 2020/4/5 15:36
 * @Description:
 */
public enum ResultCode {

    SUCCESS("200", "请求成功"),
    FAIL("500", "业务异常"),
    ;

    private String code;

    private String name;

    ResultCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
