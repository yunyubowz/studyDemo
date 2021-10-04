package com.example.demo.distributedSystem.rabbitMQDemo.rabbitMQDemo8;

import com.example.demo.distributedSystem.rabbitMQDemo.RabbitMQConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

//    @RabbitListener(queues = RabbitMQConfig.QueueDLName)
//    public void DLQueueLister(Message message) throws InterruptedException {
//        System.out.println("死信队列收到消息"+message);
//        Thread.sleep(10000);
//        System.out.println("10秒结束");
//    }


    @RabbitListener(queues = RabbitMQConfig.QueueName)
    public void DLDealQueueLister(Message message) throws InterruptedException {
        System.out.println("死信处理队列收到消息"+message);
    }
}
