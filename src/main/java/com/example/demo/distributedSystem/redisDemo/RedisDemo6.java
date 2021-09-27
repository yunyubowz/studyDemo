package com.example.demo.distributedSystem.redisDemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * spring启动redis
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDemo6 {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void testDemo1(){
        stringRedisTemplate.opsForValue().set("商品001",50+"");
    }

    @Test
    public void testDemo2(){
        System.out.println(stringRedisTemplate.opsForValue().get("测试1"));
    }
}
