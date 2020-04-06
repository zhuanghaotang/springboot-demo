package com.novellatonyatt.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.novellatonyatt.constants.UserType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: Zhuang HaoTang
 * @Date: 2020/4/5 15:22
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("系统用户")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserModel {

    @ApiModelProperty("记录唯一标识")
    private Integer id;

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空或者空字符串")
    @Length(max = 20,message = "用户名长度不能大于20")
    private String username;

    @ApiModelProperty("密码")
    @NotBlank
    private String password;

    @ApiModelProperty("用户类型")
    private UserType userType;
}
