package com.example.demo.jvm;

/**
 * 一个线程对应一个栈（开辟的内存的）
 * 一个方法对应一个栈帧
 * javap -c 反汇编代码
 */
public class Math {

    public int sum(){
        int a = 3;
        //iconst_3 将int类型3压入操作数栈
        //istore_1 将int类型存入局部变量1
        //iconst_2 将int类型2压入操作数栈
        //istore_2 将int类型存入局部变量2
        //iload_1  从局部变量1中装载int类型
        //iload_2  从局部变量2中装载int类型
        //iadd     执行int类型的加法
        //bipush   将一个8位带符号的整数压入操作数栈
        //imul 执行int的乘法
        //i_store3 将int类型存入局部变量3
        //iload_3  从局部变量3中装载int类型
        //ireturn  从方法中返回int类型的数据
        int b = 2;
        int c = (a+b)*10;
        return c;
    }


    public static void main(String[] args) {

        Math math = new Math();
        //new 新建一个对象
        //dup 复制栈顶部一个字长的内容
        //invokespecial 根据编译时的类型来调用实例方法
        //astore_1 将引用类型或returnAddress类型值存入局部变量1
        //aload_1 从局部变量中装载引用类型的值
        math.sum();
        //invokevirtual 调度对象的实现方法
        //pop 弹出栈顶端一个字长的内容

    }


}
