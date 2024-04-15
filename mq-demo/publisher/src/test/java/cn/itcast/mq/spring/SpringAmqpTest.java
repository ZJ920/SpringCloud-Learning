package cn.itcast.mq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSimpleQueue() {
        // 队列名称
        String queueName = "simple.queue";
        // 消息
        String message = "hello, spring amqp!";
        // 发送消息
        rabbitTemplate.convertAndSend(queueName, message);
    }

    /**
     * workQueue
     * 向队列中不停发送消息，模拟消息堆积。
     */
    @Test
    public void testWorkQueue() throws InterruptedException {
        // 队列名称
        String queueName = "simple.queue";
        // 消息
        String message = "hello, message_";
        for (int i = 0; i < 50; i++) {
            // 发送消息
            rabbitTemplate.convertAndSend(queueName, message + i);
            Thread.sleep(20);
        }
    }

    @Test
    public void testFanoutQueue() {
        // 交换机名称
        String exchangeName = "itcast.fanout";
        // 消息
        String message = "hello, everyone!";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName,"", message);
    }

    //向blue发送消息
    @Test
    public void testDirectQueue() {
        // 交换机名称
        String exchangeName = "itcast.exchange";
        // 消息
        String message = "hello, blue!";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName,"blue", message);
    }

    //向blue发送消息
    @Test
    public void testYTopicQueue() {
        // 交换机名称
        String exchangeName = "itcast.topic";
        // 消息
        String message = "hello, china news!";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName,"china.news", message);
    }

    //消息序列化：Spring会把你发送的消息序列化为字节发送给MQ，接收消息的时候，还会把字节反序列化为Java对象。
    @Test
    public void testSendMap() throws InterruptedException {
        // 准备消息
        Map<String,Object> msg = new HashMap<>();
        msg.put("name", "Jack");
        msg.put("age", 21);
        // 发送消息
        rabbitTemplate.convertAndSend("object.queue", msg);
    }
}
