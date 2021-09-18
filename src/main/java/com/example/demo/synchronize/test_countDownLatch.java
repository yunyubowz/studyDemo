package com.example.demo.synchronize;

import java.util.concurrent.CountDownLatch;

public class test_countDownLatch {
    static int count = 10;
    static CountDownLatch countDownLatch = new CountDownLatch(count);
    public static void main(String[] args) throws Exception {
        System.out.println("阻塞中");
        new Thread(()->m()).start();
        countDownLatch.await();
        System.out.println("阻塞冲开了");
        countDownLatch = new CountDownLatch(1);
        countDownLatch.await();
        System.out.println("阻塞冲开了");

    }

    public static void m() {
        for(int i = 0;i<10;i++){
            System.out.println(i);
            try {
                Thread.sleep(1000);
                countDownLatch.countDown();
            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }
}
