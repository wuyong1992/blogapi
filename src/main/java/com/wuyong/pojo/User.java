package com.wuyong.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by 坚果
 * on 2017/7/14
 */
@Entity
@Component
@Getter
@Setter
public class User {

    /**
     * username 姓名
     * nickname 昵称
     * password 密码
     * email    邮箱
     * status   状态  0:正常    1:禁用
     * role     角色  0:普通    1:管理员
     * created  创建时间戳
     */
    @Id
    @GeneratedValue
    private Integer id;

    private String mobile;
    private String username;
    private String nickname;
    private String email;
//    @JsonIgnore   //接收不到前端传递的password
    private String password;
    private Integer status;
    private Integer role;

    @CreationTimestamp
    private Date created;

   /* @Column(columnDefinition = "0")
    public Integer getStatus() {
        return status;
    }

    @Column(columnDefinition = "0")
    public Integer getRole() {
        return role;
    }*/


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", role=" + role +
                ", created=" + created +
                '}';
    }

}
