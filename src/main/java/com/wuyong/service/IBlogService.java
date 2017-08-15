package com.wuyong.service;

import com.wuyong.common.ServerResponse;
import com.wuyong.pojo.Blog;
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
}
