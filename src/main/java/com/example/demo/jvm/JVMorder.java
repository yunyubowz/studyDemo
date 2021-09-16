package com.example.demo.jvm;

import java.nio.channels.SelectableChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * JVM调优各种命令
 * jmap -histo pid 查看实例数量，实例占用空间大小，实例类名称
 * jmap -heap pid 查看堆内存信息
 * -XX:+HeapDumpOutOfMemoryError —XX:HeadDumpPath=./ 在堆内存溢出时将堆的内存快照存在文件中，并指定路径
 * jvm优化的思路就是在youngGC后进入survivor区的存活对象小于survivor区的50%，不让对象进去老年代，直接在年轻代被消化，减少fullgc的次数
 * S0C survivor 0 capacity 幸存区0的容量
 */
public class JVMorder {
    public static void main(String[] args) {
//        List<People> peopleList  = new ArrayList<>();
//        while(true){
//            People people = new People();
//            peopleList.add(people);
//            System.out.println(peopleList.size());
//        }
        JVMorder jvMorder = new JVMorder();
        jvMorder.deadLock();
    }

    public void deadLock(){
        JVMorder lock1 = new JVMorder();
        JVMorder lock2 = new JVMorder();
        new Thread(()->{
           synchronized (lock1){
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               synchronized (lock2){
                   System.out.println(Thread.currentThread().getName()+"----->当前线程结束");
               }
           }
        }).start();

        new Thread(()->{
            synchronized (lock2){
                synchronized (lock1){
                    System.out.println(Thread.currentThread().getName()+"----->当前线程结束");
                }
            }
        }).start();
    }
}
