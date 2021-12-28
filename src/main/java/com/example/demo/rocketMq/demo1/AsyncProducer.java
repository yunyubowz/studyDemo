package com.example.demo.rocketMq.demo1;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.CountDownLatch;

/**
 * 异步发送
 */
public class AsyncProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
        producer.setNamesrvAddr("129.28.187.162:9876");
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0;i<100;i++){
            final int index = i;
            Message msg = new Message("TopicTest",
                    "TagA",
                    "OrderID188",
                    "Hello worldxxxxxxxx".getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.send(msg,new SendCallback(){

                @Override
                public void onSuccess(SendResult sendResult) {
                    countDownLatch.countDown();
                    System.out.printf("%-10d OK %s %n", index, sendResult.getMsgId());
                }

                @Override
                public void onException(Throwable e) {
                    System.out.printf("%-10d Exception %s %n", index, e);
                    e.printStackTrace();
                }
            });//异步发送消息
        }
        countDownLatch.await();
        System.out.println("执行完毕");
        producer.shutdown();
    }
}
