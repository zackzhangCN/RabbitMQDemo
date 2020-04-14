package cn.zack.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerMyDirectExchangeConfig {

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
}
