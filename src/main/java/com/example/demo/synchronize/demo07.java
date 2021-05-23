package com.example.demo.synchronize;

/**
 * 模范一个死锁
 */
public class demo07 {
    public static void main(String[] args) {
        People p1 = new People(12,"12");
        People p2 = new People(13,"13");
        new Thread(()->{
           synchronized (p1){
               System.out.println("我是p1");
               try {
                  Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               synchronized (p2){
                   System.out.println("锁p2");
               }
           }
        }).start();

        new Thread(()->{
            synchronized (p2){
                System.out.println("我是p2");
                synchronized (p1){
                    System.out.println("锁p1");
                }
            }
        }).start();

    }
}
