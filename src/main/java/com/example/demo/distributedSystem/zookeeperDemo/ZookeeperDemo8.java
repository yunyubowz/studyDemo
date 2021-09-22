package com.example.demo.distributedSystem.zookeeperDemo;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 解决分布式的job(即定时任务问题)
 */
public class ZookeeperDemo8 {
    private String connectString = "129.28.187.162:2181,129.28.187.162:2182,129.28.187.162:2183";
    private ZkClient zkClient;
    private String jobPath = "/jobRoot";
    private String jobChildrenPath = "/job";
    private String nodePath = "";

    @Before
    public void init(){
        zkClient = new ZkClient(connectString);
        builderRoot();
        nodePath = zkClient.create(jobPath+jobChildrenPath,"slave", CreateMode.EPHEMERAL_SEQUENTIAL);
        getMaster();
    }

    private void getMaster(){
        AtomicBoolean flag = new AtomicBoolean(true);
        zkClient.getChildren(jobPath).forEach(children->{
            String dataResult = zkClient.readData(jobPath+"/"+children);
            if(dataResult.equals("master")){
                zkClient.subscribeChildChanges(jobPath,new dealDataListenerExecutor());
                flag.set(false);
            }
        });

        if(flag.get()){
            zkClient.writeData(nodePath,"master");
            System.out.println("获取master成功");
        }

    }

    private boolean isActive(){
        String result = zkClient.readData(nodePath);
        if("master".equals(result)){
            return true;
        }
        return false;
    }

    private void builderRoot(){
        if(!zkClient.exists(jobPath)){
            zkClient.create(jobPath,null,CreateMode.PERSISTENT);
        }
    }

    class dealDataListenerExecutor implements IZkChildListener {

        @Override
        public void handleChildChange(String s, List<String> list) throws Exception {
            getMaster();
        }
    }

    @Test
    public void start(){
        try {
            System.out.println(isActive());
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
