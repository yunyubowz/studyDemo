package com.example.demo.synchronize;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现一个容器，提供两个方法，add, size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当
 * 个数到5个时，线程2给出提示并结束
 */
public class test_volatile01 {
    public static void main(String[] args) {
        new Thread(()->t2()).start();
        new Thread(()->t1()).start();

    }

    public synchronized static void t1() {
        List<String> list = new ArrayList<>();
        for(int i = 0;i<10;i++) {
            list.add("1");
            System.out.println(list.size());
            if(list.size()==5){
                test_volatile01.class.notify();
                try {
                    test_volatile01.class.wait();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }

    }

    public synchronized static void t2() {
            try {
                test_volatile01.class.wait();
                System.out.println("t2结束");
                test_volatile01.class.notify();
            }catch (Exception e){
                e.printStackTrace();
            }
    }


}
