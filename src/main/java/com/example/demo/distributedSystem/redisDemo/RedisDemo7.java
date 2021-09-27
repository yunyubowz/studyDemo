package com.example.demo.distributedSystem.redisDemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * redis的分布式锁
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDemo7 {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    String lockKey = "userId";

    @Test
    public void testDemo01() throws InterruptedException {
        String clientId = UUID.randomUUID().toString();
        Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey,clientId,10, TimeUnit.SECONDS);
        System.out.println(stringRedisTemplate.getExpire(lockKey));
        new Thread(()->{
            while(true){
                if(stringRedisTemplate.opsForValue().get("商品001")!=null){
                    try {
                        long xsends = stringRedisTemplate.getExpire(lockKey)-1;
                        System.out.println(xsends);
                        Thread.sleep(xsends*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(stringRedisTemplate.opsForValue().get(lockKey)!=null){
                        stringRedisTemplate.expire(lockKey,10,TimeUnit.SECONDS);
                    }else{
                        break;
                    }
                }else{
                    break;
                }
            }
        }).start();
        Thread.sleep(25000);
        if(!result){
            return;
        }
        try {
            Integer count = Integer.valueOf(stringRedisTemplate.opsForValue().get("商品001"));
            if(count>0){
                stringRedisTemplate.opsForValue().set("商品001",--count+"");
                System.out.println("消费成功,库存减一");
            }else{
                System.out.println("库存不足");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            stringRedisTemplate.delete(lockKey);
        }

    }
}
