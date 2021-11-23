package com.example.demo.AnnotationTest;

@AnnotationTest(value = "测试valueClass",path = "测试pathClass")
public class AnnotationTestClass {

    @AnnotationTest(value = "测试valueMethod",path = "测试pathMethod",isFlag = true)
    private void AnnotationTestMethod(){

    }

    @AnnotationTest(value = "测试valueFileId",path = "测试pathFileId",isFlag = true)
    private boolean isFlag;
}
