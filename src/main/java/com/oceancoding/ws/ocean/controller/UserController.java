package com.oceancoding.ws.ocean.controller;

import com.oceancoding.ws.ocean.bean.OceanUser;

import com.oceancoding.ws.ocean.responseData.GlobalResultBody;
import com.oceancoding.ws.ocean.service.OceanUserServiceImpl;
import com.oceancoding.ws.ocean.utils.IdWorker;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(description = "用户管理相关接口")
@RequestMapping("/user")
public class UserController {
    //日志
    private static final Logger logger =  LoggerFactory.getLogger(UserController.class);

    @Autowired
    OceanUserServiceImpl oceanUserService;

    @PostMapping("/")
    @ApiOperation("用户注册的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", defaultValue = "请输入用户名", required = true),
            @ApiImplicitParam(name = "email", value = "邮箱", defaultValue = "请输入邮箱"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "form")
    })
    public boolean registerUser(@RequestParam(required = true)String username, String email, @RequestParam(required = true)String password) {
        logger.info("用户开始注册");
        OceanUser oceanUser = null;
        IdWorker idWorker = new IdWorker();
        oceanUser.setId(idWorker.nextId());
        oceanUser.setEmail(email);
        oceanUser.setUserName(username);
        oceanUser.setPassword(password);
        oceanUserService.insertUser(oceanUser);
        //如果用户名为空就手动抛出一个自定义的异常！
//        if(user.getUserName()==null){
//            throw  new BizException("-1","用户不能为空！");
//        }
        logger.info("注册成功", oceanUser.getId(),oceanUser.getEmail(),oceanUser.getUserName(),oceanUser.getPassword());

        return true;
    }
//    @Autowired
//    private RedisUtils redisUtils;
//    @RequestMapping(value = "/hello/{id}")
//    public String hello(@PathVariable(value = "id") String id){
//        //查询缓存中是否存在
//        boolean hasKey = redisUtils.exists(id);
//        String str = "";
//        if(hasKey){
//            //获取缓存
//            Object object =  redisUtils.get(id);
//            log.info("从缓存获取的数据"+ object);
//            str = object.toString();
//        }else{
//            //从数据库中获取信息
//            log.info("从数据库中获取数据");
//            str = testService.test();
//            //数据插入缓存（set中的参数含义：key值，user对象，缓存存在时间10（long类型），时间单位）
//            redisUtils.set(id,str,10L,TimeUnit.MINUTES);
//            log.info("数据插入缓存" + str);
//        }
//        return str;
//    }

    @GetMapping("/")
    @ResponseBody
    public GlobalResultBody get(){
        OceanUser oceanUser = new OceanUser();
        oceanUser.setUserName("ss");
        oceanUser.setPassword("12356");
        return GlobalResultBody.success(oceanUser);

    }
}
