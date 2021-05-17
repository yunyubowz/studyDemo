package com.example.demo.synchronize;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class test_condition {
    static Lock lock = new ReentrantLock();
    static Condition produce = lock.newCondition();
    static Condition consume = lock.newCondition();
    static List<String> list = new ArrayList<>();
    public static void main(String[] args) throws InterruptedException {
        getProductThreadList(10).forEach(t->t.start());
        Thread.sleep(1000);
        getConsumeThreadList(2).forEach(t->t.start());
    }

    private static void put() {
        try {
            lock.lock();
            while(list.size()==10) {
                produce.await();
            }
            consume.signalAll();
            list.add("产品");
            System.out.println("产生了一个产品"+getCount());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    private static void get() {
        try {
            lock.lock();
            while(list.size()==0) {
                consume.await();
            }
            produce.signalAll();
            list.remove(0);
            System.out.println("消费了一个产品"+getCount());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    private static int getCount(){
        try {
            lock.lock();
            return list.size();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return 0;
    }

    private static List<Thread> getProductThreadList(int count){
        List<Thread> list = new ArrayList<>();
        for(int i = 0;i<count;i++){
            list.add(new Thread(()->{
                while(true){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    put();
                }
            }));
        }
        return list;
    }

    private static List<Thread> getConsumeThreadList(int count){
        List<Thread> list = new ArrayList<>();
        for(int i = 0;i<count;i++){
            list.add(new Thread(()->{
                while(true){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    get();
                }
            }));
        }
        return list;
    }
}
