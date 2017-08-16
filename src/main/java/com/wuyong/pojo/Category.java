package com.wuyong.pojo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 坚果
 * on 2017/8/16
 */
@Entity
@Component
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer parentId;      //父分类，上级分类id ，空表示该分类为一级分类
    private String name;           //分类名称
    private Integer status;         //分类状态，是否启用 0表示启用  1表示禁用
    private Integer sortOrder;      //排序字段


    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(updatable = false)
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", sortOrder=" + sortOrder +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
