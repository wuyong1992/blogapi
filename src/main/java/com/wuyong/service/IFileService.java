package com.wuyong.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by 坚果
 * on 2017/7/24
 */
public interface IFileService {

    public String upload(MultipartFile file, String path);
}
