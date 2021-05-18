package com.example.demo.queue;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class HelloQueue {
    public static void main(String[] args) {
        Queue<String> queue = new ArrayBlockingQueue<>(6);
        queue.add("5");
        queue.add("6");
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        System.out.println(queue);
    }
}
