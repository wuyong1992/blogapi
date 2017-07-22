package com.wuyong.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by 坚果
 * on 2017/7/22
 */
public interface IBlogService {

    public String upload(MultipartFile file, String path);
}
