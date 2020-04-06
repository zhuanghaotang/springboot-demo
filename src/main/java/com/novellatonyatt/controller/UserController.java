package com.novellatonyatt.controller;

import com.novellatonyatt.constants.PageVO;
import com.novellatonyatt.constants.Result;
import com.novellatonyatt.constants.ResultCode;
import com.novellatonyatt.constants.UserType;
import com.novellatonyatt.model.UserModel;
import com.novellatonyatt.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;

/**
 * @Author: Zhuang HaoTang
 * @Date: 2020/4/5 15:26
 * @Description:
 */
@RestController
@RequestMapping("user")
@CrossOrigin
@Api("用户")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(UserType.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                this.setValue(UserType.getByValue(Integer.parseInt(text)));
            }
        });
    }

    @ApiOperation("登录")
    @PostMapping("login")
    public Result login(@ApiParam("用户实体") @Validated UserModel userModel) {
        try {
            AuthenticationToken authenticationInfo = new UsernamePasswordToken(userModel.getUsername(), userModel.getPassword());
            Subject subject = SecurityUtils.getSubject();
            subject.login(authenticationInfo);
            return new Result(ResultCode.SUCCESS);
        } catch (Exception e) {
            log.error("用户登录失败，用户名：{}，密码：{}", userModel.getUsername(), userModel.getPassword(), e);
        }
        return new Result(ResultCode.FAIL);
    }

    @ApiOperation("注册")
    @PostMapping("register")
    public Result register(@ApiParam("用户实体") @Validated UserModel userModel) {
        try {
            if (userService.getUserByUsername(userModel.getUsername()) != null) {
                return new Result<>(ResultCode.FAIL, String.format("已存在用户名：%s", userModel.getUsername()));
            }
            userService.register(userModel);
            return new Result(ResultCode.SUCCESS);
        } catch (Exception e) {
            log.error("注册失败：", e);
        }
        return new Result(ResultCode.FAIL);
    }

    @ApiOperation("分页获取用户")
    @GetMapping("getUserList")
    @RequiresPermissions("sys:user:list")
    public Result<PageVO<UserModel>> getUserList(@ApiParam("第几页") @RequestParam(defaultValue = "1") Integer pageNo, @ApiParam("每页显示多少条") @RequestParam(defaultValue = "15") Integer pageSize) {
        PageVO<UserModel> pageVO = userService.getUserList(pageNo, pageSize);
        return new Result<>(ResultCode.SUCCESS, pageVO);
    }
}
