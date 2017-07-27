package com.wuyong.controller;

import com.google.common.collect.Maps;
import com.wuyong.common.Const;
import com.wuyong.common.ServerResponse;
import com.wuyong.pojo.Blog;
import com.wuyong.service.IBlogService;
import com.wuyong.service.IFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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


    @RequestMapping(value = "editorImgUpload")
    public Map editorImgUpload(@RequestParam(value = "file") MultipartFile[] files,
                               HttpServletRequest request, HttpSession session) {
        logger.info("富文本请求收到");
        logger.info("收到内容是：" + files);
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
     * @param session 会话
     */
    @PostMapping(value = "imgUpload")
    public void imgUpload(@RequestParam(value = "file") MultipartFile file,
                          HttpServletRequest request, HttpSession session) {

        //TODO 保存到对应的blog

        //this.editorImgUpload(files, request, session);
        String path = request.getSession().getServletContext().getRealPath("/upload");
        this.imageURL = imgPrefix + this.iFileService.upload(file, path);
        logger.info("imageURL====>" + imageURL);
    }

    @PostMapping(value = "blogSave")
    public ServerResponse blogSave(Blog blog, HttpSession session) {
        logger.info("接受blog信息");
        blog.setImgUrl(this.imageURL);
        //logger.info("blog====>" + blog);

        return iBlogService.blogSave(blog, session);
    }


}
