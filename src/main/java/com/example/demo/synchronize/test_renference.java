package com.example.demo.synchronize;

import org.apache.commons.collections.list.PredicatedList;

import java.util.ArrayList;
import java.util.List;

public class test_renference {
    public static void main(String[] args) {
        List<People> people = new ArrayList<>();
        People people1 = new People(12,"于波");
        People people2 = new People(12,"于波");
        People people3 = new People(12,"于波");
        People people4 = new People(12,"于波");
        System.out.println(people1);
        System.out.println(people2);
        System.out.println(people3);
        System.out.println(people4);
        people.add(people1);
        people.add(people2);
        people.add(people3);
        people.add(people4);
        for(People people5:people){
            System.out.println(people5);
        }
        people1 = null;
        people2 = null;
        people3 = null;
        people4 = null;
        for(People people5:people){
            System.out.println(people5);
        }
    }

}
