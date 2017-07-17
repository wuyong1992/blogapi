package com.wuyong.controller;

import com.wuyong.common.ServerResponse;
import com.wuyong.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by 坚果
 * on 2017/7/15
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 用户注册模块
     *
     * @param user 用户对象
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ServerResponse userRegister(User user, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");

        return null;
    }


    @RequestMapping(value = "test", method = RequestMethod.POST)
    public ServerResponse test(User user, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        logger.info("user:", user);
        if (StringUtils.isBlank(user.getUsername())) {
            return ServerResponse.createByErrorMessage("user为空");
        }
        return ServerResponse.createBySuccess(user);
    }
}
