package com.example.demo.synchronize;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *AtomXXX类可以保证可见性吗？请写一个程序来证明
 */
public class demo05 {
    //static int atomicInteger = 0;
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {

        new Thread(()->{
            System.out.println("start");
            while(atomicInteger.get()==0){
            }
            System.out.println("end");
        }).start();
        Thread.sleep(1000);
        atomicInteger.incrementAndGet();
    }
}
