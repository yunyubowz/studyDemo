package com.example.demo.distributedSystem.zookeeperDemo;

import lombok.SneakyThrows;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * zookeeper的正常api调用
 * <dependency>
 *   <groupId>org.apache.zookeeper</groupId>
 *   <artifactId>zookeeper</artifactId>
 *   <version>3.6.3</version>
 * </dependency>
 *
 */
public class ZookeeperDemo2 {
    private String connectString = "129.28.187.162:2181";
    private ZooKeeper zooKeeper;
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    @Before
    public void init(){
        try {
            zooKeeper = new ZooKeeper(connectString, 4000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    countDownLatch.countDown();
                    System.out.println("已经获得了连接");
                    System.out.println("接收到一个监听路径为--->"+watchedEvent.getPath());
                }
            });
            countDownLatch.await();
            countDownLatch = new CountDownLatch(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(zooKeeper);
    }


    /**
     * 创建节点
     */
    @Test
    public void createNodeDemo1(){
        String path = "/clientNodeDemo5";//设置节点路径
        byte[] bytes = "我是你爸爸".getBytes();//设置节点数据
        List<ACL> acls = new ArrayList<>();//设置权限
        ACL aclOne = new ACL(ZooDefs.Perms.ADMIN|ZooDefs.Perms.CREATE|ZooDefs.Perms.DELETE|ZooDefs.Perms.WRITE|ZooDefs.Perms.READ,new Id("world","anyone"));
        //ACL aclTwo = new ACL(ZooDefs.Perms.ALL,new Id("ip","218.104.74.252"));
        acls.add(aclOne);
        //acls.add(aclTwo);
        try {
            System.out.println(zooKeeper);
            zooKeeper.create(path,bytes,acls, CreateMode.PERSISTENT);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 获取数据
     */
    @Test
    public void getDataDemo1() {
        String path = "/clientNodeDemo4";//设置节点路径
        Stat stat = new Stat();
        try {
            byte[] bytes = zooKeeper.getData(path,false,null);
            System.out.println(new String(bytes));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 修改数据
     */
    @Test
    public void setDataDemo1(){
        String path = "/clientNodeDemo4";//设置节点路径
        Stat stat = new Stat();
        int version = 0;
        try {
            stat = zooKeeper.setData(path,"我不是你爸爸".getBytes(),version);
            System.out.println(stat.toString());
            System.out.println(version);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取子节点
     */
    @Test
    public void getChildrenNodeDemo1(){
        String path = "/clientNodeDemo4";//设置节点路径

        try {
            List<String> childrenList = zooKeeper.getChildren(path, new Watcher() {
                @SneakyThrows
                @Override
                public void process(WatchedEvent watchedEvent) {
                    List<String> childrenList = zooKeeper.getChildren(watchedEvent.getPath(),false);
                    System.out.println(childrenList);
                    countDownLatch.countDown();
                }
            });
            System.out.println(childrenList);
            countDownLatch.await();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 设置子节点
     */
    @Test
    public void setChildrenNodeDemo1(){
        try {
            String path = "/clientNodeDemo4";//设置节点路径
            ACL aclOne = new ACL(ZooDefs.Perms.ADMIN | ZooDefs.Perms.CREATE | ZooDefs.Perms.DELETE | ZooDefs.Perms.WRITE | ZooDefs.Perms.READ, new Id("world", "anyone"));
            List<ACL> aclList = new ArrayList<>();
            aclList.add(aclOne);
            zooKeeper.create(path + "/clientNodechildrenNode","测试".getBytes(),aclList,CreateMode.PERSISTENT);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 监听节点
     */
    @Test
    public void listenNode() throws KeeperException, InterruptedException {
        System.out.println("进来了");
        try {
            String path = "/clientNodeDemo4";//设置节点路径
            byte[] bytes = zooKeeper.getData(path, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("进来了");
                    countDownLatch.countDown();
                    System.out.println(watchedEvent.getPath());
                }
            }, null);
        }catch (Exception e){
            e.printStackTrace();
        }
        countDownLatch.await();
    }
}
