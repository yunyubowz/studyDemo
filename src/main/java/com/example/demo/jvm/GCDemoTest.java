package com.example.demo.jvm;


/**
 * 当一次性存活的对象大小超过当前要放入的survivor区的50%的大小时，由于对象动态年龄判断，这一部分对象都是同龄
 * 对导致这一部分对象会放入老年代，但是这些对象是朝生夕死的对象不应该放入老年区，可以适当调大survivor区的大小
 * 调大jvm的年轻代的大小-Xms(堆的初始化大小) -Xmx(堆的最大大小) -Xmn(堆的年轻代大小)
 *
 *
 */
public class GCDemoTest {
}
