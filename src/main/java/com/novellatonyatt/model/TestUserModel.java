package com.novellatonyatt.model;

import com.novellatonyatt.constants.UserType;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Zhuang HaoTang
 * @create: 2020-04-10 16:25
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("系统用户")
public class TestUserModel {

    private String id;

    private String username;

    private String password;

    private UserType userType;

    private Boolean delFlag;

    private long price;
}
