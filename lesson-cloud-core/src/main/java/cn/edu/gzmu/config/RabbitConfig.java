package cn.edu.gzmu.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * rabbitmq配置类
 *
 * 1、TTL
 * RabbitMQ可以针对队列设置x-expires(则队列中所有的消息都有相同的过期时间)或者针对Message设置x-message-ttl(对消息进行单独设置，每条消息TTL可以不同)
 * 来控制消息的生存时间，如果超时(两者同时设置以最先到期的时间为准)，则消息变为dead letter(死信)
 *
 * 2、Dead Letter Exchanges（DLX）
 * RabbitMQ的Queue可以配置x-dead-letter-exchange和x-dead-letter-routing-key（可选）两个参数
 * 如果队列内出现了dead letter，则按照这两个参数重新路由转发到指定的队列。
 * x-dead-letter-exchange：出现dead letter之后将dead letter重新发送到指定exchange
 * x-dead-letter-routing-key：出现dead letter之后将dead letter重新按照指定的routing-key发送
 *
 * @author soul
 * @version 1.0
 * @date 19-3-25 14:51
 */
@Configuration
public class RabbitConfig {
    public final static String LOG_QUEUE = "log";
    public final static String DELAY_LOG_QUEUE = "delayLog";
    public final static String EXCHANGE = "logExchange";
    public final static String ROUTING_KEY = "logRoutingKey";
    public final static String DEAD_LETTER_EXCHANGE = "deadLetterExchange";
    public final static String DELAY_ROUTING_KEY = "delayRoutingKey";


    /**
     * 创建一个名为log的消息队列(立即消费)
     * 第一个参数是创建的queue的名字，第二个参数是是否支持持久化
     *
     * @return Queue
     */
    @Bean
    public Queue immediateQueue() {
        return new Queue(this.LOG_QUEUE, true);
    }

    /**
     * 创建一个延时队列,绑定交换机和路由
     */
    @Bean
    public Queue delayQueue() {
        Map<String, Object> params = new HashMap<>();
        // x-dead-letter-exchange 声明了队列里的死信转发到的DLX名称，
        params.put("x-dead-letter-exchange", this.EXCHANGE);
        // x-dead-letter-routing-key 声明了这些死信在转发时携带的 routing-key 名称。
        params.put("x-dead-letter-routing-key", this.ROUTING_KEY);
        return new Queue(this.DELAY_LOG_QUEUE, true, false, false, params);
    }

    /**
     * 构造立即消费的交换机
     * 一共有三种构造方法，可以只传exchange的名字，
     * 第二种，可以传exchange名字，是否支持持久化，是否可以自动删除，
     * 第三种在第二种参数上可以增加Map，Map中可以存放自定义exchange中的参数
     *
     * @return DirectExchange
     */
    @Bean
    public DirectExchange immediateExchange() {
        return new DirectExchange(this.EXCHANGE, true, false);
    }

    /**
     * 构造延时消费的交换机
     *
     * @return DirectExchange
     */
    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(this.DEAD_LETTER_EXCHANGE, true, false);
    }

    /**
     * 把立即消费的队列和立即消费的exchange绑定在一起
     *
     * @return BindingBuilder
     */
    @Bean
    public Binding immediateBinding() {
        return BindingBuilder.bind(immediateQueue()).to(immediateExchange()).with(this.ROUTING_KEY);
    }

    /**
     * 把延时消费的队列和死信消费的exchange绑定在一起
     *
     * @return BindingBuilder
     */
    @Bean
    public Binding delayBinding() {
        return BindingBuilder.bind(delayQueue()).to(deadLetterExchange()).with(this.DELAY_ROUTING_KEY);
    }
}

