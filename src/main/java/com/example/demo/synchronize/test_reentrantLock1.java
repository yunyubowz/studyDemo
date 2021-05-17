package com.example.demo.synchronize;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class test_reentrantLock1 {
    static Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        new Thread(()->m()).start();
        new Thread(()->m()).start();
    }

    private static void m(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName());
            lock.lock();
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
            lock.unlock();
        }

    }
}
