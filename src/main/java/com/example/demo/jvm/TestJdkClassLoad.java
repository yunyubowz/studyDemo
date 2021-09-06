package com.example.demo.jvm;

import com.sun.crypto.provider.DESedeKeyFactory;

public class TestJdkClassLoad {
    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());//获取启动类加载器
        System.out.println(DESedeKeyFactory.class.getClassLoader().getClass().getName());//获取扩展类加载器
        System.out.println(TestJdkClassLoad.class.getClassLoader().getClass().getName());//获取应用程序加载器
        System.out.println(People.class.getClassLoader().getClass().getName());//获取应用程序加载器

    }
}
