package com.wuyong.pojo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 坚果
 * on 2017/7/11
 */
@Entity
@Component
@Getter
@Setter
public class Blog {

    /**
     * id       主键，自增长
     * title    标题
     * imgUrl   图片路径
     * intro    简介
     * content  内容
     * authorId 所属作者id
     * categoryId   所属分类id
     */
    @Id
    @GeneratedValue
    private Integer id;

    private String title;
    private String imgUrl;
    private String intro;
    private String content;
    private Integer authorId;
    private Integer categoryId;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(updatable = false)
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;
    public Blog() {
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", intro='" + intro + '\'' +
                ", content='" + content + '\'' +
                ", authorId=" + authorId +
                ", creatTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
