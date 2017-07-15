package com.wuyong.common;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * 常量类
 * Created by 坚果
 * on 2017/6/18.
 */
public class Const {

    public static final String CURRENT_USER = "currentUser";

    public static final String EMAIL = "email";
    public static final String USERNAME = "username";

    //排序规则
    public interface ProductOrderBy {
        //set集合价格，使用set的事件复杂度O1，list是On，可提高效率
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc", "price_asc");
    }

    //通过内部的接口类分组
    public interface Role {
        int ROLE_CUSTOMER = 0;  //普通用户
        int ROLE_ADMIN = 1;  //管理员
    }

    //通过枚举类定义常量
    public enum ProductStatusEnum {
        ON_SALE(1, "在售");

        private String value;
        private int code;

        ProductStatusEnum(int code, String value) {
            this.value = value;
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }

}
