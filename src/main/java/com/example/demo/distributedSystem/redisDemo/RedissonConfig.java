package com.example.demo.distributedSystem.redisDemo;

import org.redisson.Redisson;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//@Component
public class RedissonConfig {

    @Bean
    public Redisson redisson(){
        Config config = new Config();
        //config.useSingleServer("192.168.133.130:8001");//单例模式
        ClusterServersConfig clusterServersConfig = config.useClusterServers()
                .addNodeAddress("redis://192.168.133.130:8001")
                .addNodeAddress("redis://192.168.133.130:8002")
                .addNodeAddress("redis://192.168.133.130:8003")
                .addNodeAddress("redis://192.168.133.130:8004")
                .addNodeAddress("redis://192.168.133.130:8005")
                .addNodeAddress("redis://192.168.133.130:8006");//集群模式
        clusterServersConfig.setPassword("yubo");
        return (Redisson)Redisson.create(config);
    }
}
