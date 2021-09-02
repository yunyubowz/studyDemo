package com.example.demo.jvm;

/**
 * -Xss对应一个线程栈的最大内存值
 * 默认1M
 */
public class StackOverFlowError {

    public static int count = 0;
    public static void main(String[] args) {
        try {
            redo();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(count);
        }


    }

    public static void redo(){
        count++;
        redo();
    }
}
