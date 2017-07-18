package com.wuyong.service;


import com.wuyong.common.ServerResponse;
import com.wuyong.pojo.User;

import javax.servlet.http.HttpSession;

/**
 * Created by 坚果
 * on 2017/7/15
 */

public interface IUserService {

    public ServerResponse userRegister(User user);

    public ServerResponse login(User user, HttpSession session);
}
