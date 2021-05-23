package com.example.demo.synchronize;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *写一个程序证明AtomXXX类的多个方法并不构成原子性
 */
public class demo06 {
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    public static void main(String[] args) throws Exception {
        List<Thread> threads = new ArrayList<>();
        for(int i = 0;i<10;i++){
            threads.add(new Thread(()->m()));
        }
        threads.forEach(t->t.start());
        new Thread(()->{
            System.out.println(atomicInteger.get());
        }).start();
        threads.forEach(t-> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(atomicInteger.get());

    }

    public static void m(){
        for(int i = 0;i<1000;i++){
            atomicInteger.incrementAndGet();
        }
    }
}
