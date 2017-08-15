package com.wuyong.service.serviceImpl;

import com.google.common.collect.Lists;

import com.wuyong.common.ServerResponse;
import com.wuyong.pojo.Blog;
import com.wuyong.pojo.User;
import com.wuyong.repository.BlogRepository;
import com.wuyong.repository.UserRepository;
import com.wuyong.service.IBlogService;
import com.wuyong.service.IFileService;
import com.wuyong.util.FTPUtil;
import com.wuyong.vo.BlogVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    @Autowired
    private UserRepository userRepository;

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
     *
     * @param blog 博客
     * @return
     */
    public ServerResponse blogSave(Blog blog) {
        //User currentUser = (User) session.getAttribute("currentUser");
        /*logger.info("currentUser====>"+currentUser);
        if (currentUser == null || currentUser.getUsername() == null) {
            return ServerResponse.createByErrorMessage("请先登录");
        }
        if (blog == null || blog.getAuthorId() == null){
            return ServerResponse.createByErrorMessage("没有编写博客或者没有登录");
        }
        if (!StringUtils.equals(blog.getAuthorId()+"",currentUser.getId()+"")) {
            return ServerResponse.createByErrorMessage("该博客与当前用户不符合");
        }*/
        //保存到数据库
        Blog blogSaved = blogRepository.save(blog);
        if (blogSaved.getId() == null) {
            return ServerResponse.createByErrorMessage("添加博客失败");
        }
        return ServerResponse.createBySuccess("添加成功", blogSaved);
    }

    /**
     * 获取所有blog信息
     *
     * @return
     */
    public ServerResponse getAllBlogs() {
        List<Blog> blogList = blogRepository.findAll(new Sort(Sort.Direction.DESC,"id"));

        List<BlogVo> blogVoList = new ArrayList<BlogVo>();
        BlogVo blogVo = null;
        for (Blog blog : blogList) {
            User user = userRepository.findOne(blog.getAuthorId());
            blogVo = new BlogVo();
            blogVo.setAuthorName(user.getUsername());
            blogVo.setAuthorId(blog.getAuthorId());
            blogVo.setContent(blog.getContent());   //列表展示可以不用传输具体内容
            blogVo.setId(blog.getId());
            blogVo.setImgUrl(blog.getImgUrl());
            blogVo.setIntro(blog.getIntro());
            blogVo.setTitle(blog.getTitle());
            blogVo.setCreateTime(blog.getCreateTime());
            blogVo.setUpdateTime(blog.getUpdateTime());
            blogVoList.add(blogVo);
        }

        if (blogVoList.size() > 0) {
            return ServerResponse.createBySuccess(blogVoList);
        }
        return ServerResponse.createByErrorMessage("获取失败");
    }

    //获取单个blog
    public ServerResponse getBlogById(Integer id) {
        if (id < 1) {
            return ServerResponse.createByErrorMessage("id不能是负数！");
        }
        Blog blog = blogRepository.findOne(id);
        if (blog == null) {
            return ServerResponse.createByErrorMessage("没有该id对应的blog");
        }
        BlogVo blogVo = new BlogVo();
        User user = userRepository.findOne(blog.getAuthorId());
        blogVo.setTitle(blog.getTitle());
        blogVo.setAuthorName(user.getUsername());
        blogVo.setAuthorId(blog.getAuthorId());
        blogVo.setContent(blog.getContent());
        blogVo.setId(blog.getId());
        blogVo.setImgUrl(blog.getImgUrl());
        blogVo.setIntro(blog.getIntro());
        blogVo.setTitle(blog.getTitle());
        blogVo.setCreateTime(blog.getCreateTime());
        blogVo.setUpdateTime(blog.getUpdateTime());
        return ServerResponse.createBySuccess(blogVo);
    }
}
