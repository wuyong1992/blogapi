package com.wuyong.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 坚果
 * on 2017/7/28
 */
@RestController
@RequestMapping(value = "/rest")
public class TestController {


    @RequestMapping(value = "test")
    public String test() {
        return "ok";
    }
}
