package cn.zack.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping(path = "topicExchange")
public class ProviderMyTopicExchangeController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 消息携带路由键为MyTopicExchangeQueue.One
     * @param messageData
     * @return
     */
    @GetMapping(path = "sendOne/{messageData}")
    public String sendTopicExchangeMsgOne(@PathVariable("messageData") String messageData){
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("createTime",new Date().toString());
        hashMap.put("messageData",messageData);
        // 将消息携带绑定键值MyTopicRouting.One,发送到MyTopicExchange交换机
        rabbitTemplate.convertAndSend("MyTopicExchange","MyTopicRouting.One",hashMap);
        return "OK";
    }

    /**
     * 消息携带路由键为MyTopicExchangeQueue.One
     * @param messageData
     * @return
     */
    @GetMapping(path = "sendTwo/{messageData}")
    public String sendTopicExchangeMsgTwo(@PathVariable("messageData") String messageData){
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("createTime",new Date().toString());
        hashMap.put("messageData",messageData);
        // 将消息携带绑定键值MyTopicRouting.Two,发送到MyTopicExchange交换机
        rabbitTemplate.convertAndSend("MyTopicExchange","MyTopicRouting.Two",hashMap);
        return "OK";
    }
}
