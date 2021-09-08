package com.example.demo.jvm;

/**
 * 垃圾回收器
 * 1、Serial(-XX:UserSerialGC -XX:UserSerialOldGC)垃圾回收器是一种单线程的垃圾回收器
 * 意味着它会以一条线程去收集垃圾，在收集垃圾的过程中也会停止其他应用程序线程简称STW(Stop The world)
 * 简单而高效
 * 2、ParNew(-XX:UserParNewGC)即多线程收集垃圾的Serial垃圾收集器
 * 3、Parallel Scavenge(-XX:UserParallelGC —XX：UserParallelOldGC)
 * 是一种关注于cpu吞吐量的收集器（吞吐量=cpu用户运行代码的时间/cpu总的运行时间）
 * 4.CMS垃圾收集器
 * 分为几个阶段
 * 1.初始标记标记GC ROOTS可以直接引用的对象此阶段需要STW(停止所有用户运行的线程)
 * 2.并发标记 此阶段GC线程和用户线程同时开启,用一个闭环结构去标记可达对象。但是此阶段并不能保证标记到所有的可达对象，因为用户在操作系统时，
 * 会不断的产生新的对象，GC线程无法保证可达性分析的实时性，所以GC会记录下发生引用更新的地方。
 * 3.重新标记 这个阶段就是对上个阶段GC发生引用更新的地方做可达性分析,此阶段会停止掉所有用户线程（stw）
 * 4.并发清理 GC和用户线程同时运行开始清理垃圾
 * 优点并发收集，给用户停顿的感觉很少
 * 缺点占用cpu资源，无法清理浮游垃圾
 * 回收的不确定性，可能在并发标记和并发清理的时候又来一次fullGC 也就是"concurrent mode failure" 此时会进入stop the world
 * 用serial垃圾收集器去收集
 *
 */
public class GCDemo {
}
