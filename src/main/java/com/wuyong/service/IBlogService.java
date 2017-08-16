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

    public String richTextImgUpload(MultipartFile file, String path);

    public ServerResponse blogSave(Blog blog);

    public ServerResponse getAllBlogs();

    public ServerResponse getBlogById(Integer id);

    public ServerResponse blogUpdate(Blog blog);

    public ServerResponse searchBlogs(SearchParams searchParams);
}

