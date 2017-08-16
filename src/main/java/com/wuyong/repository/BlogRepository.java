package com.wuyong.repository;

import com.wuyong.pojo.Blog;
import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 坚果
 * on 2017/7/27
 * blog
 */
@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer>{

    //根据title 和 categoryID查找
    List<Blog> findBlogsByTitleContainingAndCategoryId(String title, Integer categoryId);

    //只有categoryId时查找
    List<Blog> findBlogsByCategoryId(Integer categoryId);

    //只有title时使用
    List<Blog> findBlogsByTitleContaining(String title);

}
