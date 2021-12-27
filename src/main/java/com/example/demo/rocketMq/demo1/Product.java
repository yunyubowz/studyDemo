package com.example.demo.rocketMq.demo1;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class Product {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
        producer.setNamesrvAddr("www.xn--qby4ca.xn--6qq986b3xl:9876");
        producer.start();
        for (int i = 0;i<20;i++){
            Message msg = new Message("TopicTest",
                    "TagA",
                    "OrderID188",
                    "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.sendOneway(msg);//异步发送消息
        }
        producer.shutdown();
    }
}
