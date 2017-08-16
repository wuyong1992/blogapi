package com.wuyong.pojo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Created by 坚果
 * on 2017/8/16
 * 搜索条件
 */
@Component
@Getter
@Setter
public class SearchParams {

    private String searchTitle;
    private Integer searchCategoryId;
}
