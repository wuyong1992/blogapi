package com.wuyong.controller;

import com.wuyong.common.ServerResponse;
import com.wuyong.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 坚果
 * on 2017/8/16
 */
@RestController
@RequestMapping(value = "category")
public class CategoryController {

    @Autowired
    private ICategoryService iCategoryService;

    /**
     * 获取所有一级分类
     *
     * @return
     */
    @RequestMapping(value = "getCategorys")
    public ServerResponse getCategorys() {
        return iCategoryService.getCategory();
    }

}
