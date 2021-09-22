package com.example.demo.distributedSystem.zookeeperDemo;


/**
 * redis主从结构
 * 一个master对应多个slave
 * 当slave第一次启动时，master会和他做全量更新
 * 1.master会将内存数据做RDB快照处理发给slave
 * 2.slave将数据写入内存
 * 3.master再将命令缓冲区的命令发给slave
 * 4.slave重写内存数据
 * 5.master通过socket长链接将最新的操作命令数据发给slave,保证主从数据一致性
 * 当slave断开后重新连接master时，slave会将数据的偏移量（即记录最新数据的一个id和master的id）如果master的id没变且偏移量在
 * master的缓冲队列中，那么就会做增量更新
 * 否则就是全量更新
 *
 */
public class RedisDemo3 {
}
