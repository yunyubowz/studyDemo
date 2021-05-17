package com.example.demo.synchronize;

import java.util.ArrayList;
import java.util.List;

/**
 *要求线程交替打印A1B2。。。Z26
 */
public class test_volatile03 {
    static Thread t1 = null,t2 = null;
    public static void main(String[] args) throws Exception {
        for(int i = 0;i<1;i++){
            t2 = new Thread(()->t2());
            t1 = new Thread(()->t1());
            t2.start();
            t1.start();
            t2.join();
            t1.join();

        }
    }

    public synchronized static void t1() {
        try {
        for(int i = 97;i<123;i++) {
                System.out.print((char)i);
                test_volatile03.class.notifyAll();
                test_volatile03.class.wait();
                }
        }catch (Exception e){
            e.printStackTrace();
        }
        test_volatile03.class.notify();
        System.out.println("");
    }

    public synchronized static void t2() {
        try {
        for(int i = 1;i<27;i++) {
                System.out.print(i);
                test_volatile03.class.notifyAll();
                test_volatile03.class.wait();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        test_volatile03.class.notify();
        System.out.println("");
    }

}
