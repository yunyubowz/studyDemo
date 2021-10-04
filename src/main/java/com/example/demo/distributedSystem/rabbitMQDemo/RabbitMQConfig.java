package com.example.demo.distributedSystem.rabbitMQDemo;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    public static final String ExchangeName = "springboot_exchange_demo1";

    public static final String QueueName = "springboot_queue_demo1";

    public static final String ExchangeDLName = "springboot_exchange_dl_demo1";

    public static final String QueueDLName = "springboot_queue_dl_demo1";

    public static final String DLKey = "test.#";


    //定义交换机
    @Bean
    public Exchange ExchangeDemo(){
        return ExchangeBuilder.topicExchange(ExchangeName).durable(true).build();
    }

    @Bean
    public Queue QueueOne(){
        return QueueBuilder.durable(QueueName).build();
    }



    /**
     * 死信交换机
     * @return
     */
    @Bean
    public Exchange ExchangeDLDemo(){
        return ExchangeBuilder.topicExchange(ExchangeDLName).durable(true).build();
    }


    /**
     * 死信队列
     */
    @Bean
    public Queue QueueDLDemo(){
        Map<String, Object> hashMap = new HashMap<>();
        /**
         * 声明死信交换机
         */
        hashMap.put("x-dead-letter-exchange",ExchangeDLName);
        /**
         * 声明死信路由链
         */
        hashMap.put("x-dead-letter-routing-key",DLKey);
        return QueueBuilder.durable(QueueDLName).ttl(10000).withArguments(hashMap).build();
    }

    @Bean
    public Binding QueueBindExchange(@Qualifier("QueueOne") Queue queue,@Qualifier("ExchangeDemo") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("test.#").noargs();
    }

    @Bean
    public Binding QueueBindDLExchange(@Qualifier("QueueDLDemo") Queue queue,@Qualifier("ExchangeDemo") Exchange exchange){
        System.out.println("开始");
        return BindingBuilder.bind(queue).to(exchange).with("test.#").noargs();
    }

    @Bean
    public Binding DLQueueBindExchange(@Qualifier("QueueDLDemo") Queue queue,@Qualifier("ExchangeDLDemo") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(DLKey).noargs();
    }

    @Bean
    public Binding DLQueueBindExchangeTwo(@Qualifier("QueueOne") Queue queue,@Qualifier("ExchangeDLDemo") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(DLKey).noargs();
    }

}
