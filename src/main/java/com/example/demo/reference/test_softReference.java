package com.example.demo.reference;

import java.lang.ref.SoftReference;

public class test_softReference {
    public static void main(String[] args) {
        SoftReference<byte[]> softReference = new SoftReference<>(new byte[10*1024*1024]);
        System.out.println(softReference.get());
        System.gc();
        System.out.println(softReference.get());
        byte[] bytes = new byte[15*1024*1024];
        System.out.println(softReference.get());

    }
}
