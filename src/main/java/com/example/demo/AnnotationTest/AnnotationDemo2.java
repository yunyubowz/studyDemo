package com.example.demo.AnnotationTest;

import org.reflections.Reflections;
import org.reflections.scanners.*;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

public class AnnotationDemo2 {
    public static void main(String[] args) {
        //"com.example.demo.AnnotationTest"
        Reflections f = new Reflections(new ConfigurationBuilder()
                .forPackages("com.example.demo.AnnotationTest")//指定扫描路径
                .filterInputsBy(new FilterBuilder().excludePackage("mystu")) //排除某个包，注意不能是扫描包子包，否则不生效
                .setScanners(new TypeAnnotationsScanner(),new SubTypesScanner(),new MethodAnnotationsScanner(),new MethodParameterScanner(),new FieldAnnotationsScanner()));
        Set<Class<?>> hashSetType = f.getTypesAnnotatedWith(AnnotationTest.class);
        hashSetType.forEach(k->{
            try {
                AnnotationTest annotationTest = k.getAnnotation(AnnotationTest.class);
                System.out.println("class-->"+annotationTest.path()[0]);
                System.out.println("class-->"+annotationTest.value()[0]);
                System.out.println("class-->"+annotationTest.isFlag());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println(hashSetType);
        Set<Method> hashSetMethod = f.getMethodsAnnotatedWith(AnnotationTest.class);
        hashSetMethod.forEach(k->{
            try {
                AnnotationTest annotationTest = k.getAnnotation(AnnotationTest.class);
                System.out.println("method-->"+annotationTest.path()[0]);
                System.out.println("method-->"+annotationTest.value()[0]);
                System.out.println("method-->"+annotationTest.isFlag());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println(hashSetMethod);
        Set<Field> hashFieIdMethod = f.getFieldsAnnotatedWith(AnnotationTest.class);
        hashFieIdMethod.forEach(k->{
            try {
                AnnotationTest annotationTest = k.getAnnotation(AnnotationTest.class);
                System.out.println("filid-->"+annotationTest.path()[0]);
                System.out.println("filid-->"+annotationTest.value()[0]);
                System.out.println("filid-->"+annotationTest.isFlag());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println(hashFieIdMethod);


    }
}
