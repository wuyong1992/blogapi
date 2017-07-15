package com.wuyong.pojo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by 坚果
 * on 2017/7/11
 */
@Entity
@Component
@Getter@Setter
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
    private Integer id;

    private String title;
    private String imgUrl;
    private String intro;
    private String content;

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
                '}';
    }

}
