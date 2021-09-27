package com.example.demo.distributedSystem.redisDemo;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 属性redis的各种api

 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDemo1 {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * redis操作字符串
     */
    @Test
    public void testString(){
        /**
         * 单个上传字符串
         */
        stringRedisTemplate.opsForValue().set("demo1","test1");
        System.out.println(stringRedisTemplate.opsForValue().get("demo1"));
        System.out.println(stringRedisTemplate.opsForValue().size("demo1"));
        System.out.println(stringRedisTemplate.opsForValue().getAndSet("demo1","test2"));
        System.out.println(stringRedisTemplate.opsForValue().get("demo1"));
        System.out.println(stringRedisTemplate.delete("demo1"));
        /**
         * 批量上传字符串
         */
        System.out.println(stringRedisTemplate.opsForSet().add("demo2", "test1","test2"));
        System.out.println(stringRedisTemplate.opsForSet().pop("demo2"));
        System.out.println(stringRedisTemplate.opsForSet().pop("demo2"));
        /**
         * 上传map对象
         */
        Map<String,String> hashMap = new HashMap<>();
        hashMap.put("key1","value1");
        hashMap.put("key2","value2");
        hashMap.put("key3","value3");
        redisTemplate.opsForHash().putAll("demo3",hashMap);
        System.out.println(redisTemplate.opsForHash().entries("demo3"));
        redisTemplate.opsForHash().put("demo3","key4","value4");
        System.out.println(redisTemplate.opsForHash().entries("demo3"));
        /**
         *上传List对象
         */
        List<String> list = new ArrayList<>();
        list.add("valueList1");
        list.add("valueList2");
        list.add("valueList3");
        redisTemplate.opsForList().leftPushAll("demo4",list);
        System.out.println(redisTemplate.opsForList().range("demo4",0,2));
        System.out.println(redisTemplate.opsForList().leftPop("demo4"));
        new Thread(()->System.out.println(redisTemplate.opsForList().leftPop("demo5",1000, TimeUnit.SECONDS))).start();
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }














}
