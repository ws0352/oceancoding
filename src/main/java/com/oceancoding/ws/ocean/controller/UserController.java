package com.oceancoding.ws.ocean.controller;

import com.oceancoding.ws.ocean.bean.OceanUser;
import com.oceancoding.ws.ocean.exceptionHandler.BizException;
import com.oceancoding.ws.ocean.exceptionHandler.GlobalExceptionHandler;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "用户管理相关接口")
@RequestMapping("/user")
public class UserController {
    private static final Logger logger =  LoggerFactory.getLogger(UserController.class);
    @PostMapping("/")
    @ApiOperation("用户注册的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", defaultValue = "请输入用户名", required = true),
            @ApiImplicitParam(name = "email", value = "邮箱", defaultValue = "请输入邮箱"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "form")
    })
    public boolean registerUser(@RequestParam(required = true)String username, String email, @RequestParam(required = true)String password) {
        logger.info("用户开始注册");
        //如果用户名为空就手动抛出一个自定义的异常！
//        if(user.getUserName()==null){
//            throw  new BizException("-1","用户不能为空！");
//        }
        
        return true;
    }

}
