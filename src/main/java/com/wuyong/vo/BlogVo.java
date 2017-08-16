package com.wuyong.vo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by 坚果
 * on 2017/8/14
 */
@Getter
@Setter
@Component
public class BlogVo {

    private Integer id;                     //id
    private String title;                   //标题
    private String imgUrl;                  //图片链接
    private String intro;                   //简介
    private String content;                 //内容
    private Integer authorId;               //所属作者id
    private String authorName;              //所属作者姓名
    private Integer categoryId;             //所属分类id
    private String categoryName;            //所属分类名称
    private Date createTime;
    private Date updateTime;

}
