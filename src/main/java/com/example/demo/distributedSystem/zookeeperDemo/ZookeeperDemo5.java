package com.example.demo.distributedSystem.zookeeperDemo;


import com.example.demo.util.SystemMonitor;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import sun.management.snmp.jvmmib.EnumJvmThreadCpuTimeMonitoring;

import java.lang.instrument.Instrumentation;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 实现功能一个monitor监控多个服务的负载情况
 *
 */
public class ZookeeperDemo5 {
    private String connectString = "129.28.187.162:2181,129.28.187.162:2182,129.28.187.162:2183";
    private ZkClient zkClient = null;
    private String rootPath = "/cpuWatchRootPath";
    private String childrenPath = rootPath+"/server";
    private String nodePath = "";
    private Thread thread;
    private static ZookeeperDemo5 instance;

    public static void main(String[] args) {
        ZookeeperDemo5.premain(null,null);
    }

    public static void premain(String args, Instrumentation instrumentation) {
        instance = new ZookeeperDemo5();
        if (args != null) {
            instance.connectString = args;
        }
        instance.init();
    }

    private void init(){
        /**
         * 初始化zkClient  设置会话心跳超时为5秒，连接超时为4秒
         */
        zkClient = new ZkClient(connectString,5000,20000);
        /**
         * 创建根路径
         */
        buildRoot(rootPath);
        Map<String,String> data = getSystemInfo();
        System.out.println(data);
        /**
         * 创建上报的节点
         */
        nodePath = zkClient.create(childrenPath,data,CreateMode.EPHEMERAL_SEQUENTIAL);
        /**
         * 创建线程实时更新cpu数据
         */
        thread = new Thread(()->{

            while (true){
                Map<String,String> updateData = getSystemInfo();
                System.out.println(updateData);
                zkClient.writeData(nodePath,updateData);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        thread.start();
    }


    private void buildRoot(String rootPath){
        if(!zkClient.exists(rootPath)){
            zkClient.create(rootPath,null, CreateMode.PERSISTENT);
        }
    }

    private Map<String,String> getSystemInfo(){
        Map<String,String> map = new HashMap<>();
        String lastUpdateTime = System.currentTimeMillis()+"";
        String ip = getLocalIp();
        String cpu = SystemMonitor.getCPUUserPer();
        MemoryUsage memoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        String heapUsed = memoryUsage.getUsed()/1024/1024+"";//已使用的堆内存
        String heapMax = memoryUsage.getMax()/1024/1024+"";//堆内存的大小
        map.put("lastUpdateTime",lastUpdateTime);
        map.put("ip",ip);
        map.put("cpu",cpu);
        map.put("heapUsed",heapUsed);
        map.put("heapMax",heapMax);
        return map;
    }


    private String getLocalIp(){
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
            return inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }finally {
            return "获取ip失败";
        }
    }
}
