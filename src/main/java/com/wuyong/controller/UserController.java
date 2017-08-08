package com.wuyong.controller;

import com.wuyong.common.Const;
import com.wuyong.common.ServerResponse;
import com.wuyong.pojo.User;
import com.wuyong.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 坚果
 * on 2017/7/15
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService iUserService;

    /**
     * 用户注册
     *
     * @param user          用户对象
     * @param bindingResult 错误信息
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ServerResponse userRegister(@RequestBody @Validated User user, BindingResult bindingResult) {
        logger.info("====获取注册user对象:" + "\t" + user);
        if (bindingResult.hasErrors()) {
//            bindingResult.getFieldErrors();
//            List<FieldError> errorList = bindingResult.getFieldErrors();
//            StringBuilder errorStr = new StringBuilder();
//            errorStr.append("错误信息：\n\r");
//            for (ObjectError error : errorList) {
//                errorStr.append(error.getDefaultMessage() + "\r\n");
//            }
            logger.info("校验错误信息====>" + bindingResult.getFieldError().getDefaultMessage());
            return ServerResponse.createByErrorMessage(bindingResult.getFieldError().getDefaultMessage());
        }
        return iUserService.userRegister(user);
    }

    /**
     * 校验手机号是否已被注册
     *
     * @param mobile
     * @return
     */
    @RequestMapping(value = "validateMobile")
    public Boolean validateMobile(String mobile) {
        return iUserService.validateMobile(mobile);
    }


    /**
     * 用户登录
     * 后期需要加入token验证
     *
     * @param mobile   用户登录使用手机号
     * @param password 密码
     * @return
     */
    @PostMapping(value = "login")
    public ServerResponse userLogin(String mobile, String password, HttpSession session) {
        ServerResponse serverResponse = iUserService.login(mobile, password);
        if (serverResponse.isSuccess()) {
            /*//将当前用户置入session
            session.setAttribute(Const.CURRENT_USER, serverResponse.getData());
            logger.info(Const.CURRENT_USER + serverResponse.getData());*/
        }
        return serverResponse;
    }

    /**
     * 判断是否登录
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "isLogin")
    public ServerResponse isLogin(HttpSession session) {
        logger.info("====是否登录请求已收到====");
        return iUserService.isLogin(session);
    }

    /**
     * 退出
     *
     * @param session 会话
     * @return
     */
    @RequestMapping(value = "logout")
    public ServerResponse logout(HttpSession session) {
        logger.info("======退出登录请求已收到====");
        return iUserService.logout(session);
    }

    /*@RequestMapping(value = "test", method = RequestMethod.POST)
    public ServerResponse test(User user, HttpServletResponse response) {
        logger.info("获取user对象:" + "\t" + user);
        if (StringUtils.isBlank(user.getUsername())) {
            return ServerResponse.createByErrorMessage("user为空");
        }
        return ServerResponse.createBySuccess(user);
    }*/
}
