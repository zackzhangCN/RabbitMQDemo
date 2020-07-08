package cn.zack.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class ProviderMyDirectExchangeConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        // 指定 ConfirmCallback
        rabbitTemplate.setConfirmCallback(this);
        // 指定 ReturnCallback
        rabbitTemplate.setReturnCallback(this);
    }

    /**
     * 创建队列，名为MyDirectExchangeQueue
     *
     * @return
     */
    @Bean
    public Queue MyDirectExchangeQueue() {
        return new Queue("MyDirectExchangeQueue", true);
    }

    /**
     * 创建直连型交换机，名为MyDirectExchange
     *
     * @return
     */
    @Bean
    DirectExchange MyDirectExchange() {
        return new DirectExchange("MyDirectExchange");
    }

    /**
     * 绑定，将直连队列和直连交换器绑定，并设置绑定键值为MyDirectExchangeRouting
     *
     * @return
     */
    @Bean
    Binding MyDirectExchangeBinding() {
        return BindingBuilder.bind(MyDirectExchangeQueue()).to(MyDirectExchange()).with("MyDirectExchangeRouting");
    }

    /**
     * 实现Rabbitmq的ConfirmCallback接口, 开启消息发送成功确认
     *
     * @param correlationData 消息标识
     * @param b 是否成功
     * @param s 失败原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        System.out.println("唯一消息标识: " + correlationData);
        System.out.println("确认结果: " + b);
        System.out.println("失败原因: " + s);
    }

    /**
     * 实现Rabbitmq的ReturnCallback接口, 开启消息发送失败返回
     *
     * @param message 消息主体
     * @param i 消息主体
     * @param s 描述
     * @param s1 交换器
     * @param s2 路由键
     */
    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println("消息主体: " + message);
        System.out.println("消息主体: " + i);
        System.out.println("描述: " + s);
        System.out.println("消息使用的交换器: " + s1);
        System.out.println("消息使用的路由键: " + s2);
    }
}
