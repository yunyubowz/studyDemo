package com.example.demo.distributedSystem.zookeeperDemo;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 监管端，监控所有节点的负载情况
 */
public class ZookeeperDemo6 {
    private String connectString = "129.28.187.162:2181,129.28.187.162:2182,129.28.187.162:2183";
    private ZkClient zkClient;
    private String rootPath = "/cpuWatchRootPath";

    public static void main(String[] args) {
        try {
            ZookeeperDemo6.class.newInstance().init();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void init(){
        zkClient = new ZkClient(connectString,20000,20000);
        watchChilren(rootPath);
        zkClient.subscribeChildChanges(rootPath, new dealDataChangeExecutor());
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 给每一个子节点添加监听
     * @param rootPath
     */
    private void watchChilren(String rootPath){
        List<String> childrenPathList = zkClient.getChildren(rootPath);
        childrenPathList.stream().forEach(p->{
            System.out.println(p);
            zkClient.subscribeDataChanges(rootPath+"/"+p, new dealDataChangeExecutor());
        });
    }





    class dealDataChangeExecutor implements IZkDataListener,IZkChildListener{

        @Override
        public void handleDataChange(String s, Object o) throws Exception {
            System.out.println(s);
            System.out.println(o);
            doDeal(o);
        }

        @Override
        public void handleDataDeleted(String s) throws Exception {

        }

        @Override
        public void handleChildChange(String s, List<String> list) throws Exception {
            watchChilren(s);
            System.out.println(list);
        }
    }

    private void doDeal(Object o){
        Map<String,String> hashMap = (Map<String, String>) o;
        for(Map.Entry<String,String> entry:hashMap.entrySet()){
            System.out.println(entry.getKey()+"------>"+entry.getValue());
        }

    }
}
