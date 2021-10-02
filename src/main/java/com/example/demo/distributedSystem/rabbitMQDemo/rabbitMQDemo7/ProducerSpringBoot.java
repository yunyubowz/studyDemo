package com.example.demo.distributedSystem.rabbitMQDemo.rabbitMQDemo7;


import com.example.demo.distributedSystem.rabbitMQDemo.RabbitMQConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * springboot整合rabbitMQ
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerSpringBoot {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    ConsumerCallBackServiceImpl consumerCallBackService;
    @Autowired
    ConsumerReturnBackServiceImpl consumerReturnBackService;

//    @Autowired
//    public ProducerSpringBoot(RabbitTemplate rabbitTemplate) {
//
//    }

    @Test
    public void Produce(){
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(consumerCallBackService);
        rabbitTemplate.setReturnCallback(consumerReturnBackService);
        for(int i = 0;i<100;i++){
            rabbitTemplate.convertAndSend(RabbitMQConfig.ExchangeName,"test.1","springboot发送的消息"+i,new CorrelationData(UUID.randomUUID().toString()));
        }
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
