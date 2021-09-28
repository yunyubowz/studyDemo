package com.example.demo.distributedSystem.redisDemo;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import jodd.util.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * 1、缓存穿透
 * 缓存穿透是指访问一个不存在的keys,当缓存层不会命中时，会直接命中存储层，如果有大量的不存在的key访问系统，会对数据库造成很大压力
 * 解决办法，每查询一个key，就算不存在也在缓存层存一个null。
 * 2、缓存
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDemo9 {
    @Autowired
    StringRedisTemplate stringRedisTemplate;



    @Test
    public void test1(){//解决缓存穿透 将不存在的keys也存入缓存层，并设置执行时间
        String value = stringRedisTemplate.opsForValue().get("key");
        if(StringUtil.isBlank(value)){
            String storge = stringRedisTemplate.opsForValue().get("key");//存储层查询
            stringRedisTemplate.opsForValue().set("key",storge);//不管是不是空值都存入数据库
            stringRedisTemplate.expire("key",60*5, TimeUnit.SECONDS);//并设置过期时间
        }
    }

    @Test
    public void test2(){//解决缓存穿透 布隆过滤器
        //1000：期望存入的数据个数，0.001：期望的误差率
        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")),1000,00.1);
        List<String> keys = new ArrayList<>();
        String lockKey = "";
        //把key存入bloom过滤器
        for(String key:keys){
            bloomFilter.put(key);
        }

        if(bloomFilter.mightContain(lockKey)){
            return;
        }

    }


}
