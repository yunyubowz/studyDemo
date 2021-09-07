package com.example.demo.jvm;

import java.util.ArrayList;
import java.util.List;

public class FinalizeTest {
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public FinalizeTest(int count){
        this.count = count;
    }

    public void finalize(){
        System.out.println(this.count+"被回收");
    }

    public static void main(String[] args) {
        List<FinalizeTest> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        while(true){
            list.add(new FinalizeTest(++i));
            if(i>1000){
                while(true){
                    list = null;
                    System.gc();
                }
            }
        }
    }
}
