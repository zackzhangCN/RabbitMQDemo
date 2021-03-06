package cn.zack.controller;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 监听主题队列1
 */
@Component
@RabbitListener(queues = "MyTopicExchangeQueueOne")
public class ConsumerMyTopicExchangeReceiverOne {

    @RabbitHandler
    public void process(Map hashMap){
        System.out.println("监听主题队列1, 接收到消息: " + hashMap.get("messageData"));
        System.out.println("消息发送时间是: " + hashMap.get("createTime"));
    }
}
