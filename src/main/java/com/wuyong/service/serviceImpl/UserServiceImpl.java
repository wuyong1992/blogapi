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
        if (StringUtils.isBlank(user.getUsername())) {
            return ServerResponse.createByErrorMessage("请输入用户名");
        }
        if (StringUtils.isBlank(user.getNickname())) {
            return ServerResponse.createByErrorMessage("请输入昵称");
        }
        if (StringUtils.isBlank(user.getPassword())) {
            return ServerResponse.createByErrorMessage("请输入密码");
        }
        //判断用户名是否已经存在
        User userFind = userRepository.findUserByUsername(user.getUsername());
        if (userFind != null) {
            return ServerResponse.createByErrorMessage("用户名已存在");
        }
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
     * @param user 传入数据
     * @param session 会话
     * @return
     */
    public ServerResponse login(User user, HttpSession session) {
        User currentUser = userRepository.findUserByUsername(user.getUsername());
        if (currentUser == null) {
            return ServerResponse.createByErrorMessage("该用户不存在");
        }
        if (!StringUtils.equals(currentUser.getPassword(), user.getPassword())) {
            return ServerResponse.createByErrorMessage("密码错误");
        }
        //将当前用户置入session
        session.setAttribute("currentUser", currentUser);
        return ServerResponse.createBySuccess("注册成功",currentUser);
    }


}
