package com.wuyong.service;

import com.wuyong.common.ServerResponse;
import com.wuyong.pojo.Blog;
import com.wuyong.pojo.SearchParams;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * Created by 坚果
 * on 2017/7/22
 */
public interface IBlogService {

    String richTextImgUpload(MultipartFile file, String path);

    ServerResponse blogSave(Blog blog);

    ServerResponse getAllBlogs();

    ServerResponse getBlogById(Integer id);

    ServerResponse blogUpdate(Blog blog);

    ServerResponse searchBlogs(SearchParams searchParams);

    ServerResponse deleteBlogById(Integer id);

    ServerResponse hideBlogById(Integer id);
}

