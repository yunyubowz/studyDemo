package com.example.demo.distributedSystem.rabbitMQDemo.rabbitMQDemo8;


import com.example.demo.distributedSystem.rabbitMQDemo.RabbitMQConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 生产者
 * 生产者把消息发送到交换机，交换机把消息发送到死信队列，当消息在死信队列超时，
 * 死信队列会把消息发送到死信交换机，由专家的队列处理该消息
 *
 * 正常交换机---->死信队列----->死信交换机----->专门的队列--->消费者
 * ttl (time to live)
 * 死信队列  （消息超时,消息未能成功消费都算死信）dead-letter-exchange
 * 延迟队列=ttl+死信队列
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerDLX {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void testDemo1() throws InterruptedException {
        rabbitTemplate.convertAndSend(RabbitMQConfig.ExchangeName,"test.111","test");
        Thread.sleep(1000000);
    }
}
