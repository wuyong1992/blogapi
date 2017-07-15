package com.wuyong.jedis;

import com.wuyong.BlogapiApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * Created by 坚果
 * on 2017/7/13
 * jedis测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BlogapiApplication.class)
public class JedisDemo {

    @Autowired
    private JedisPool jedisPool;

    @Test
    public void test1() {

        //获取连接对象
        Jedis jedis = jedisPool.getResource();

        //get
        String username = jedis.get("username");
        System.out.println(username);

    }
}
