package cn.edu.gzmu.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * rabbitmq配置类
 * 延时队列需要做以下工作:
 * 1、访问http://www.rabbitmq.com/community-plugins.html并且ctrl+f搜索rabbitmq_delayed_message_exchange
 * 2、下载对应rabbitmq的版本并解压放入rabbitmq安装目录里面的plugins里面
 * 3、启动插件输入命令:rabbitmq-plugins enable rabbitmq_delayed_message_exchange
 * 4、查看插件列表命令:rabbitmq-plugins list
 * 5、重启rabbitmq
 *
 * @author soul
 * @version 1.0
 * @date 19-3-25 14:51
 */
@Configuration
public class RabbitConfig {
    final static String LOG_QUEUE = "logQueue";
    final static String DELAY_LOG_QUEUE = "delayLogQueue";
    final static String EXCHANGE = "Exchange";
    final static String ROUTING_KEY = "RoutingKey";
    final static String DELAY_EXCHANGE = "delayExchange";
    final static String DELAY_ROUTING_KEY = "delayRoutingKey";


    /**
     * 创建一个LOG_QUEUE的消息队列(立即消费)
     * 第一个参数是创建的queue的名字，第二个参数是是否支持持久化,第三个是否唯一,第四个是否自动删除
     *
     * @return Queue
     */
    @Bean
    public Queue immediateQueue() {
        return new Queue(LOG_QUEUE, true, false, false);
    }

    /**
     * 创建一个DELAY_LOG_QUEUE的消息队列(延时消费)
     *
     * @return Queue
     */
    @Bean
    public Queue delayQueue() {
        return new Queue(DELAY_LOG_QUEUE, true, false, false);
    }

    /**
     * 构造立即消费的交换机(exchange)
     * 一共有三种构造方法，可以只传exchange的名字，
     * 第二种，可以传exchange名字，是否支持持久化，是否可以自动删除等，
     * 第三种在第二种参数上可以增加Map，Map中可以存放自定义exchange中的参数
     *
     * @return DirectExchange
     */
    @Bean
    public DirectExchange immediateExchange() {
        return new DirectExchange(EXCHANGE, true, false);
    }

    /**
     * 构造延时消费的交换机(exchange)
     *
     * @return CustomExchange
     */
    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(DELAY_EXCHANGE, "x-delayed-message", true, false, args);
    }

    /**
     * 把立即消费的队列和立即消费的exchange绑定在一起
     *
     * @return BindingBuilder
     */
    @Bean
    public Binding immediateBinding() {
        return BindingBuilder.bind(immediateQueue()).to(immediateExchange()).with(ROUTING_KEY);
    }

    /**
     * 把延时消费的队列和延时消费的exchange绑定在一起
     *
     * @return BindingBuilder
     */
    @Bean
    public Binding delayBinding() {
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with(DELAY_ROUTING_KEY).noargs();
    }
}

