package com.wuyong.service.serviceImpl;

import com.wuyong.common.ServerResponse;
import com.wuyong.pojo.User;
import com.wuyong.repository.UserRepository;
import com.wuyong.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by 坚果
 * on 2017/7/15
 */
@Transactional
@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    public ServerResponse userRegister(User user) {

//        userRepository.f

        return null;
    }

}
