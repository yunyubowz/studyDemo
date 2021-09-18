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
 * 集群leader的选举机制
 * 当集群刚刚启动的时候，集群会进行leader选举，或者半数以上节点无法和leader连接
 * 第一轮所有节点都会将当选票投给自己,由此导致所有节点的票的数量是一样的，进入第二轮
 * 第二轮从1节点开始节点会将票投给比自己myid大的相邻节点如果有三个节点由此导致2节点当选leader
 * 当节点启动时会在集群中寻找leader节点，如果找到leader节点自身会变成follower或者observer角色,如果找不到leader节点会变成looking状态
 * 当半数以上的follower或observer节点找不到leader节点此时会触发选举机制或leader宕机，所有的follower会变成looking状态然后进行投票选举出leader节点
 * 数据同步机制
 * zookeeper保持数据一致性一共分成两个方面
 * 1.正常数据写入提交
 * 当客户向zookeeper发送写入请求时,如果发送的不是leader服务器而是follower服务器，follower会将此请求转发给leader服务器，
 * leader会将此请求以proposal发送给follower服务器，当follower处理完proposal后会向leader服务器发送ack当leader节点收到半数以上follower发送的
 * ack后，会进行事务发送一条commit的proposal给follower服务器，当服务器收到commit的proposal后会记录事务移交将数据写入内存数据库
 * 2.节点宕机后恢复后的数据同步
 * 当follower宕机恢复后的第一时间就回去寻找leader服务器，如果此时leader节点的正在处理客户端的其他请求，无法给节点同步数据，宕机的节点就会去和leader节点比zxid
 * 如果leader节点的zxid比follower节点大，就会进行数据同步
 * zxid是一个64的编号，前32位表示leader节点的变更，没变一次就加1
 * 后32位是数据变更次数数据每变一次，也会加一
 *
 *
 *
 *
 */
public class ZookeeperDemo4 {
}
