package cn.zack.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProviderMyTopicExchangeConfig {

    /**
     * 创建队列1，名为MyTopicExchangeQueueOne
     *
     * @return
     */
    @Bean
    public Queue MyTopicExchangeQueueOne() {
        return new Queue("MyTopicExchangeQueueOne", true);
    }

    /**
     * 创建队列2，名为MyTopicExchangeQueueTwo
     *
     * @return
     */
    @Bean
    public Queue MyTopicExchangeQueueTwo() {
        return new Queue("MyTopicExchangeQueueTwo", true);
    }

    /**
     * 创建主题交换器，名为MyTopicExchange
     *
     * @return
     */
    @Bean
    TopicExchange MyTopicExchange() {
        return new TopicExchange("MyTopicExchange");
    }

    /**
     * 将主题队列1和主题交换器绑定，并设置绑定键值为MyTopicRouting.One
     * 这样只要消息携带的路由键是MyTopicRouting.One,都会被投放到主题队列1
     *
     * @return
     */
    @Bean
    Binding MyTopicExchangeBindingOne() {
        return BindingBuilder.bind(MyTopicExchangeQueueOne()).to(MyTopicExchange()).with("MyTopicRouting.One");
    }

    /**
     * 将主题队列2和主题交换器绑定，并设置绑定键值为MyTopicRouting.#
     * 这样只要消息携带的路由键是以MyTopicRouting.开头,都会被投放到主题队列2
     *
     * @return
     */
    @Bean
    Binding MyTopicExchangeBindingTwo() {
        return BindingBuilder.bind(MyTopicExchangeQueueTwo()).to(MyTopicExchange()).with("MyTopicRouting.#");
    }
}