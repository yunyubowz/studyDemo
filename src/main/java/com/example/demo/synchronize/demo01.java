package com.example.demo.synchronize;

public class demo01 {
    static Object o = new Object();
    public static void main(String[] args) {
        demo01 demo01 = new demo01();
        demo01.test01();
        synchronized (o){
            System.out.println("我是谁的的谁");
        }

    }

    public synchronized void test01() {
        System.out.println("我是i++");
    }
}
