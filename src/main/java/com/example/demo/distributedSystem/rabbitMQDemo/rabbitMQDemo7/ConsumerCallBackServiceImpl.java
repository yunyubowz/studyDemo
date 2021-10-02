package com.example.demo.distributedSystem.rabbitMQDemo.rabbitMQDemo7;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


/**
 * 需在yml中配置
 * publisher-returns: true
 *     listener:
 *       simple:
 *         acknowledge-mode: manual
 *         retry:
 *           enabled: true
 *     publisher-confirm-type: correlated
 *     当消息由broke传递到exchange时如果传递则会触发ConfirmCallback
 */
@Component
public class ConsumerCallBackServiceImpl implements RabbitTemplate.ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if(!b){
            System.out.println("correlationData"+correlationData);//消息信息id,需要自己传递
            System.out.println("b"+b);//表示消息是否进入exchange
            System.out.println("s"+s);//退回原因
        }

    }
}
