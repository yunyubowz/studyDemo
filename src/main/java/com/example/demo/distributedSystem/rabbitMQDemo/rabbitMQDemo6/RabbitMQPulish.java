package com.example.demo.distributedSystem.rabbitMQDemo.rabbitMQDemo6;

import com.example.demo.distributedSystem.rabbitMQDemo.RabbitMQUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * redis的发布订阅模式
 * 发布者
 */
public class RabbitMQPulish {
    static Connection connection = RabbitMQUtil.getConnection();

    public static void main(String[] args) throws Exception {
        Channel channel = connection.createChannel();
        Map<String,String> dataMap = new HashMap<>();
        dataMap.put("2020.10.1.12","2020.10.1.12");
        dataMap.put("2020.10.2.13","2020.10.2.13");
        dataMap.put("2020.10.3.14","2020.10.3.14");
        dataMap.put("2020.10.4.15","2020.10.4.15");
        dataMap.put("2020.10.4.16","2020.10.4.15");
        dataMap.put("2020.10.1.17","2020.10.1.12");
        dataMap.put("2020.10.2.18","2020.10.2.13");
        dataMap.put("2020.10.3.19","2020.10.3.14");
        dataMap.put("2020.10.4.110","2020.10.4.15");
        dataMap.put("2020.10.4.111","2020.10.4.15");
        //开启监听模式
        channel.confirmSelect();
        channel.addConfirmListener(new ConfirmListener() {
            @Override//表明数据已被broker接受
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                //第二个参数代表接收的数据是否为批量接收，一般我们用不到。
                System.out.println("消息已被Broker接受,Tag:" + deliveryTag );
            }

            @Override//表明数据已被broker拒收   队列已满，限流，io异常
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                //第二个参数代表接收的数据是否为批量接收，一般我们用不到。
                System.out.println("消息已被Broker拒收,Tag:" + deliveryTag);
            }
        });
        channel.addReturnListener(new ReturnListener() {
            @Override//表明被broker接受消息但是没有队列接受该消息
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(replyCode+":"+replyText+":"+exchange+":"+routingKey+":"+new String(body));
            }
        });
        dataMap.forEach((k,v)->{
            try {
                channel.basicPublish("exchangeDemo2",k,true,null,v.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println("消息发送成功");
    }


}
