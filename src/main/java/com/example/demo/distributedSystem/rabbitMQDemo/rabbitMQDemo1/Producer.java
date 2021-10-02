package com.example.demo.distributedSystem.rabbitMQDemo.rabbitMQDemo1;

import com.example.demo.distributedSystem.rabbitMQDemo.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;


/**
 * rabbitMQ是一个MQ(Message queue)消息队列，是用来保存消息的容器，多用于分布式系统间的通信
 * 优点
 * 1.解耦 降低各个系统间的耦合性
 * 2.削峰填谷 限制系统的每秒的访问次数，使访问量每秒到达一个均值，以至于系统减轻压力
 * 3.异步提速 例如一个商品下订单后，后续的发短信，扣款，减库存，可以存到MQ中由后续的系统从中拿数据操作，以免影响用户的体验
 * 缺点
 * 1.系统可用性低，如果mq挂了对业务会造成影响
 * 2.系统复杂度高，如何保证mq的信息不会丢失
 *
 *
 * 下面的例子是普通的队列模式
 *  生产者向队列中设置消息，一个消费者消费
 */
public class Producer {
    public static void main(String[] args) throws Exception {
        Connection conn = RabbitMQUtil.getConnection();//获取连接长链接
        Channel channel = conn.createChannel();//创建通信“通道”，相当于TCP中的虚拟连接
        //初始化管道，如果管道存在就用这个管道
        //第一个参数管道名称
        //第二个参数是否持久化
        //第三个是否私有化其他管道是能用该管道 true代表只有第一次拥有它的消费者才能一直使用，其他消费者不让访问
        //第四个：是否自动删除,false代表连接停掉后不自动删除掉这个队列
        //其他额外的参数, null
        channel.queueDeclare("queueDemo1",false,false,false,null);
        //四个参数
        //exchange 交换机，暂时用不到，在后面进行发布订阅时才会用到
        //队列名称
        //额外的设置属性
        //最后一个参数是要传递的消息字节数组
        channel.basicPublish("","queueDemo1",null,"测试信息1".getBytes());
        channel.close();
        conn.close();
        System.out.println("发送成功");

    }

}
