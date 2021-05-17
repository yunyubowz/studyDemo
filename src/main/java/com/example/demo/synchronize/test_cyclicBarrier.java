package com.example.demo.synchronize;

import javax.sound.midi.Soundbank;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class test_cyclicBarrier {
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(7);

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        for(int i = 0;i<10;i++){
            System.out.println(i);
            new Thread(()->m()).start();
            Thread.sleep(1000);
        }
    }


    public static void m() {
            try {
                System.out.println("阻塞了");
                Thread.sleep(1000);
                cyclicBarrier.await();
                System.out.println("阻塞冲开了");
            }catch (Exception e){
                e.printStackTrace();
            }
    }
}
