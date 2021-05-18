package com.example.demo.synchronize;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class singleton {
    private static People people = null;
    private static class singletonclass{
        private static People people = new People(18,"yanyuting");
    }

    private static People getSingletonPeople(){
        if(people==null){
            people = new People(18,"yanyuting");
            return people;
        }
        return people;
    }

    public static void main(String[] args) {
       for(int i = 0;i<1000;i++){
           new Thread(()->{
               System.out.println(singleton.getSingletonPeople());
           }).start();
       }
    }
}
