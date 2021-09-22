package com.example.demo.distributedSystem.zookeeperDemo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 属性redis的各种api
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDemo1 {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testString(){
        System.out.println(stringRedisTemplate.opsForSet().add("demo1", "test1"));
        System.out.println(stringRedisTemplate.opsForSet().pop("demo1"));
    }













}
