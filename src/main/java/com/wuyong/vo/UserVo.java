package com.wuyong.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Created by 坚果
 * on 2017/8/8
 */
@Getter
@Setter
@Component
public class UserVo {

    private Integer id;
    private String mobile;
    private String username;
    private String email;
    private Integer status;
    private Integer role;
}
