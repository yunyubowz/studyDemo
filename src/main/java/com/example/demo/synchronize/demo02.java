package com.example.demo.synchronize;

import javax.sound.midi.Soundbank;

public class demo02 {
    private static int a,b,x,y;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        while(true){
            x = 0; y = 0;
            a = 0; b = 0;
            Thread t1 = new Thread(()->m());
            Thread t2 = new Thread(()->n());
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println("第"+i+++"次x==="+x+"y==="+y);
            if(x==0&&y==0){
                break;
            }
        }

    }


    public static void m(){
        a = 1;
        x = b;
    }

    public static void n(){
        b = 1;
        y = a;
    }
}
