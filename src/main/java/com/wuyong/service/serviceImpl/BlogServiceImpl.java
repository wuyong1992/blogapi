package com.wuyong.service.serviceImpl;

import com.google.common.collect.Lists;

import com.wuyong.service.IBlogService;
import com.wuyong.service.IFileService;
import com.wuyong.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


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


}
