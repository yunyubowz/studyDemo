package com.example.demo.distributedSystem.zookeeperDemo;

/**
 * redis的哨兵集群
 * 举例首先会有3台哨兵服务器，3台redis服务器1台master 2台slave 哨兵会监控redis的master节点，
 * 哨兵会根据订阅方式告诉客户端（client）master的服务器地址，当master节点挂掉之后（半数以上哨兵认为），哨兵也会帮助redis做主从切换
 * 将其中一台slave选为master节点，会修改redis的配置文件
 */
public class RedisDemo4 {
}
