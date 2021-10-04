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
import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * 1、缓存穿透
 * 缓存穿透是指访问一个不存在的keys,当缓存层不会命中时，会直接命中存储层，如果有大量的不存在的key访问系统，会对数据库造成很大压力
 * 解决办法，每查询一个key，就算不存在也在缓存层存一个null。
 * 布隆过滤器是一个大型的位数组和几个无偏hash函数，无偏hash函数即是能把hash值算的很均匀
 * 布隆过滤器会将当前存入的keys进行hash函数的运算，将算的数字存入当前数组的位置上，并置为1，求当前key的存不存也是如此，查看算出的位置上的值是不是都为1
 * 如果不是当前则当前key不存在
 * 2、缓存失效，当大批缓存同时失效，可能会有大量请求直接访问数据，导致数据库访问压力过大
 * 解决办法，随机设置每一个key的失效时间
 * 3、缓存雪崩,当缓存支撑不住宕掉，大批量的请求会达到存储层，
 * 解决办法，把保证缓存层高可用性  做redis集群，流量降级
 * 4、热点缓存key重建优化
 * 当热点缓存失效，且当前缓存的key比较大，缓存的不能快速重建，
 * 解决办法用分布式锁，只允许一个线程重建缓存，防止流量大部分打到缓存层
 * 5.key的取名建议  表名:业务名:id(防止key值冲突)
 * 6.防止bigKey
 * 7.redis的淘汰策略
 * 1.被动清除，当一个redis查询访问一个key的时候如果发现该key过期则清除该key
 * 2.主动清除，redis会定期去删除过期的key，随机取20个设置了过期策略的key,如果key过期就删除，如果过期的key超过25%则继续第一步
 * 3.当超过使用的设置maxmemory限定时，触发主动清理策略
 * 只有主机节点才会触发主动清理策略，
 * volatile-lru 根据LRU算法在过期key中删除（默认策略）
 * allkeys-lru 根据LRU算法删除所有键，不管数据有没有设置超时属性，直到腾出足够空间为止。
 * allkeys-random 随机删除key直到有足够空间
 * volatile-random 随机删除所有过期key 直到有足够空间
 * noeviction 不剔除任何数据，当空间满了，只响应读操作
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

    @Test
    public void test3(){//解决缓存同时失效的情况,给每一个key随机设置不同的失效时间
        List<String> keys = new ArrayList<>();
        keys.forEach(key->{
            stringRedisTemplate.opsForValue().set(key,key,new Random(300).nextInt()+300,TimeUnit.SECONDS);//随机设置缓存失效时间
        });
    }


    @Test
    public void test4(){//热点缓存重建
        String value = stringRedisTemplate.opsForValue().get("hotKey");
        if(value==null){
            try {

            }catch (Exception e){
                stringRedisTemplate.opsForValue().setIfAbsent("hotKeyCJ",Thread.currentThread().getId()+"",30,TimeUnit.SECONDS);
                stringRedisTemplate.opsForValue().set("hotKey","hotValue");
            }finally {
                stringRedisTemplate.delete("hotKey");
            }
        }
    }





}
