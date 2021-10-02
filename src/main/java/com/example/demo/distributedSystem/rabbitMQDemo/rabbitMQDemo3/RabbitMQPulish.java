package com.example.demo.distributedSystem.rabbitMQDemo.rabbitMQDemo3;

import com.example.demo.distributedSystem.rabbitMQDemo.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * redis的发布订阅模式
 * 发布者
 * （广播模式）
 * 生产者向队列中发布消息，多个消费者订阅该交换机，获取订阅的消息
 * 具体操作
 * 生产者设置交换机
 * 消费者设置队列绑定交换机即订阅
 * 生产者发布消息的时候所有订阅该交换机的队列都会收到消息
 */
public class RabbitMQPulish {
    static Connection connection = RabbitMQUtil.getConnection();

    public static void main(String[] args) throws Exception {
        Channel channel = connection.createChannel();
        channel.basicPublish("exchangeDemo1","",null,"发布订阅模式".getBytes());
        channel.close();
        connection.close();
        System.out.println("消息发送成功");
    }


}
