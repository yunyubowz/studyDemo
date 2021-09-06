package com.example.demo.jvm;

/**
 * 对象动态年龄判断
 * 老年代空间分配担保机制
 * 引用计数器法
 * 可达性分析顺丰
 */
public class GCTest {
    public static void main(String[] args) {
        byte[] byte1 = new byte[500*1024*1000];//500M
        byte[] byte2 = new byte[10*1024*1000];//5M

    }
}
