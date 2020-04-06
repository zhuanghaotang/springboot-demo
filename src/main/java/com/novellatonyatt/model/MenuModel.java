package com.novellatonyatt.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Zhuang HaoTang
 * @Date: 2020/4/5 22:59
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("菜单")
@TableName("menu")
public class MenuModel {

    @ApiModelProperty("记录唯一标识")
    @TableId(type = IdType.AUTO)
    private int id;

    @ApiModelProperty("父菜单ID")
    private int pid;

    @ApiModelProperty("菜单名称")
    private String menuName;

    @ApiModelProperty("菜单权限")
    private String permission;

    @ApiModelProperty("删除标识")
    private Boolean delFlag;

}
