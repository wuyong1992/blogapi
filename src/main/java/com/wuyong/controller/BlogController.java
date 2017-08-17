package com.wuyong.controller;

import com.google.common.collect.Maps;
import com.wuyong.common.Const;
import com.wuyong.common.ServerResponse;
import com.wuyong.pojo.Blog;
import com.wuyong.pojo.SearchParams;
import com.wuyong.service.IBlogService;
import com.wuyong.service.IFileService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by 坚果
 * on 2017/7/22
 */
@RestController
@RequestMapping(value = "blog")
public class BlogController {

    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);
    @Value("${ftp.server.http.prefix}")
    private String imgPrefix;

    @Autowired
    private IBlogService iBlogService;
    @Autowired
    private IFileService iFileService;

    private String imageURL;


    /**
     * 富文本中图片上传信息
     *
     * @param files
     * @param request
     * @return
     */
    @RequestMapping(value = "editorImgUpload")
    public Map editorImgUpload(@RequestParam(value = "file") MultipartFile[] files,
                               HttpServletRequest request) {
        logger.info("富文本请求收到收到内容是：" + files);
        logger.info("数量：" + files.length);

        Map resultMap = Maps.newHashMap();

        String path = request.getSession().getServletContext().getRealPath("/upload");
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                String targetFileName = iBlogService.richTextImgUpload(file, path);
                logger.info(targetFileName);
                String url = imgPrefix + targetFileName;
                resultMap.put("link", url);
                return resultMap;
            }
        } else {
            //TODO
            return null;
        }

        return null;
    }

    /**
     * blog简介图片,先上传图片获取到图片url地址
     * url赋予全局变量imageURL，在blogSubmit中使用
     *
     * @param file    文件
     * @param request 请求
     */
    @PostMapping(value = "/rest/imgUpload")
    public void imgUpload(@RequestParam(value = "file") MultipartFile file,
                          HttpServletRequest request) {

        //TODO 保存到对应的blog

        //this.editorImgUpload(files, request, session);
        String path = request.getSession().getServletContext().getRealPath("/upload");
        this.imageURL = imgPrefix + this.iFileService.upload(file, path);
        logger.info("imageURL====>" + imageURL);
    }

    /**
     * blog上传
     *
     * @param blog
     * @param request
     * @return
     */
    @PostMapping(value = "/rest/blogSave")
    public ServerResponse blogSave(@RequestBody Blog blog, HttpServletRequest request) {
        logger.info("接受blog信息====>" + blog);
        if (blog.getCategoryId() == null) {
            blog.setCategoryId(4);      //TODO 硬编码，需要改
        }
        blog.setImgUrl(this.imageURL);
        this.imageURL = "";     //置空
        /*String authorization = request.getHeader("authorization");
        String token = authorization.substring(7);
        String username = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
        */
        return iBlogService.blogSave(blog);
    }


    /**
     * 更新blog
     *
     * @param blog
     * @return
     */
    @PostMapping(value = "/rest/blogUpdate")
    public ServerResponse blogUpdate(@RequestBody Blog blog) {
        //logger.info("this.imageURL" + this.imageURL); null
        blog.setImgUrl(this.imageURL);
        return iBlogService.blogUpdate(blog);
    }


    /**
     * 获取所有blog信息
     *
     * @return
     */
    @RequestMapping(value = "getAllBlogs")
    public ServerResponse getAllBlogs() {
        return iBlogService.getAllBlogs();
    }

//    TODO 分页获取blog信息

    /**
     * 根据id获取blog
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getBlogById")
    public ServerResponse getBlogById(Integer id) {
        return iBlogService.getBlogById(id);
    }

    /**
     * 根据所有条件返回对应blog
     *
     * @param searchParams 搜索条件
     * @return
     */
    public ServerResponse searchBlogs(@RequestBody SearchParams searchParams) {
        return iBlogService.searchBlogs(searchParams);
    }

    /**
     * 根据blog id 删除blog,改变状态为删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/rest/deleteBlog/{id}")
    public ServerResponse deleteBlogByid(@PathVariable(value = "id") Integer id ) {
        return iBlogService.deleteBlogById(id);
    }

    /**
     * 根据blog id 禁用log,改变状态为禁用
     * @param id
     * @return
     */
    @RequestMapping(value = "/rest/hideBlog/{id}")
    public ServerResponse hideBlogByid(@PathVariable(value = "id") Integer id ) {
        return iBlogService.hideBlogById(id);
    }

}
