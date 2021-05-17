package com.example.demo.synchronize;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class test_reentrantLock2 {
    static Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        new Thread(()->m()).start();
        new Thread(()->m()).start();
    }

    private static void m(){
        try {
            lock.tryLock(1,TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(4000);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}
