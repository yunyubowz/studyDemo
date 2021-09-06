package com.example.demo.jvm;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 内存溢出
 * 指的是老年代满了对象没地方存了
 * -Xss设置栈的大小
 * ‐XX:MetaspaceSize=256M
 * ‐XX:MetaspaceSize=256M
 *  -Xms堆的初始化大小
 *  -Xmx堆的最大大小
 *  -Xmn堆的新生代大小
 *  堆分4个区
 *  eden区(8/10)s1区(1/10)s2区(1/10)总的堆大小(1/3)
 *  老年代总的堆大小(2/3)
 *  minor GC第一次(当eden区满的时候回收eden区无引用的对象，将存活的对象放入s1区)
 *  minor GC第二次(当eden区满的时候回收eden区和s1区无引用的对象，将存活的对象放入s2区（如果s2区内存不够会放入老年代）)
 *  minor GC第三次(当eden区满的时候回收eden区和s2区无引用的对象，将存活的对象放入s1区（如果s1区内存不够会放入老年代）)
 *  重复2,3次当对象的经历15次minor gc还没有被回收，对象会被放入老年代
 *  当老年代满了会触发fullgc此时会stw（Stop the world）停止所有正在运行的线程，jvm调优即减少fullgc的发生
 *  将符号引用替换为直接引用，该阶段会把一些静态方法(符号引用，比如 main()方法)替换为指向数据所存内存的指针或句柄等(直接引用)，这是所谓的静态链 接过程(类加载期间完成)，动态链接是在程序运行期间完成的将符号引用替换为直接 引用，下节课会讲到动态链接
 */
public class HeadTest {
    private byte[] bytes = new byte[1024*100];//100kb大小
    private static List<HeadTest> array = new ArrayList<HeadTest>();
    public static void main(String[] args) {
        while(true){
            array.add(new HeadTest());
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
