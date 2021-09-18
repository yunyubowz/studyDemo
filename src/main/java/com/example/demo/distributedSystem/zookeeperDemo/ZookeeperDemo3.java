package com.example.demo.distributedSystem.zookeeperDemo;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.apache.commons.collections4.trie.analyzer.StringKeyAnalyzer;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

/**
 * zkclient(api)的使用
 */
public class ZookeeperDemo3 {
    private static String serverString = "129.28.187.162:2181";
    private static ZkClient zkClient;
    static {
        zkClient = new ZkClient(serverString);
    }

    /**
     * 获取数据
     */
    @Test
    public void getData(){
        String path = "/clientNodeDemo4";//设置节点路径
        zkClient.setZkSerializer(new ZkSerializerMy());
        String s = zkClient.readData(path);
        System.out.println(s);
    }

    class ZkSerializerMy implements ZkSerializer {
        //序列化，数据--》byte[]
        public byte[] serialize(Object o) throws ZkMarshallingError {
            return String.valueOf(o).getBytes();
        }
        //反序列化，byte[]--->数据
        public Object deserialize(byte[] bytes) throws ZkMarshallingError {
            return new String(bytes);
        }
    }
}
