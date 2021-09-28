package com.example.demo.distributedSystem.redisDemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDemo8 {
    @Autowired
    Redisson redisson;
    @Autowired
    StringRedisTemplate stringRedisTemplate;


    /**
     * Redisson的使用锁的使用
     */
    @Test
    public void testDemo1(){
        String lockKey = "lockKey";
        RLock redissonLock = redisson.getLock(lockKey);
        try {
            redissonLock.tryLock();//尝试加锁，30s后会将该锁销毁
            Integer integer  = Integer.valueOf(stringRedisTemplate.opsForValue().get("商品001"));
            System.out.println(integer);
            stringRedisTemplate.opsForValue().set("商品001", String.valueOf(--integer));
            System.out.println(stringRedisTemplate.opsForValue().get("商品001"));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            redissonLock.unlock();//释放锁
        }
    }

    /**
     * lua脚本的使用
     * lua脚本是具有原子性的，且当运行时报错后，回滚前面指令的数据
     */
    @Test
    public void testDemo2(){
        String luaScrip = "local count = redis.call('get',KEYS[1])"+
                          "local a = tonumber(count) "+
                          "local b = tonumber(ARGV[1]) "+
                          "if a > b then "+
                          "redis.call('set',KEYS[1],a-b) "+
                          "return 1 end return 0";
        DefaultRedisScript redisScript = new DefaultRedisScript(luaScrip);
        redisScript.setResultType(Long.class);
        Long result = (Long) stringRedisTemplate.execute(redisScript, Arrays.asList("商品001"),"10");
        System.out.println(result);
        System.out.println(stringRedisTemplate.opsForValue().get("商品001"));
    }








}
