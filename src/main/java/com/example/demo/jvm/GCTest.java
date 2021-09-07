package com.example.demo.jvm;

import java.lang.ref.SoftReference;
import java.security.PrivateKey;

/**
 * 对象动态年龄判断
 * 在一次minorGC之后，jvm会累加survivor各年龄段存活对象的内存大小，当累加到某个年龄段时总内存大小超过survivor区的50%时。
 * 会将该年龄段以及之后的年龄段的对象移到老年代
 * 老年代空间分配担保机制 -XX:HandlePromotionFailure
 * 每次minorGC之前都会判断老年代剩余空间是否小于年轻代当前对象大小之和，如果小于则判断是否配置了-XX:HandlePromotionFailure这个参数
 * 如果配置了在判断老年代剩余空间是否小于每一次minorGC后进去老年代平均对象大小之和
 * 如果小于则会直接做fullGC不会做minorGC
 * 引用计数器法
 * 给对象添加一个引用计数器，每当有一个地方引用它计数器数量就+1，当引用失效数量就减1;任何时候计数器为0的对象都是不能再被使用的
 * 但是它没发解决对象相互引用的情况
 * public class Test{
 *     private Test t1;
 *     public static void main(String[] args){
 *         Test A = new Test();
 *         Test B = new Test();
 *         A.t1 = B;
 *         B.t1 = A;
 *         A = null;
 *         B = null;
 *         //此时虽然A和B对象都不可能再被调用到了但是,A对象和B对象在内部都有引用指向，导致引用计数器不为0,
 *         //所以引用计数器是解决不来对象相互调用的情况的
 *     }
 * }
 * 可达性分析算法
 * 通过GC Roots的对象为节点，从这些节点向下搜索，能找到的对象标记为非垃圾对象，其余未标记都为垃圾对象
 * GC Roots的根节点可以为:线程栈的本地变量，静态变量，本地方法栈的变量
 *
 * 常见引用类型
 * 强软弱虚四种引用类型
 * 强引用就是我们正常的应用
 * 软引用当eden区满了时候，没有空间放对象后，会被gc回收
 * 弱引用当发生gc时会被立即回收
 * 虚引用指向的是堆外内存，所以我们无法通过该内存拿到对象
 */
public class GCTest {
    private GCTest gcTest;
    private byte[] bytes = new byte[1024*1000*6];

    public static void main(String[] args) throws InterruptedException {
        GCTest A = new GCTest();
        GCTest B = new GCTest();
        A.gcTest = B;
        B.gcTest = A;
        A = null;
        B = null;

        /**
         * 强引用
         */
        GCTest normalReference = new GCTest();
        /**
         * 软引用，当GC发生后，发现没有新的空间去存放对象会把软引用强制回收
         * -Xmx设置jvm的最大大小内存值
         */
        SoftReference<GCTest> softReference = new SoftReference<GCTest>(new GCTest());


    }
    public void finalize(){
        System.out.println("被GC了");
    }
}
