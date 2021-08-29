package com.example.demo.reference;

import java.lang.ref.WeakReference;

public class People {
    private WeakReference<People> weakReference;
    private String name;
    public void finalize(){
        System.out.println("被GC了");
    }

    public People() {

    }

    public People(WeakReference<People> weakReference, String name) {
        this.weakReference = weakReference;
        this.name = name;
    }

    public WeakReference<People> getWeakReference() {
        return weakReference;
    }

    public void setWeakReference(WeakReference<People> weakReference) {
        this.weakReference = weakReference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "People{" +
                "weakReference=" + weakReference +
                ", name='" + name + '\'' +
                '}';
    }
}
