package com.example.demo.distributedSystem.rabbitMQDemo.rabbitMQDemo1;

import com.example.demo.distributedSystem.rabbitMQDemo.RabbitMQUtil;
import com.rabbitmq.client.*;

import java.io.IOException;


/**
 * 消费者
 */
public class ConsumerDemo1 {
    public static void main(String[] args) throws Exception {
        Connection conn = RabbitMQUtil.getConnection();
        Channel channel = conn.createChannel();
        channel.queueDeclare("queueDemo1",false,false,false,null);
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
                String message = new String(body);
                System.out.println("消费者收到的消息"+message);
                System.out.println("消息的TagId"+envelope.getDeliveryTag());
                //false表示确认当前收到的消息，设置为true的时候则代表签收该消费者所有未签收的消息
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }

}
