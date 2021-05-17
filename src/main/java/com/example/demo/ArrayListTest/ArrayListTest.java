package com.example.demo.ArrayListTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        arrayList.add("6");
        arrayList.add("7");
        arrayList.add("8");
        arrayList.add("9");
        arrayList.add("10");
        Iterator<String> iterable = arrayList.iterator();
        while (iterable.hasNext()){
            Object  obj =  iterable.next();
            System.out.println(obj);
            iterable.remove();
        }

    }


}
