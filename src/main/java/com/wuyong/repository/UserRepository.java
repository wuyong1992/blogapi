package com.wuyong.repository;

import com.wuyong.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 坚果
 * on 2017/7/15
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public List<User> findUserByUsername(String username);


}
