package com.example.demo.synchronize;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 9：写一个程序，在main线程中启动100个线程，100个线程完成后，主线程打印“完成”，使用join()和countdownlatch都可以完成，请比较异同。
 */
public class demo08 {
    static CountDownLatch countDownLatch = new CountDownLatch(100);
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        List<Thread> list = getListThread();
        list.forEach(s->s.start());
        list.forEach(s-> {
            try {
                s.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("完成");
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);




        startTime = System.currentTimeMillis();
        List<Thread> list2 = getListThread2();
        list2.forEach(s->s.start());
        countDownLatch.await();
        System.out.println("完成");
        endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);


    }
    private static List<Thread> getListThread(){
        List<Thread> threads = new ArrayList<>();
        for(int i = 0;i<100;i++){
            threads.add(new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"----完成");
            }));
        }
        return threads;
    }

    private static List<Thread> getListThread2(){
        List<Thread> threads = new ArrayList<>();
        for(int i = 0;i<100;i++){
            threads.add(new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"----完成");
                countDownLatch.countDown();
            }));
        }
        return threads;
    }


}
