package com.example.demo.distributedSystem.rabbitMQDemo.rabbitMQDemo2;

import com.example.demo.distributedSystem.rabbitMQDemo.RabbitMQUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class ConsumerDemo2 {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("queueDemo1",false,false,false,null);
        channel.basicQos(1);
        channel.basicConsume("queueDemo1", false, new Consumer() {
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
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("成功消费消息"+new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
