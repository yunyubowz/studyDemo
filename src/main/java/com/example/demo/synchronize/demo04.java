package com.example.demo.synchronize;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 写一个程序来证明atomic类比synchronized更高效
 */
public class demo04 {
    static int count = 0;
    static AtomicInteger anInt = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        List<Thread> list = getThreadList();
        list.forEach(t->t.start());
        list.forEach(t-> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(count);
        long end = System.currentTimeMillis();
        System.out.println(end-start);

        long startAtomic = System.currentTimeMillis();
        list = getThreadListAtomic();
        list.forEach(t->t.start());
        list.forEach(t-> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long endAtomic = System.currentTimeMillis();
        System.out.println(anInt.get());
        System.out.println(endAtomic-startAtomic);


    }

    private static List<Thread> getThreadList(){
        List<Thread> list = new ArrayList<>();
        for(int i = 0;i<1000;i++){
            list.add(new Thread(()->m()));
        }
        return list;
    }

    private synchronized static void m(){
        for(int i = 0;i<1000;i++){
            count++;
        }
    }

    private static List<Thread> getThreadListAtomic(){
        List<Thread> list = new ArrayList<>();
        for(int i = 0;i<1000;i++){
            list.add(new Thread(()->n()));
        }
        return list;
    }

    private static void n(){
        for(int i = 0;i<1000;i++){
            anInt.incrementAndGet();
        }
    }
}
