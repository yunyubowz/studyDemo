package com.example.demo.reference;

import java.lang.ref.WeakReference;

/**
 * 测试弱引用当gc发生时弱引用就会被回收
 */
public class test_weakReference {
    public static void main(String[] args) throws InterruptedException {
        People p1 = new People();
        WeakReference<People> weakReference = new WeakReference<>(p1);
        System.gc();
        p1 = null;
        System.out.println(p1);

        System.out.println(weakReference.get());
////      System.out.println(weakReference.get());
////      System.gc();
////      System.out.println(weakReference.get());
//        People people = new People(weakReference,"大哥大");
//        System.gc();
//        System.out.println(people);
//        System.out.println(weakReference.get());
////      people = null;
////      System.gc();
//        System.out.println(weakReference.get());
//        System.out.println(people);
//        ThreadLocal<People> threadLocal = new ThreadLocal<>();
//        threadLocal.set(new People());
//        System.gc();
//        System.out.println(threadLocal.get());
    }
}
