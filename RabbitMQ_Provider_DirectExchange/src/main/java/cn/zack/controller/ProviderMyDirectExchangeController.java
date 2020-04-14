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
@RequestMapping(path = "directExchange")
public class ProviderMyDirectExchangeController {

    @Autowired
    // 注入一个rabbitTemplate
    private RabbitTemplate rabbitTemplate;

    @GetMapping(path = "send/{messageData}")
    public String sendDirectExchangeMsg(@PathVariable("messageData") String messageData){
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("createTime",new Date().toString());
        hashMap.put("messageData",messageData);
        // 将消息携带绑定键值MyDirectExchangeRouting,发送到MyDirectExchange交换机
        rabbitTemplate.convertAndSend("MyDirectExchange","MyDirectExchangeRouting",hashMap);
        return "OK";
    }
}
