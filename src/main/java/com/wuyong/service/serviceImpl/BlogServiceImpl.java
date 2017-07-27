package com.wuyong.service.serviceImpl;

import com.google.common.collect.Lists;

import com.wuyong.common.ServerResponse;
import com.wuyong.pojo.Blog;
import com.wuyong.pojo.User;
import com.wuyong.repository.BlogRepository;
import com.wuyong.service.IBlogService;
import com.wuyong.service.IFileService;
import com.wuyong.util.FTPUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by 坚果
 * on 2017/7/22
 */
@Transactional
@Service("iBlogService")
public class BlogServiceImpl implements IBlogService {

    private static final Logger logger = LoggerFactory.getLogger(BlogServiceImpl.class);

    @Autowired
    private IFileService iFileService;
    @Autowired
    private BlogRepository blogRepository;

    /**
     * 文件上传
     *
     * @param file 文件
     * @param path 路径
     * @return
     */
    public String richTextImgUpload(MultipartFile file, String path) {
        return iFileService.upload(file, path);
    }

    /**
     * 保存博客
     * @param blog  博客
     * @param session   会话
     * @return
     */
    public ServerResponse blogSave(Blog blog, HttpSession session){
        User currentUser = (User) session.getAttribute("currentUser");
        //TODO null?
        logger.info("currentUser====>"+currentUser);
        if (currentUser == null || currentUser.getUsername() == null) {
            return ServerResponse.createByErrorMessage("请先登录");
        }
        if (blog == null || blog.getAuthorId() == null){
            return ServerResponse.createByErrorMessage("没有编写博客或者没有登录");
        }
        if (!StringUtils.equals(blog.getAuthorId()+"",currentUser.getId()+"")) {
            return ServerResponse.createByErrorMessage("该博客与当前用户不符合");
        }
        //保存到数据库
        Blog blogSaved = blogRepository.save(blog);
        if (blogSaved.getId() == null) {
            return ServerResponse.createByErrorMessage("添加博客失败");
        }
        return ServerResponse.createBySuccess("添加成功", blogSaved);
    }

}
