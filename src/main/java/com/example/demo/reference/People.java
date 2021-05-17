package com.example.demo.reference;

public class People {
    public void finalize(){
        System.out.println("被GC了");
    }
}
