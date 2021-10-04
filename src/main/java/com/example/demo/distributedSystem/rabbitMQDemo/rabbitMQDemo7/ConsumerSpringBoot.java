package com.example.demo.distributedSystem.rabbitMQDemo.rabbitMQDemo7;


import com.example.demo.distributedSystem.rabbitMQDemo.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * springboot整合rabbitMQ
 */
@Component
public class ConsumerSpringBoot {

    @RabbitListener(queues=RabbitMQConfig.QueueName)
    public void ListenerDemo(Message message, Channel channel) {
       System.out.println(message);
        try {
            Thread.sleep(100);
            System.out.println(message.getMessageProperties().getDeliveryTag());
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
