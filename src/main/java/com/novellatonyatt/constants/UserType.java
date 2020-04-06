package com.novellatonyatt.constants;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Author: Zhuang HaoTang
 * @Date: 2020/4/5 23:47
 * @Description:
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserType implements BaseEnum {

    C_SIDE(1, "C端用户"), B_SIDE(2, "B端用户");

    @EnumValue
    private int value;

    private String name;

    UserType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @JsonCreator
    public static UserType getByValue(int value) {
        for (UserType userType : UserType.values()) {
            if (userType.getValue() == value) {
                return userType;
            }
        }
        return null;
    }
}
