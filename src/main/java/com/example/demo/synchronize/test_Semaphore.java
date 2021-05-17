package com.example.demo.synchronize;

import java.util.concurrent.Semaphore;

/**
 * 线程间信号量
 */
public class test_Semaphore {
    static Semaphore semaphore = new Semaphore(1);
    public static void main(String[] args) {
        new Thread(()->m("t1")).start();
        new Thread(()->m("t2")).start();;
    }
    public static void m(String name)  {
        try {
            semaphore.acquire();//信号量加锁
            for(int i = 0;i<10;i++){
                System.out.println(Thread.currentThread().getName()+"正在执行----->"+name);
                Thread.sleep(200);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            semaphore.release();//信号量释放
        }

    }
}
