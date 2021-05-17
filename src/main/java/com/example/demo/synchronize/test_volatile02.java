package com.example.demo.synchronize;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题:写个固定 容量同步容器，拥有put 和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 */
public class test_volatile02 {
    static List<String> sycn = new ArrayList<>();
    static int productCount = 0;
    static int consumeCount = 0;

    public synchronized static String get(int index) throws InterruptedException {
        while(sycn.size()==0){
            test_volatile02.class.notify();
            test_volatile02.class.wait();
        }
        String value = sycn.get(index);
        sycn.remove(index);
        return value;
    }

    public synchronized static void put(String s) throws InterruptedException {
        while(sycn.size()==10){
            test_volatile02.class.notify();
            test_volatile02.class.wait();
        }
        sycn.add(s);

    }

    public synchronized static int getCount(){
        return sycn.size();
    }

    public static void main(String[] args) {
        for(Thread thread:getProductThread()){
            thread.start();
        }
        for(Thread thread:getConsumeThread()){
            thread.start();
        }
//        for(Thread thread:getListenerThread()){
//            thread.start();
//        }

    }


    public static List<Thread> getProductThread(){
        List<Thread> arr = new ArrayList<>();
        for(int i = 0;i<10;i++){
            arr.add(new Thread(()->{
                while(true) {
                    try {
                        Thread.sleep(1000);
                        put("景区");
                        System.out.println("生成了一个当前数量" + getCount());
                        System.out.println("生成总数量"+productCount++);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }));
        }
        return arr;
    }

    public static List<Thread> getConsumeThread(){
        List<Thread> arr = new ArrayList<>();
        for(int i = 0;i<2;i++){

            arr.add(new Thread(()->{
                while(true) {
                    try {
                        Thread.sleep(1000);
                        get(0);
                        System.out.println("消费了一个当前数量" + getCount());
                        System.out.println("消费总数量"+consumeCount++);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }));
        }
        return arr;
    }


    public static List<Thread> getListenerThread(){
        List<Thread> arr = new ArrayList<>();
        for(int i = 0;i<1;i++){
            arr.add(new Thread(()->{
                while(true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("当前容器内的数量" + getCount());
                }
            }));
        }
        return arr;
    }



}
