package com.example.demo.distributedSystem.rabbitMQDemo;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String ExchangeName = "springboot_exchange_demo1";

    public static final String QueueName = "springboot_queue_demo1";

    //定义交换机
    @Bean
    public Exchange ExchangeDemo(){
        return ExchangeBuilder.topicExchange(ExchangeName).durable(true).build();
    }

    @Bean
    public Queue QueueOne(){
        return QueueBuilder.durable(QueueName).build();
    }

    @Bean
    public Binding QueueBindExchange(@Qualifier("QueueOne") Queue queue,@Qualifier("ExchangeDemo") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("test.#").noargs();
    }

}
