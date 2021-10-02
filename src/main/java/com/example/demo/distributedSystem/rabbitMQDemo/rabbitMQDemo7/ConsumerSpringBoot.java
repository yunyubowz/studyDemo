package com.example.demo.distributedSystem.rabbitMQDemo.rabbitMQDemo7;


import com.example.demo.distributedSystem.rabbitMQDemo.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * springboot整合rabbitMQ
 */
@Component
public class ConsumerSpringBoot {

    @RabbitListener(queues=RabbitMQConfig.QueueName)
    public void ListenerDemo(Message message, Channel channel) {
       System.out.println("message"+message);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
