package com.wuyong.service.serviceImpl;

import com.wuyong.common.ServerResponse;
import com.wuyong.pojo.User;
import com.wuyong.repository.UserRepository;
import com.wuyong.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by 坚果
 * on 2017/7/15
 */
@Transactional
@Service("iUserService")
public class UserServiceImpl implements IUserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    /**
     * 用户注册
     *
     * @param user 用户对象
     * @return
     */
    public ServerResponse userRegister(User user) {
        //前端校验，后端也防止api被恶意调用注册
        //用户名去除空格
        String username = StringUtils.trim(user.getUsername());
        if (StringUtils.isBlank(username)) {
            return ServerResponse.createByErrorMessage("请输入用户名");
        }
        if (StringUtils.isBlank(user.getNickname())) {
            return ServerResponse.createByErrorMessage("昵称还是要有的");
        }
        if (StringUtils.isBlank(user.getPassword())) {
            return ServerResponse.createByErrorMessage("请输入密码");
        }
        //判断用户名是否已经存在
        User userFind = userRepository.findUserByUsername(username);
        if (userFind != null) {
            return ServerResponse.createByErrorMessage("用户名已存在");
        }
        user.setRole(0);
        user.setStatus(0);
        //TODO password MD5加密
        userRepository.save(user);
        //TODO 注册成功后自动登录
        //将该用户放入session中
        /*User currentUser = userRepository.findUserByUsername(user.getUsername()).get(0);
        if (currentUser == null) {
            return ServerResponse.createByErrorMessage("获取用户失败");
        }
        session.setAttribute("currentUser", currentUser);
        */
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    /**
     * 用户登录并保存至session
     *
     * @param user 传入数据
     * @return
     */
    public ServerResponse login(User user) {

        User currentUser = userRepository.findUserByUsername(user.getUsername());
        if (currentUser == null) {
            return ServerResponse.createByErrorMessage("该用户不存在");
        }
        //TODO password MD+salt
        if (!StringUtils.equals(currentUser.getPassword(), user.getPassword())) {
            return ServerResponse.createByErrorMessage("密码错误");
        }
        //密码置空
        currentUser.setPassword("");
        //TODO 校验成功，angular再次发送请求获取jjwt生成的token
        return ServerResponse.createBySuccess("登录成功", currentUser);
    }

    /**
     * 判断当前用户是否已经登录
     *
     * @param session 会话
     * @return
     */
    public ServerResponse isLogin(HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null || "".equals(currentUser.getUsername())) {
            return ServerResponse.createByErrorMessage("当前用户尚未登录");
        }
        return ServerResponse.createBySuccess("该用户已登录", currentUser);
    }

    /**
     * 退出功能
     *
     * @param session 会话
     * @return
     */
    public ServerResponse logout(HttpSession session) {

        session.removeAttribute("currentUser");
        if (session.getAttribute("currentUser") != null) {
            return ServerResponse.createByErrorMessage("退出失败");
        }
        return ServerResponse.createBySuccessMessage("退出成功!");
    }
}
