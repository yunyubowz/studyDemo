package com.example.demo.distributedSystem.redisDemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * (1)redis的持久化(将数据持久化在硬盘上)
 * RDB模式
 * 将redis整个内存数据写入快照文件
 * 触发条件例如在60s内redis做出1000次修改数据集的命令
 * 问题是如果在60s内做了过多的修改数据集的命令，但是在同步过程中或同步前redis挂掉就会损失过多数据
 * AOF模式
 * 每当有修改数据集命令，都会被添加到AOF文件的末尾
 * 触发条件 1.每单有修改命令就修改aof（非常慢因为redis过度的做磁盘io了，但是数据足够安全，不会丢失）
 *         2.每秒修改一次aof文件，非常快不用过多的磁盘io，就算丢失数据也只会丢失1秒的数据
 * RDB 与 AOF 对比
 * RDB由于是二进制文件比AOF命令行文件小
 * RDB没有AOF数据安全
 * redis挂掉恢复数据的速度RBD比AOF快
 * (2)混合化持久化
 *    AOF在增量写入AOF文件时，同时会将写入前的内存数据做RDB快照处理，然后将RDB快照写入新的AOF文件头部，然后再将AOF增量修改命令写入新的AOF
 *    做完后才会替换旧的AOF文件
 *    AOF的命令行数据将会写在RBD的文件后
 *
 */
public class RedisDemo2 {

    @Autowired
    public RedisTemplate redisTemplate;

    @Test
    public void test(){
        redisTemplate.opsForList().leftPush("demo5","test5");
    }
}
