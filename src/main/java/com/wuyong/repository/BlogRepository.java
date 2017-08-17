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

    //查找所有可用的blog
    List<Blog> findAllByStatusOrderBySortOrderDesc(Integer status);

    //根据title 和 categoryID查找
    List<Blog> findBlogsByTitleContainingAndCategoryIdAndStatus(String title, Integer categoryId, Integer status);

    //只有categoryId时查找
    List<Blog> findBlogsByCategoryIdAndStatus(Integer categoryId, Integer status);

    //只有title时使用
    List<Blog> findBlogsByTitleContainingAndStatus(String title, Integer status);

}
