package com.example.demo.distributedSystem.zookeeperDemo;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Op;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * 用zookeeper实现分布式锁
 */
public class ZookeeperDemo9 {
    private String connectString = "129.28.187.162:2181,129.28.187.162:2182,129.28.187.162:2183";
    private ZkClient zkClient;
    private String lockPathRoot = "/lockRoot";
    private String lockPath = "/lock";
    private CountDownLatch countDownLatch = new CountDownLatch(100);
    private int count = 1;
    private lockZk lockZk = new lockZk();

    private void init(){
        try {
        zkClient = new ZkClient(connectString);
        for(int i = 0;i<10;i++){
            new Thread(()->{
                lockZk.tryGetLock();
                count = (Integer) zkClient.readData("/test");
                zkClient.writeData("/test",count+1);
                System.out.println(count);
                lockZk.unWriteLock();
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println(count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try {
            ZookeeperDemo9.class.newInstance().init();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void builderRoot(){
        if(!zkClient.exists(lockPathRoot)){
            zkClient.create(lockPathRoot,null, CreateMode.PERSISTENT);
        }
    }


    class lockZk{

        public String nodePath = "";

        public synchronized void tryGetLock(){
            nodePath = zkClient.create(lockPathRoot+lockPath,"waitLock", CreateMode.EPHEMERAL_SEQUENTIAL);
            getWriteLock();
        }

        public void getWriteLock(){
            List<String> childrenPathList = zkClient.getChildren(lockPathRoot).stream().sorted().map(p->lockPathRoot+"/"+p).collect(Collectors.toList());
            if(childrenPathList.indexOf(nodePath)==0){
                zkClient.writeData(nodePath,"w");
                return;
            }
            String lastStringPath = childrenPathList.get(childrenPathList.indexOf(nodePath)-1);
            if("waitLock".equals(zkClient.readData(lastStringPath))||"w".equals(zkClient.readData(lastStringPath))){
                zkClient.subscribeDataChanges(lastStringPath,new dealDataListenerExecutor());
                try {
                    wait(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                notify();
                zkClient.writeData(nodePath,"w");
            }

        }

        public void unWriteLock(){
            zkClient.delete(nodePath);
        }


        class dealDataListenerExecutor implements IZkDataListener{

            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                getWriteLock();
            }
        }

    }


}
