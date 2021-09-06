package com.example.demo.jvm;


import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Matcher;

public class MyClassLoad extends ClassLoader {

    private String classPath;//类路径

    public MyClassLoad(String classPath){
        this.classPath = classPath;
    }

    private byte[] loadClassToData(String name)  {
        byte[] bytes = null;
        name = name.replaceAll("\\.", Matcher.quoteReplacement(File.separator));
        try{
            FileInputStream fileInputStream = new FileInputStream(classPath+ Matcher.quoteReplacement(File.separator)+
//                    "target"+Matcher.quoteReplacement(File.separator)+
//                    "classes"+Matcher.quoteReplacement(File.separator)+
                    name+
                    ".class");
            int len = fileInputStream.available();
            bytes = new byte[len];
            fileInputStream.read(bytes);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return bytes;
        }
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return defineClass(name,loadClassToData(name),0,loadClassToData(name).length);
    }

    public static void initClass(String classPath,int age,String name){
        try {
            MyClassLoad classLoad = new MyClassLoad(classPath);
            Class loadClass = classLoad.loadClass("com.example.demo.jvm.People");
            Constructor con = loadClass.getConstructor(int.class,String.class);//获取有参构造
            Object obj = con.newInstance(age,name);
            Method method = loadClass.getMethod("introduce",null);
            method.invoke(obj);
            System.out.println("当前"+loadClass.getName()+"类的类加载器"+loadClass.getClassLoader().getClass().getName());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        initClass("D:\\personIdeaWorkSpace",18,"吴签");
    }

}
