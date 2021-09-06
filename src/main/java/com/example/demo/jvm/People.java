package com.example.demo.jvm;

public class People {
    private int age;//姓名
    private String name;//年龄

    public People(int age,String name){
        this.age = age;
        this.name= name;
    }

    public People(){
    }

    public void finalize(){
        System.out.println("我被回收了");
    }

    public void introduce(){
        System.out.println("大家好我是: "+name+"我今年: "+age);
    }
}
