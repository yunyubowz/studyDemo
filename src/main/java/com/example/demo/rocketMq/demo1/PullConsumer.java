package com.example.demo.rocketMq.demo1;

import org.apache.rocketmq.client.consumer.DefaultLitePullConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PullConsumer {

    public static volatile boolean running = true;
    public static void main(String[] args) throws Exception {
        DefaultLitePullConsumer litePullConsumer = new DefaultLitePullConsumer("please_rename_unique_group_name");
        litePullConsumer.setAutoCommit(false);
        litePullConsumer.setNamesrvAddr("129.28.187.162:9876");
        litePullConsumer.start();
        Collection<MessageQueue> mqSet = litePullConsumer.fetchMessageQueues("TopicTest");
        List<MessageQueue> list = new ArrayList<>(mqSet);
        List<MessageQueue> assignList = new ArrayList<>();
        for (int i = 0; i < list.size() / 2; i++) {
            assignList.add(list.get(i));
        }
        litePullConsumer.assign(assignList);
        litePullConsumer.seek(assignList.get(0), 0);
        try {
            while (running) {
                List<MessageExt> messageExts = litePullConsumer.poll();
                System.out.printf("%s %n", messageExts);
                litePullConsumer.commitSync();
            }
        } finally {
            litePullConsumer.shutdown();
        }
    }
}
