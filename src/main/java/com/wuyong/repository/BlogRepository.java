package com.wuyong.repository;

import com.wuyong.pojo.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 坚果
 * on 2017/7/27
 */
@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer>{

}
