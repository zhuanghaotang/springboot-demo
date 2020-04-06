package com.novellatonyatt.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Zhuang HaoTang
 * @Date: 2020/4/5 22:48
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("角色")
@TableName("role")
public class RoleModel {

    @ApiModelProperty("记录唯一标识")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("角色英文名称")
    private String ename;

    @ApiModelProperty("创建时间")
    private String createTime;

    @ApiModelProperty("删除标识")
    private Boolean delFlag;
}
