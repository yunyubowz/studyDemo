package com.example.demo.distributedSystem.rabbitMQDemo.rabbitMQDemo5;

import com.example.demo.distributedSystem.rabbitMQDemo.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * redis的发布订阅模式
 * 发布者
 * （主题模式）
 * 生产者向队列中发布消息，多个消费者订阅该交换机，获取订阅的消息
 * 具体操作
 * 生产者设置交换机
 * 消费者设置队列绑定交换机，并设置rounting即订阅
 * 生产者发布消息的时候并设置rounting   匹配该rounting的所有订阅该交换机的队列都会收到消息
 *
 * 匹配规则
 * #代表多个词
 * *代表一个词
 * 例如    com.*  匹配 com.us 但不能匹配com.us.yu
 *        com.#  com.us 和 com.us.yu 二者都能匹配
 */
public class RabbitMQPulish {
    static Connection connection = RabbitMQUtil.getConnection();

    public static void main(String[] args) throws Exception {
        Channel channel = connection.createChannel();
        Map<String,String> dataMap = new HashMap<>();
        dataMap.put("2020.10.1.12","2020.10.1.12");
        dataMap.put("2020.10.2.13","2020.10.2.13");
        dataMap.put("2020.10.3.14","2020.10.3.14");
        dataMap.put("2020.10.4.15","2020.10.4.15");
        dataMap.put("2020.10.4.16","2020.10.4.16");
        dataMap.forEach((k,v)->{
            try {
                channel.basicPublish("exchangeDemo3",k,null,v.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        channel.close();
        connection.close();
        System.out.println("消息发送成功");
    }


}
