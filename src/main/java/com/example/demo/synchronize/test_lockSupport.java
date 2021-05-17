package com.example.demo.synchronize;

import java.util.concurrent.locks.LockSupport;

public class test_lockSupport {

    public static void main(String[] args) throws InterruptedException {
       Thread t1 = new Thread(()->{
            System.out.println("t1开始了");
            LockSupport.park();
            System.out.println("t1结束了");
        });

        Thread t2 = new Thread(()->{
            System.out.println("t2开始了");
            LockSupport.park();
            System.out.println("t2结束了");
        });
        t1.start();
        t2.start();
        Thread.sleep(2000);
        LockSupport.unpark(t2);
        Thread.sleep(2000);
        LockSupport.unpark(t1);

    }


}
