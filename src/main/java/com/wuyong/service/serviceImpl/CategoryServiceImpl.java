package com.wuyong.service.serviceImpl;

import com.wuyong.common.ServerResponse;
import com.wuyong.pojo.Category;
import com.wuyong.repository.CategoryRepository;
import com.wuyong.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by 坚果
 * on 2017/8/16
 */
@Service("iCategoryService")
@Transactional
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    //获取所有一级分类
    public ServerResponse getCategory() {
//        排序第二个参数为对象的属性，而不是数据库的字段名称
        List<Category> categoryList = categoryRepository.findAll(new Sort(Sort.Direction.ASC, "sortOrder"));
        if (categoryList.size() > 0) {
            return ServerResponse.createBySuccess(categoryList);
        }
        return ServerResponse.createByErrorMessage("暂时没有分类");
    }
}
