package com.example.demo.synchronize;

import java.util.ArrayList;
import java.util.List;

public class test_volatile04 {
    private volatile static int count = 0;

    public static void main(String[] args) {
        List<Thread> list = new ArrayList<>();
        for(int i = 0;i<100;i++){
            list.add(new Thread(()->addCount()));
        }
        list.forEach(t->t.start());
        list.forEach(t-> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(count);
    }


    private static void addCount(){
        for(int i = 0;i<10000;i++){
            count++;
        }
    }
}
