package com.example.demo.queue;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class HelloQueue {
    public static void main(String[] args) {
        Queue<String> queue = new ArrayBlockingQueue<>(6);
        queue.add("5");
        System.out.println(queue.peek());

        queue.add("6");
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        for(int i = 0;i<6;i++){
            System.out.println("element--->"+queue.element());
            System.out.println(queue.poll());

        }

    }
}
