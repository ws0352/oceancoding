package com.oceancoding.ws.ocean.controller;

import com.oceancoding.ws.ocean.bean.OceanUser;
import com.oceancoding.ws.ocean.exceptionHandler.BizException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UserController {
    @PostMapping("/user")
    public boolean insert(@RequestBody OceanUser user) {
        System.out.println("开始新增...");
        //如果姓名为空就手动抛出一个自定义的异常！
        if(user==null){
            throw  new BizException("-1","用户不能为空！");
        }
        return true;
    }

}
