package com.example.demo.synchronize;

import javax.sound.midi.Soundbank;
import java.util.concurrent.Exchanger;

/**
 * 线程间交换数据
 */
public class test_exchanger {
    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(()->m("t1")).start();
        new Thread(()->m("t2")).start();;
    }

    public static void m(String name)  {
        try {
            System.out.println(Thread.currentThread().getName()+"正在执行----->"+name);
            System.out.println(Thread.currentThread().getName()+"正在执行----->"+exchanger.exchange(name));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
