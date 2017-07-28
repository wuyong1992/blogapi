package com.wuyong.pojo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
     */
    @Id
    @GeneratedValue
    private Integer id;     //id

    private String title;   //标题
    private String imgUrl;  //图片链接
    private String intro;   //简介
    private String content; //内容
    private Integer authorId; //所属作者id

    @CreationTimestamp
    private Date created;

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
                ", created=" + created +
                '}';
    }
}
