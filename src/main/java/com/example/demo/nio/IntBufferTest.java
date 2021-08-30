package com.example.demo.nio;

import javax.sound.midi.Soundbank;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * Buffer（缓冲区）
 *
 * Buffer是一个对象，它包含一些需要写入或者读取的数据。在NIO类库中加入Buffer对象，体现了新类库与原IO的一个重要区别。
 * 在面向流的IO中，可以直接将数据写入或读取到Stream对象中。
 * 在NIO类库中，所有的数据都是用缓冲区处理的（读写）。
 * 缓冲区实质上是一个数组，通常它是一个字节数组（ByteBuffer），也可以使用其他类型的数组。
 * 这个数组为缓冲区提供了访问数据的读写等操作属性，如位置、容量、上限等概念，具体的可以参考API文档。
 *
 * position： 它指的是下一次读取或写入的位置。
 *
 * limit： 指定还有多少数据需要写出(在从缓冲区写入通道时)，或者还有多少空间可以读入数据(在从通道读入缓冲区时)，
 * 它初始化是与capacity的值一样，当调用flip()方法之后，它的值会改变成position的值，
 * 而position被置0。它箭头所指的位置是最后一位元素的下一位所在的位置*
 *
 * capacity： 指定了可以存储在缓冲区中的最大数据容量，
 * 实际上，它指定了底层数组的大小，或者至少是指定了准许我们使用的底层数组的容量，这个初始化后就不会再改变了。
 */
public class IntBufferTest {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(10);
        intBuffer.put(10);//
        intBuffer.put(11);//
        intBuffer.put(34);//
        System.out.println("未调用复位方法之前的buffer"+intBuffer);
        intBuffer.flip();//复位,将limit变成position的值,将position置位0位,
        intBuffer.put(11);//将intBuffer位置0的值改为11，position增加1位
        intBuffer.put(1,22);//将intBuffer位置1打的值改为22，position不变
        System.out.println("调用复位方法之前的buffer"+intBuffer);
        getAll(intBuffer);

        //wrap方法的使用
        int[] arr = new int[]{1,2,3};
        IntBuffer intBuffer1 = IntBuffer.wrap(arr);
        System.out.println(intBuffer1);
        getAll(intBuffer1);
        intBuffer1 = intBuffer1.wrap(arr,0,3);
        System.out.println(intBuffer1);
        getAll(intBuffer1);


    }
    
    private static void getAll(IntBuffer buffer){
        while(true){
            System.out.println(buffer.get());//获取当前position位置的值,position增加1位
            if (buffer.position()==buffer.limit()){
                break;
            }
        }
    }
    
}
