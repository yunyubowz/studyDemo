package com.example.demo.synchronize;

import net.bytebuddy.implementation.bytecode.Throw;

public class demo03 {
    public static void main(String[] args) {
        new Thread(()-> {
            try {
                n();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->m()).start();

    }

    private synchronized static void m(){
        try {
            while(true){
                Thread.sleep(1000);
                System.out.println("我是线程"+Thread.currentThread().getName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private synchronized static void n() throws Exception {

            while(true){
                try {
                    Thread.sleep(10000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                throw new Exception();
            }

    }
}
