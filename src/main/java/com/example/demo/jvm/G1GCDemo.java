package com.example.demo.jvm;

/**
 * G1将堆分为大小相等的region默认是2048个，例如堆的大小有4096M那么每一个region的大小就是2M
 * 每一个region都有可能是老年代,年轻代,巨型对象区 -XX:G1HeapRegionSize可以指定分区的数量
 * 开始的时候年轻代的默认占比是5%,eden区和幸存区的比例是8:1:1例如年轻代有1000个region那么幸存区的数量就是100和100
 * 在不断产生对象的过程中，如果eden区被放满，那么就会计算回收eden的时间，如果远小于系统设置的期望时间,可以通过—XX:MaxGCPaseMills来设置
 * 会不断将region转为eden区（最大值60%）可以通过-XX:G1MaxNewSizePercent来调整
 * G1垃圾收集器会将大对象放入humongous区，大对象的判断条件是大于一个region的50%的大小，如果一个对象足够大会横跨多个region来防止
 * 设置humongous区的意义在于防止老年代空间被占用，
 * 当老年代占用阈值超过45%（可以通过-XX:InitiatingHeapOccupancyPercent来设置）时就会触发mixedGC
 * mixedGc（回收新生代和部分老年代以及humongous区（根据回收region的期望值来排序））
 * 回收主要是复制算法，将各个region区域的存活的对象放入其他region如果region的空间不够就会触发fullgc
 * 导致经常发生mixed导致用户效率降低
 *
 * G1垃圾收集器的一次GC的过程是
 * 初始标记（stw   标记所有GCROOTS能直接到达的对象）
 * 并发标记 (并行执行) 用一个闭环去记录可达对象，但是这个阶段并不能标记所有的可达对象，因为系统还在实时运行，因为用户系统还在不断的更新引用
 * ,所以会记录这些引用发生更新的对象
 * 重新标记 (stw)从并发标记的记录更新对象的地方出发，标记所有可达对象
 * 筛选回收 (stw)g1会根据回收每一个region的时间来排序，比如有1000个region回收前800region可以达到用户回收的期望时间那么就会这800region就不会回收了
 *
 * G1的名字garbage-first由来是因为在后台维护了一个优先列表，用于记录回收价值醉的region的例如一个region1200ms可以回收10M
 * 还有一个region150ms可以回收20M，Garbage-first会优先回收region2
 *
 *
 * garbage-first几个有点吧
 * 1.分代治理便于管理heap内存
 * 2.并发整合减少用户的停顿时间，充分利用cpu多核的性能
 * 3.空间整合，回收垃圾后不会产生多个空间碎片
 * 4.可预测的停顿：重复降低系统停顿时间
 *
 *
 *
 *
 */
public class G1GCDemo {
}
