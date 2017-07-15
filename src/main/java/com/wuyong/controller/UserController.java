package com.wuyong.controller;

import com.wuyong.common.ServerResponse;
import com.wuyong.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 坚果
 * on 2017/7/15
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    /**
     * 用户注册模块
     * @param user  用户对象
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ServerResponse userRegister(User user) {


        return null;
    }
}
