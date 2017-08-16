package com.wuyong.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
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

    @Pattern(regexp = "^1[3|4|5|8][0-9]\\d{8}$", message = "{user.mobile.pattern}")
    private String mobile;
    @NotBlank(message = "{user.username.NotBlank}")
    private String username;
    //    @JsonIgnore   //接收不到前端传递的password
    @NotBlank(message = "{user.password.NotBlank}")
    private String password;
    @Email(message = "{user.password.Email}")
    private String email;
    private Integer status;
    private Integer role;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(updatable = false)      //不更新
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;

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
                ", mobile='" + mobile + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", role=" + role +
                ", creatTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
