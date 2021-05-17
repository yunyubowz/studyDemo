package com.example.demo.synchronize;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁（共享锁，排它锁）
 */
public class test_readWriteLock {
    static Lock lock = new ReentrantLock();
    static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    public static void main(String[] args) {
        for(Thread thread:getReadThreadList()){
            thread.start();
        }

        for(Thread thread:getWriteThreadList()){
            thread.start();
        }
    }


    public static List<Thread> getReadThreadList(){
        List<Thread> list = new ArrayList<>();
        for (int i = 0;i<18;i++) list.add(new Thread(()->read()));
        return list;
    }

    public static List<Thread> getWriteThreadList(){
        List<Thread> list = new ArrayList<>();
        for (int i = 0;i<18;i++) list.add(new Thread(()->write()));
        return list;
    }

    private static void read(){
        try {
            readLock.lock();
            System.out.println(Thread.currentThread().getName()+"正在read操作");
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readLock.unlock();
        }

    }

    private static void write(){
        try {
            writeLock.lock();
            System.out.println(Thread.currentThread().getName()+"正在write操作");
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            writeLock.unlock();
        }

    }
}
