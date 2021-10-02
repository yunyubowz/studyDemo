package com.example.demo.distributedSystem.rabbitMQDemo.rabbitMQDemo6;

import com.example.demo.distributedSystem.rabbitMQDemo.RabbitMQUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 发布订阅模式
 * 的订阅者
 */
public class RabbitMQSubscribeDemo2 {
    static Connection connection = RabbitMQUtil.getConnection();

    public static void main(String[] args) throws Exception {
        Channel channel = connection.createChannel();
        //声明队列信息
        channel.queueDeclare("queueDemo2",false,false,false,null);
        //绑定交换机
        channel.queueBind("queueDemo2","exchangeDemo2","2020.10.2.13");
        channel.basicConsume("queueDemo2", new Consumer() {
            @Override
            public void handleConsumeOk(String consumerTag) {

            }

            @Override
            public void handleCancelOk(String consumerTag) {

            }

            @Override
            public void handleCancel(String consumerTag) throws IOException {

            }

            @Override
            public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {

            }

            @Override
            public void handleRecoverOk(String consumerTag) {

            }

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body)+ " tag:"+envelope.getDeliveryTag());
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
