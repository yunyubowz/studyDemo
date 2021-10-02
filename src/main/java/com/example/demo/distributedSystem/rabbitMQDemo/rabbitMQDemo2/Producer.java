package com.example.demo.distributedSystem.rabbitMQDemo.rabbitMQDemo2;


import com.example.demo.distributedSystem.rabbitMQDemo.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.IOException;

/**
 * 生产者向队列中设置消息，多个消费者消费
 */
public class Producer {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("queueDemo1",false,false,false,null);

        for(int i = 0;i<100;i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("票号","票号1500056: "+i);
            jsonObject.put("用户","18815596093: ");
            channel.basicPublish("","queueDemo1",null,jsonObject.toString().getBytes());
        }
        channel.close();
        connection.close();
        System.out.println("发送完毕");
    }
}
