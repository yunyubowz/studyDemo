package com.example.demo.distributedSystem.zookeeperDemo;


/**
 * zookeeper集群的部署
 * 集群的作用
 * 1.用于读写分离,增加系统承载
 * 2.主从自动切换，增加服务的容错性，部分节点故障不会影响系统的使用
 * zookeeper节点有三个角色
 * leader     用于数据的写入，和向其他节点分配读取操作
 * follower   用作数据的读取，当leader节点挂掉直接可以拥有投票权，可以成为leader节点
 * observer   用作数据的读取，不拥有投票权，也不计算在可用服务器，即使挂掉也不会触发zookeeper半数服务器不可用状态
 *
 *
 */
public class ZookeeperDemo4 {
}
