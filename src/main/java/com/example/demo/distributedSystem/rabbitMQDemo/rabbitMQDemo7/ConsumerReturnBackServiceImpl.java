package com.example.demo.distributedSystem.rabbitMQDemo.rabbitMQDemo7;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/** 需在yml中配置
 * publisher-returns: true
 *     listener:
 *       simple:
 *         acknowledge-mode: manual
 *         retry:
 *           enabled: true
 *     publisher-confirm-type: correlated
 *      当消息由exchange传递到queue时如果传递则会触发ReturnCallback
 */
@Component
public class ConsumerReturnBackServiceImpl implements RabbitTemplate.ReturnCallback {

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println("message"+message);//消息本体
        System.out.println("i"+i);//消息唯一id
        System.out.println("s"+s);//退回原因
        System.out.println("s1"+s1);//队列名称
        System.out.println("s2"+s2);//route-key
    }
}
