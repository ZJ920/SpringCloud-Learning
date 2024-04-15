package cn.itcast.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {

    //序列化配置
    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue simpleQueue() {
        //创建一个名为"simple.queue"的队列
        return new Queue("simple.queue", true, false, false);
    }

    @Bean
    Queue objectQueue() {
        //创建一个名为"object.queue"的队列
        return new Queue("object.queue", true, false, false);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        String exchangeName = "itcast.fanout";
        return new FanoutExchange(exchangeName); // 创建一个名为"itcast.fanout"的fanout类型交换机
    }

    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanout.queue1"); // 创建一个名为"fanout.queue1"的队列
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanout.queue2"); // 创建一个名为"fanout.queue2"的队列
    }

    //绑定队列
    @Bean
    public Binding bindingQueue1(Queue fanoutQueue1, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange); // 将队列1绑定到交换机
    }

    @Bean
    public Binding bindingQueue2(Queue fanoutQueue2, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange); // 将队列2绑定到交换机
    }
}
