package com.example.demo.distributedSystem.redisDemo;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * redis集群
 * 如何配置redis集群
 * 1、新建每个集群不同的存储数据的文件夹   例如mkdir ‐p /usr/local/redis‐cluster/8001
 * 修改redis.conf配置文件文件
 * 2、daemonize yes修改集群为后台启动
 * 3、dir /url/local/redis-cluster/8001 指定文件存放目录如持久化之后的文件，集群的节点的信息。
 * 4、cluster‐enabled yes（开启集群模式）
 * 5、cluster-config-file nodes-8001.conf 集群节点信息文件名称
 * 6、cluster-node-timeout 节点间的心跳时间超过这个时间没有相应可认定该节点挂掉
 * 7、# bind 127.0.0.1(去掉IP访问限制)
 * 8、protected-mode no(关闭保护模式) 不受ip绑定限制
 * 9、appendOnly yes 开始AOF持久化模式
 * 10、requirepass  yubo （设置redis密码）
 * 11、masterauth yubo设置集群间的访问密码
 * 12、启动每个实例节点/url/local/redis/src/redis-server redis.conf
 * 13、要保证每个集群间可以正常通信，所以需要关闭防火墙
 * 14、用redis‐cli创建整个redis集群(redis5以前的版本集群是依靠ruby脚本redis‐trib.rb实现)
 * 下面命令里的1代表为每个创建的主服务器节点创建一个从服务器节点
 * 执行这条命令需要确认三台机器之间的redis实例要能相互访问，可以先简单把所有机器防火墙关掉，如果不关闭防火墙
 * 则需要打开redis服务端口和集群节点gossip通信端口
 * /usr/local/redis‐5.0.3/src/redis‐cli ‐a yubo ‐‐cluster create ‐‐cluster‐replicas 1 192.168.0.
 * 61:8001 192.168.0.62:8002 192.168.0.63:8003 192.168.0.61:8004 192.168.0.62:8005 192.168.0.63:8006
 * 15、连接集群 redis-cli -c -a yubo -h 192.168.133.130 -p 8001(-c 表示集群模式 -a表示密码 -h表示ip -p 表示端口)
 *
 *
 * redis集群的工作原理是N个redis的主从架构被组成一个集群，
 * 一个集群共有16383个槽位（slots）,每个主从架构都能分到一定的槽位,当客户端向redis集群发起一个操作命令
 * redis就会用crc16算法计算keys并先16348这个值取余（HASH_SLOT = CRC16(key) mod 16384）根据计算的槽位决定去访问那个客户端
 *
 * 当客户端向一个节点发送指令，节点发现当前key的槽位不归自己管理，节点会向把发出当前指令跳转到对应槽位的节点上，客户端也会删除本地的槽点缓存
 * ,同步最新的槽点缓存。
 *
 *
 * 集群间的通信靠gossip协议,gossip协议包括多种消息 ping pong meet fail
 * gossip协议就是一个并行的深度广度优先遍历算法，一个节点会把自己的数据扩散到与其相邻的节点上去,被扩散的节点也会重复当前操作
 * 虽然会有延迟，但具有最终一致性
 *
 * redis的集群的选举机制
 * 当一个slave感知到master节点挂掉之后，在n秒延迟之后，会向其他master节点发送转为主节点的请求(failover_auth_request),当master接收到后会
 * 确认后发送ack给slave，当超过半数的master发送ack给slave后，slave即被选举成新的master（这就是为什么要超过三个节点，如果是两个节点，挂掉一个，就剩一个是无法选举成功的）
 * 具体延迟算法如下
 * DELAY = 500ms + random(0 ~ 500ms) + SLAVE_RANK * 1000ms
 * •SLAVE_RANK表示此slave已经从master复制数据的总量的rank。Rank越小代表已复制的数据越新。这种方式下，持
 * 有最新数据的slave将会首先发起选举（理论上）。
 *
 *
 *
 *
 *
 * jedis操作
 */
public class RedisDemo5 {
    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        /**
         * 最大连接数
         */
        jedisPoolConfig.setMaxTotal(20);
        /**
         * 最大空闲数
         */
        jedisPoolConfig.setMaxIdle(10);
        /**
         * 最小空闲数
         */
        jedisPoolConfig.setMinIdle(5);
        Set<HostAndPort> jedisClusterNode = new HashSet<>();
        jedisClusterNode.add(new HostAndPort("192.168.133.130",8001));
        jedisClusterNode.add(new HostAndPort("192.168.133.130",8002));
        jedisClusterNode.add(new HostAndPort("192.168.133.130",8003));
        jedisClusterNode.add(new HostAndPort("192.168.133.130",8004));
        jedisClusterNode.add(new HostAndPort("192.168.133.130",8005));
        jedisClusterNode.add(new HostAndPort("192.168.133.130",8006));

        JedisCluster jedisCluster = null;
        jedisCluster = new JedisCluster(jedisClusterNode,5000,10000,10,"yubo",jedisPoolConfig);
        jedisCluster.set("sx","xx");
        System.out.println("插入成功");
        for(int i = 0;i<1000;i++){
            jedisCluster.set(i+"",i+"");
        }

    }


}
