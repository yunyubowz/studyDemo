package com.example.demo.reference;

/**
 * 测试强引用
 */
public class test_normalReference {
    public static void main(String[] args) {
        People people = new People();
        people = null;
        System.gc();
        while(true){

        }
    }
}
