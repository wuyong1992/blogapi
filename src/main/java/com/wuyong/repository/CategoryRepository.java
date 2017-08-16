package com.wuyong.repository;

import com.wuyong.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 坚果
 * on 2017/8/16
 * blog分类
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
