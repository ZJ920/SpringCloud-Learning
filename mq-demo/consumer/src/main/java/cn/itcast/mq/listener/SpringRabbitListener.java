package cn.itcast.mq.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Map;

@Component
public class SpringRabbitListener {

    //itcast.fanout交换机
    @RabbitListener(queues = "simple.queue")
    public void listenWorkQueue1(String msg) throws InterruptedException {
        System.out.println("消费者1接收到消息：【" + msg + "】" + LocalTime.now());
        Thread.sleep(20);
    }

    @RabbitListener(queues = "simple.queue")
    public void listenWorkQueue2(String msg) throws InterruptedException {
        System.err.println("消费者2........接收到消息：【" + msg + "】" + LocalTime.now());
        Thread.sleep(200);
    }

    //------------------------------------------------------------------------------------------------------------------
    //多消费者处理
    @RabbitListener(queues = "fanout.queue1")
    public void listenFanoutQueue1(String msg) {
        System.err.println("消费者1........接收到消息：【" + msg + "】" + LocalTime.now());
    }

    @RabbitListener(queues = "fanout.queue2")
    public void listenFanoutQueue2(String msg) throws InterruptedException {
        System.err.println("消费者2........接收到消息：【" + msg + "】" + LocalTime.now());
    }

    //------------------------------------------------------------------------------------------------------------------
    //在Fanout模式中，一条消息，会被所有订阅的队列都消费。但在某些场景下，我们希望不同的消息被不同的队列消费。这时就要用到Direct类型的Exchange。
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1"),//队列
            exchange = @Exchange(name = "itcast.exchange", type = "direct"),//交换机
            key = {"red", "blue"}//key
    ))
    public void listenDirectQueue1(String msg) {
        System.err.println("消费者1........接收到消息：【" + msg + "】" + LocalTime.now());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2"),//队列
            exchange = @Exchange(name = "itcast.exchange", type = "direct"),//交换机
            key = {"red", "yellow"}//key
    ))
    public void listenDirectQueue2(String msg) {
        System.err.println("消费者2........接收到消息：【" + msg + "】" + LocalTime.now());
    }
    //------------------------------------------------------------------------------------------------------------------

    /**
     * `Topic`类型的`Exchange`与`Direct`相比，都是可以根据`RoutingKey`把消息路由到不同的队列。
     * 只不过`Topic`类型`Exchange`可以让队列在绑定`Routing key` 的时候使用通配符！
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue1"),//队列
            exchange = @Exchange(name = "itcast.topic", type = ExchangeTypes.TOPIC),//交换机
            key = "china.#"//以china开头的所有key
    ))
    public void listenTopicQueue1(String msg) {
        System.err.println("消费者1........接收到消息：【" + msg + "】" + LocalTime.now());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue2"),//队列
            exchange = @Exchange(name = "itcast.topic", type = ExchangeTypes.TOPIC),//交换机
            key = "#.news"//以news结尾的所有key
    ))
    public void listenTopicQueue2(String msg) {
        System.err.println("消费者2........接收到消息：【" + msg + "】" + LocalTime.now());
    }

    //Spring会把你发送的消息序列化为字节发送给MQ，接收消息的时候，还会把字节反序列化为Java对象。
    //------------------------------------------------------------------------------------------------------------------
    @RabbitListener(queues = "object.queue")
    public void listenSimpleQueue(Map<String, Object> mapMsg) throws Exception {
        System.err.println("消费者object队列1........接收到消息：【" + mapMsg + "】" + LocalTime.now());
    }
}
