package cn.zack.controller;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 指定监听的队列名称
 */
@RabbitListener(queues = "MyDirectExchangeQueue")
@Component
public class ConsumerMyDirectExchangeReceiver {
    @RabbitHandler
    public void process(Map hashMap){
        System.out.println("ConsumerDirectExchange接收到消息: " + hashMap.get("messageData"));
        System.out.println("消息发送时间是: " + hashMap.get("createTime"));
    }
}
